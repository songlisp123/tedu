<template>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">围栏列表</span>
            <el-button type="primary" style="float:right;margin-top:13px;"
            @click="router.push('/geofencemap')">新增围栏</el-button>
    </div>

    <div class="vehicleSearchCard">
            <div>
                <label for="brand">围栏名称：</label>
                <input type="text" name="name" 
                id="name" placeholder="请输入品牌"
                v-model="searchForm.name"
                @keydown.enter="loadGeo()">
            </div>

            <div class="status">
                <label for="status">状态：</label>
                <div class="select-wrapper" >
                    <select id="status"
                    v-model="searchForm.status"
                    @change="loadGeo()"> 
                        <option value="1">启用</option>
                        <option value="0">暂停使用</option>
                    </select> 
                </div>

            </div>

            <div>
                <el-button type="primary" @click="loadGeo()">提交</el-button>
                <el-button type="info" @click="reset()">重置</el-button>
            </div>
    </div>
    <!-- <el-card style="margin: 10px 5%; padding: 10px 20px; box-shadow: rgba(0, 0, 0, 0.2);"> -->
        <!-- <el-table :data="geofenceArray"> -->
            <!-- <el-table-column label="index" type="index" width="80" align="center"></el-table-column> -->
            <!-- <el-table-column label="名称" prop="name"  align="center"></el-table-column> -->
            <!-- <el-table-column label="位置" prop="position" align="center"></el-table-column> -->
            <!-- <el-table-column label="创建时间" prop="createTime" align="center"></el-table-column> -->
            <!-- <el-table-column label="状态" prop="status" align="center"> -->
                <!-- <el-switcher :inactive="0" :active="1"></el-switcher> -->
            <!-- </el-table-column> -->
            <!-- <el-table-column label="操作"> -->
                <!-- <template #default="scope"> -->
                    <!-- <el-button type="primary" link size="small">管理车辆</el-button> -->
                    <!-- <el-button type="primary" link size="small">查看围栏</el-button> -->
                    <!-- <el-button type="primary" link size="small">删除</el-button> -->
                <!-- </template> -->
            <!-- </el-table-column> -->
        <!-- </el-table> -->
    <!-- </el-card> -->
    <div class="item-wrapper">
        <div class="item p3" v-for="geofence in geofenceArray">
            <div  class="touxiang" >
                <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
            </div>
            <p class="text">围栏名称: {{ geofence.name }}</p>
            <p class="tag">位置:{{ geofence.position.substring(0,20) }}……</p>
            <p>状态：
                <el-switch inactive-value="0" active-value="1" 
                v-model="geofence.status" 
                @change="updateStatus(geofence.id,geofence.status)"></el-switch>
            </p>
            <p class="createTime">创建时间:{{ geofence.createTime }}</p>
            <p id="buttons">
                <el-button type="primary" link size="small"
                 @click="edit(geofence.id)">查看绑定车辆</el-button>
                <el-button type="parimary" link size="small">查看围栏</el-button>
                <el-button type="parimary" link size="small"
                @click="deleteGeo(geofence.id)"
                >删除</el-button>
            </p>
        </div>
    </div>

    <!-- 查看绑定车辆的弹窗 -->
    <el-dialog v-model="dialogVisible"
    title="已绑定车辆"
    style="width: 800px; padding: 10px 20px;"
    >
        <div style="margin: 10px 20px 20px ;">
            <el-button type="primary"
            @click="addBindVehicle()">添加更多车辆</el-button>
        </div>
        <div class="item-wrapper" v-for="vehicle in bindVehicleArray">
            <div class="item p3">
                <div  class="touxiang" >
                    <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
                </div>
                <p class="text">车辆品牌:{{ vehicle.brand }}</p>
                <p class="tag">车牌号:{{ vehicle.license }}</p>
                <p class="tag">车辆类型：{{ vehicle.type }}</p>
                <p><el-button type="warning"  size="small"
                    @click="cancelBind(vehicle.id)"
                    >取消绑定</el-button></p>
            </div>
        </div>
        <template #footer>
            <el-button type="primary" @click="cancel()">返回</el-button>
        </template>
    </el-dialog>

    <!-- 内层弹窗用来绑定车辆 -->
    <el-dialog v-model="innerDialogVisible" title="绑定车辆">
        <div class="item-wrapper">
            <div class="item p3" v-for="vehicle in notBindVehicleArray">
                <div  class="touxiang" >
                    <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
                </div>
                <p class="text">车辆品牌:{{ vehicle.brand }}</p>
                <p class="tag">车牌号:{{ vehicle.license }}</p>
                <p class="tag">车辆类型:{{ vehicle.type }}</p>
                <p class="tag">车辆状态:{{ vehicle.status }}</p>
                <p><el-button type="warning" size="small"
                    @click="bindVehicle(vehicle.id)"
                    >绑定</el-button></p>
            </div>
        </div>
        <template #footer>
            <el-button type="primary" @click="cancelInnerDialog()">返回</el-button>
        </template>
    </el-dialog>

</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';
import Vehicle from '../vehicle/vehicle.vue';
import router from '@/router';


//定义变量存放搜索表单
const searchForm = ref({
    name:'',
    status:''
});


//定义弹窗的可见
const dialogVisible = ref(false);


onMounted(()=>{loadGeo()});
//定义数组保存围栏数据
const geofenceArray = ref([]);

//定义变量接收后端传输的数据
const bindVehicleArray = ref([]);

//定义变量保存尚未绑定的车辆的列表
const notBindVehicleArray = ref([]);

//定义内层弹窗的可见性
const innerDialogVisible = ref(false);


//定义全局变量保存当前围栏id
var geofenceId;

function loadGeo() {
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/geofence/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('查询成功！');
            geofenceArray.value = response.data.data;
        }
        else {
            ElMessage.error('查询失败！');
        }
    });
}


function reset() {
    searchForm.value = {};
    loadGeo();
}

function updateStatus(id,status) {
    console.log(id,status);
    axios.post(BASE_URL+'/v1/geofence/update/'+id+'/'+status)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('更新成功！');
            loadGeo();
        }
        else {
            ElMessage.error('更新失败！');
        }
    });
}


function cancel() {
    console.log('用户取消操作！');
    dialogVisible.value = false;
}

function cancelInnerDialog() {
    innerDialogVisible.value = false;
}

function edit(id) {
    console.log('围栏id,%d',id);
    geofenceId = id;
    dialogVisible.value = true;
    axios.get(BASE_URL+'/v1/vehicle/query?geofenceId='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            bindVehicleArray.value = response.data.data;
        }
        else {
            ElMessage.error('出错了！请稍候重试！');
        }
    });
}


function addBindVehicle() {
    innerDialogVisible.value = true;
    axios.get(BASE_URL+'/v1/vehicle/query?geofenceBindStatus=0')
    .then((response)=>{
        if (response.data.code == 2000) {
            notBindVehicleArray.value = response.data.data;
        }
        else {
            ElMessage.error('查询失败！');
        }
    });
}

function bindVehicle(id) {
    console.log('车辆id=%d,围栏id=%d',id,geofenceId);
    axios.post(BASE_URL+'/v1/vehicle/bind/'+id+'/'+geofenceId)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('绑定成功！');
            addBindVehicle();
            edit(geofenceId);
        }
        else {
            ElMessage.error('绑定失败！');
        }
    });
}

function cancelBind(id) {
    console.log('绑定id值是:%d',id);
    axios.post(BASE_URL+'/v1/vehicle/unbind/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('取消绑定成功！');
            edit(geofenceId);
            loadGeo();
        }
        else {
            ElMessage.error('移除失败！');
        }
    });
}

function deleteGeo(id) {
    console.log(id);
    axios.post(BASE_URL+'/v1/geofence/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('移除成功！');
            loadGeo();
        }
        else {
            ElMessage.error('移除失败！');
        }
    });
}

</script>

<style>
.vehicleSearchCard {
    margin: 40px 0;
    padding: auto;
    display: flex;
    justify-content: center;
    gap: 20px;
}

.vehicleSearchCard label {
    color: #666;
}

p {
    text-align: center;
}

.touxiang {
    margin: 0 auto;
    width: 100px;
    text-align: center;

}


.item {
    background-color: #666;
    border-radius: 10px;
}

#name {
    border: none;
    width: 200px;
    height: 30px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgb(244, 244, 244);
}

.status {
    display: flex;
    flex-direction: row;
    justify-content: center;
}
#status {
    background: transparent;
    border: 0;
    border-radius: 0;
    border-bottom: 1px solid gray;
    box-shadow: none;
    color: #666;
    -webkit-appearance: none;
    width: 200px;
    height: 30px;
}

#status option {
    color: #666;
}



.select-wrapper {
    position: relative;
    width: 200px;
}

.select-wrapper:after {
  content: "<>";
  color: #666;
  font-size: 14px;
  top: 5px;
  right: 0;
  transform: rotate(90deg);
  position: absolute;
  z-index: -1;
}


.item-wrapper {
    display: grid;
    margin-left: 10%;
    margin-right: 10%;
    grid-template-columns: auto auto auto auto;
    gap: 20px;
}


#name {
    border: none;
    width: 200px;
    height: 30px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgb(244, 244, 244);
}

#name {
    border-bottom-color: var(--valid-color);
}

label {
    color: #666;
}
</style>
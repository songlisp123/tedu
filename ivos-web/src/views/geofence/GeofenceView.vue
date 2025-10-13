<template>
    <!-- <h1>xxxx电子围栏02xxxxx</h1> -->
     <div id="head">
        <p style="font-size: 15px; font-weight: bold;margin-top: 15px;">围栏管理</p>
        <el-button type="primary" @click="router.push('/geofenceMap')">新建围栏</el-button>
     </div>
     <el-card style="height: 70px; padding: 20px;  margin: 10px ;">
        <el-form :inline="true">
            <el-form-item label="围栏名称">
                <el-input placeholder="请输入" v-model="searchForm.name" @keydown.enter="submit()"></el-input>
            </el-form-item>
            <el-form-item label="围栏状态">
                <el-select style="width: 12vw;" v-model="searchForm.status" @change="submit()">
                    <el-option label="启用" value="1"></el-option>
                    <el-option label="禁用" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item >
                <el-button @click="reset()">重置</el-button>
                <el-button type="primary" @click="submit()">确定</el-button>
            </el-form-item>
        </el-form>
     </el-card>

     <el-card style=" margin: 10px;margin-top: 20px;;">
        <el-table stripe :data="genfence">
            <el-table-column label="编号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="围栏名称" prop="name"  align="center" ></el-table-column>
            <el-table-column label="围栏坐标点" prop="position"  align="center" ></el-table-column>
            <el-table-column label="创建时间" prop="createTime"  align="center" ></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"  align="center" ></el-table-column>
            <el-table-column label="状态"  align="center" >
                <template #default="scope">
                    <el-switch v-model="scope.row.status" 
                    active-value="1" inactive-value="0" 
                    @change="undateStatus(scope.row.id,scope.row.status)"></el-switch>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" link @click="loadBindVehicle(scope.row.id)">管理车辆</el-button>
                    <el-button type="primary" link>查看围栏</el-button>
                    <el-button type="primary" link @click="deleteGeo(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
     </el-card>

     <el-dialog 
     title="已绑定车辆"
     v-model="bindVehicleDialogVisible"
     style="width: 1200px; padding: 40px;"
     >
        <el-button type="primary"
         @click="showUnbindVehicle()" style="margin: 10px 0;">继续添加</el-button>
        <el-table :data="bindCarForGeofence">
            <el-table-column label="标号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="车辆品牌" prop="brand" align="center"></el-table-column>
            <el-table-column label="车牌号" prop="license" align="center"></el-table-column>
            <el-table-column label="车辆类型" prop="type" align="center"></el-table-column>
            <el-table-column label="操作" align="center" fixed="right" width="200">
                <template #default="scope">
                    <el-button type="primary" link @click="removeBindVehicle(scope.row.id)">移除</el-button>
                </template>
            </el-table-column>
        </el-table>
        <template #footer>
            <el-button type="info" @click="bindVehicleDialogVisible=false">返回到主页面</el-button>
        </template>
     </el-dialog>
     <el-dialog 
     title="可绑定车辆"
     v-model="notBindVehicleDialogVisible"
     style="width: 1200px;"
     >
        <el-table  :data="notbindCarForGeofence" style="margin-top: 20px;">
            <el-table-column label="标号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="车辆品牌" prop="brand" align="center"></el-table-column>
            <el-table-column label="车牌号" prop="license" align="center"></el-table-column>
            <el-table-column label="车辆类型" prop="type" align="center"></el-table-column>
            <el-table-column label="车辆状态" prop="status" align="center"></el-table-column>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" link @click="bindCar(scope.row.id)">绑定</el-button>
                </template>
            </el-table-column>
        </el-table>
        <template #footer>
            <el-button type="info" @click="notBindVehicleDialogVisible=false">返回上一级</el-button>
        </template>
     </el-dialog>

</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { onMounted, ref } from 'vue';
import qs from 'qs';
import router from '@/router';

//这个变量是围栏列表
const genfence =ref([]);
//这个变量是绑定于特定的围栏的车辆
const bindCarForGeofence = ref([]);

//定义变量表示未绑定蔚蓝的车辆
const notbindCarForGeofence = ref([]);

//这个变量是用来显示第二个绑定的对话框
const notBindVehicleDialogVisible = ref(false);

//定义绑定对象获取输入
const searchForm= ref({
    name:'',
    status:''
});

//变量用来显示管理信息条目的弹窗
const bindVehicleDialogVisible = ref(false);

//页面加载的时候会触发该事件
onMounted(()=>{loadGeofence()});

/* 定义全局变量保存当前正在操作得到围栏id */
var geoId ;

/**
 * 搜索框提交方法
 */
function submit() {
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/geofence/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            genfence.value = response.data.data;
            ElMessage.success('查询完成');
        }else {
            ElMessage.error('查询失败！请稍后再试');
        }
    });
};


/**
 * 定义重置方法
 */
function reset() {
    searchForm.value = {};
    // console.log(searchForm.value);
    loadGeofence();
}

/**
 * 定义加载主页面的围栏的函数
 */
function loadGeofence() {
    axios.get(BASE_URL+'/v1/geofence/query')
    .then((response)=>{
        if (response.data.code == 2000) {
            genfence.value = response.data.data;
        } else {
            ElMessage.error('发生未知错误！请重试！');
        }
    });
}


/**
 * 加载当前围栏绑定的车辆
 * @param id 当前围栏id
 */
function loadBindVehicle(geofenceId) {
    geoId = geofenceId;
    bindVehicleDialogVisible.value = true;
    axios.get(BASE_URL+'/v1/vehicle/query?geofenceId='+geofenceId)
    .then((response)=>{
        if (response.data.code == 2000) {
            bindCarForGeofence.value = response.data.data;
            console.log(bindCarForGeofence.value.length);
        }else {
            ElMessage.error('操作失败！请稍后再试');
        }
    });
}

/**
 * 移除当前围栏的绑定车辆
 * @param id 已绑定的车辆id
 */

function removeBindVehicle(id) {
    console.log(id);
    if (confirm('你确定要移除当前车辆吗？')) {
            axios.post(BASE_URL+'/v1/vehicle/unbind/'+id)
            .then((response)=>{
                if (response.data.code == 2000) {
                    ElMessage.success('移除成功');
                    console.log(geofenceId);    
                    loadBindVehicle(geofenceId);
                }else {
                    ElMessage.error('操作失败！请稍候重试！');
                }
            });
    }
};

/**
 * 显示所有未绑定的车辆列表
 */
function showUnbindVehicle() {
    notBindVehicleDialogVisible.value = true;
    axios.get(BASE_URL+'/v1/vehicle/query?geofenceBindStatus=0')
    .then((response)=>{
        if (response.data.code == 2000) {
            notbindCarForGeofence.value = response.data.data;
        }
        else {
            ElMessage.error('操作失败，请稍后再试');
        }
    });
}

/**
 * 绑定数据到当前围栏
 * @param id 当前行的汽车id号码
 */
function bindCar(id) {
    console.log(id,geoId);
    axios.post(BASE_URL+'/v1/vehicle/bind/'+geoId+'/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('绑定成功！');
            loadBindVehicle(geoId);
            showUnbindVehicle();
        }else {
            ElMessage.error('绑定失败！请稍后再试');
        }
    });
}

/**
 * 更新围栏状态
 * @param id 当前围栏id
 * @param status 监听到的围栏状态
 */
function undateStatus(id,status) {
    axios.post(BASE_URL+'/v1/geofence/update/'+id+'/'+status)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('状态修改成功！');
            loadGeofence();
        }
        else {
            ElMessage.error('操作错误！请稍候重试');
        }
    });
}

/**
 * 删除围栏的状态
 * @param id 当前围栏id
 */
function deleteGeo(id) {
    console.log(id);
    axios.post(BASE_URL+'/v1/geofence/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('删除成功！');
            loadGeofence();
        }
        else {
            ElMessage.error(response.data.message);
        }
    });
}
</script>

<style>
#head {
    height: 7vh;
    padding: 20px 20px;
    background-color: #fff;
    display: flex;
    justify-content: space-between;
    align-items: center;
    margin-top: 5px;
}

</style>
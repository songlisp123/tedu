<template>
    <div>
        <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">申请单列表</span>
            <el-button type="primary" style="float:right;margin-top:13px;">申请用车</el-button>
        </div>
        <div class="vehicleSearchCard">
            <div>
                <label for="brand">品牌：</label>
                <input type="text" name="brand" @keydown.enter="loadVehicle()"
                id="brand" placeholder="请输入品牌"
                v-model="searchForm.brand">
            </div>

            <div>
                <label for="license">车牌号：</label>
                <input type="text" name="license"  @keydown.enter="loadVehicle()"
                id="license" placeholder="请输入车牌号"
                v-model="searchForm.license">
            </div>

            <div class="status">
                <label for="status">状态：</label>
                <div class="select-wrapper" >
                    <select id="status" v-model="searchForm.status" @change="loadVehicle()"> 
                        <option value="0">占用</option>
                        <option value="1">空闲</option>
                        <option value="2">维修</option>
                        <option value="3">租赁</option>
                    </select> 
                </div>

            </div>

            <div>
                <el-button type="primary" @click="submit()">提交</el-button>
                <el-button type="info" @click="reset()">重置</el-button>
            </div>
            <el-button type="warning" style="padding-right: 20px;"
                @click="newCar()">新建车辆</el-button>
        </div>
        <div class="item-wrapper">
            <div class="item p3" v-for="vehicle in vehicles">
                <div  class="touxiang" >
                    <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
                </div>
                <p class="text">品牌:{{ vehicle.brand }}</p>
                <p class="tag">车牌号:{{ vehicle.license }}</p>
                <p class="email">模型:{{ vehicle.model }}</p>
                <!-- <p class="email">车辆校验码:{{ vehicle.code }}</p> -->
                <!-- <p class="email">类型:{{ vehicle.type }}</p> -->
                <!-- <p class="email">价格:{{ vehicle.price }}</p> -->
                <!-- <p class="email">上牌时间:{{ vehicle.regTime }}</p> -->
                <!-- <p class="email">购买时间:{{ vehicle.buyTime }}</p> -->
                <!-- <p class="email">电池类型:{{ vehicle.batteryType }}</p> -->
                <p class="email">状态:{{ vehicle.status }}</p>
                <p v-if="updateStatus">
                    汽车状态:
                    <select name="vehicleStatus" id="vehicleStatus" v-model="vehicle.status"
                    @change="updateVehicleStatus()"
                    >
                        <option value="0" >禁用</option>
                        <option value="1">启用</option>
                        <option value="2">维修</option>
                        <option value="3">租赁</option>
                    </select>
                </p>
                <p id="buttons" v-if="currentUser.level === '经理'">
                    <el-button type="info" @click="edit(vehicle.id)">编辑</el-button>
                    <el-button type="primary" @click="update(vehicle.id)">修改</el-button>
                    <el-button type="warning"
                    
                    @click="deleteVehicle(vehicle.id)">删除</el-button>                    
                </p>
            </div>
        </div>
    </div>
    <el-dialog 
    v-model="dialogVisible" 
    :title="dialogTitle"
    style="width: 600px;padding: 40px;">
        <fieldset>
            <div class="vehicleCreateForm">
                <div>
                    <label for="brand">品牌：</label> <br>
                    <input type="text" placeholder="请输入内容：" 
                    id="brand" v-model="saveForm.brand">
                </div>
                <div>
                    <label for="license">车牌号：</label> <br>
                    <input type="text" v-model="saveForm.license"
                    placeholder="请输入内容：" id="brand">
                </div>
            </div>
            <div class="vehicleCreateForm">
                <div>
                    <label for="model">型号：</label> <br>
                    <input type="text"
                    v-model="saveForm.model"
                    placeholder="请输入内容：" id="brand">
                </div>
                <div>
                    <label for="code">车辆识别码：</label> <br>
                    <input type="text" 
                    v-model="saveForm.code"
                    placeholder="请输入内容：" id="brand">
                </div>
            </div>
            <div class="vehicleCreateForm">
                <div>
                    <label for="displacement">车辆排量:</label> <br>
                    <select name="displacement" id="displacement" 
                    v-model="saveForm.displacement">
                        <option value="1">1.0T</option>
                        <option value="2">1.2T</option>
                        <option value="3">1.6T</option>
                    </select>
                </div>
                <div>
                    <label for="type">车辆类型:</label> <br>
                    <select name="type" id="type" v-model="saveForm.type" >
                        <option value="10">轿车</option>
                        <option value="20">货车</option>
                        <option value="30">客车</option>
                    </select>
                </div>
            </div>
            <div class="vehicleCreateForm">
                <div>
                    <label for="color">车身颜色:</label> <br>
                    <select id="color"  v-model="saveForm.color">
                        <option value="10">红色</option>
                        <option value="20">蓝色</option>
                        <option value="30">黑色</option>
                        <option value="40">高级灰色</option>
                        <option value="50">白色</option>
                        <option value="60">银白色</option>
                        <option value="70">粉色</option>
                        <option value="80">绿色</option>
                        <option value="90">沙漠迷彩色</option>
                        <option value="100">森林迷彩色</option>
                        <option value="110">海洋迷彩色</option>
                    </select>
                </div>
                <div>
                    <label for="license">里程数:</label> <br>
                    <input type="text" 
                    v-model="saveForm.kilometers"
                    placeholder="请输入内容：" id="license">
                </div>
            </div>
            <div class="vehicleCreateForm">
                <div>
                    <label for="buyTime">购买时间:</label> <br>
                    <input type="date" id="buyTime" v-model="saveForm.buyTime">
                </div>
                <div>
                    <label for="regTime">上牌时间:</label> <br>
                    <input type="date" id="regTime" v-model="saveForm.regTime">
                </div>
            </div>
            <div class="vehicleCreateForm">
                <div>
                    <label for="brand">购买价格:</label> <br>
                    <input type="text" 
                    v-model="saveForm.price"
                    placeholder="请输入内容：" id="brand">
                </div>
                <div>
                    <label for="license">电池类型:</label> <br>
                    <select id="battery" v-model="saveForm.batteryType">
                        <option value="10">铅酸锂电池</option>
                        <option value="20">锂电池</option>
                    </select>
                </div>
            </div>
            <div class="buttons">
                <el-button type="info" @click="close()">取消</el-button>
                <el-button type="primary" @click="save()">确认</el-button>
            </div>
        </fieldset>
    </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import qs from 'qs';
import { ElMessage } from 'element-plus';

//定义变量存放用户数据
const searchForm = ref({
    brand:'',
    license:'',
    status:''
});

//获取当前用户
const currentUser = ref(window.getUser());

//定义变量存放表单内容
const saveForm = ref({
    id:'',
    brand:'',
    license:'',
    model:'',
    code:'',
    displacement:'',
    type:'',
    color:'',
    kilometers:'',
    buyTime:'',
    regTime:'',
    price:'',
    batteryType:''
});

const dialogVisible = ref(false);

//定义变量存昂车辆
const vehicles = ref([]);

//定义变量存放标题文本
const dialogTitle = ref('新建车辆');

//定义更新状态的布尔值
const updateStatus = ref(false);

function reset() {
    searchForm.value = {};
    loadVehicle();
}


function submit() {
    console.log(searchForm.value);
    loadVehicle();
}

onMounted(()=>{loadVehicle()});

function loadVehicle() {
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/vehicle/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('检索成功！');
            vehicles.value = response.data.data;
        }
        else {
            ElMessage.error('检索失败！');
        }
    });
}

function save() {
    console.log(saveForm.value);
    axios.post(BASE_URL+'/v1/vehicle/save',saveForm.value)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('车辆新建成功！');
            saveForm.value = {};
            dialogVisible.value =false;
            loadVehicle();
        }
        else {
            ElMessage.error('插入失败！请稍后重试');
        }
    });
}


function close() {
    dialogVisible.value = false;
    saveForm.value = {};
}

function edit(id) {
    dialogTitle.value = '编辑车辆';
    dialogVisible.value = true
    axios.get(BASE_URL+'/v1/vehicle/query?id='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            saveForm.value = response.data.data[0];
            typeformatter();
            batteryformatter();
            colorFormatter();
        }
        else {
            ElMessage.error('操作失败，请重试！');
        }
    });
}

function typeformatter() {
    if (saveForm.value.type === '轿车') {
        saveForm.value.type = '10';
    }
    else if(saveForm.value.type === '货车') {
        saveForm.value.type = '20';
    }
    else {
        saveForm.value.type = '10';
    }
}

function batteryformatter() {
    if (saveForm.value.batteryType === '铅酸锂电池') {
        saveForm.value.batteryType = '10';
    }
    else {
        saveForm.value.batteryType = '20';
    }
}

function colorFormatter() {
    if (saveForm.value.color === '红色') {
        saveForm.value.color = '10';
    }
    else if (saveForm.value.color === '蓝色') {
        saveForm.value.color = '20';
    }
    else if (saveForm.value.color === '黑色') {
        saveForm.value.color = '30';
    }
     else if (saveForm.value.color === '高级灰色') {
        saveForm.value.color = '40';
    }
     else if (saveForm.value.color === '白色') {
        saveForm.value.color = '50';
    }
    else if (saveForm.value.color === '银白色') {
        saveForm.value.color = '60';
    }
    else if (saveForm.value.color === '粉色') {
        saveForm.value.color = '70';
    }
    else if (saveForm.value.color === '绿色') {
        saveForm.value.color = '80';
    }
    else if (saveForm.value.color === '沙漠迷彩色') {
        saveForm.value.color = '90';
    }
    else if (saveForm.value.color === '森林迷彩色') {
        saveForm.value.color = '100';
    }
    else if (saveForm.value.color === '海洋迷彩色') {
        saveForm.value.color = '110';
    }

    
}

function newCar() {
    dialogVisible.value = true;
    saveForm.value = {};
}

function update(id) {
    updateStatus.value = true;
}


function deleteVehicle(id) {
    axios.post(BASE_URL+'/v1/vehicle/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('删除成功！');
            loadVehicle();
        }
        else {
            ElMessage.error('删除失败!');
        }
    });
}

function updateVehicleStatus() {};


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

#brand,#license {
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

.item {
    background-color: #666;
    border-radius: 10px;
}


.touxiang {
    margin: 0 auto;
    width: 100px;
    text-align: center;

}

.item-wrapper {
    display: grid;
    margin-left: 10%;
    margin-right: 10%;
    grid-template-columns: auto auto auto auto;
    gap: 20px;
}

#buttons {
    background-color: #666;
}

p {
    text-align: center;
}

.vehicleCreateForm {
    display: flex;
    justify-content: space-between;
    margin-bottom: 30px;
    overflow: hidden;
}

fieldset {
    border: none;
}

.vehicleCreateForm label {
    color: #666;
}

.vehicleCreateForm select {
    color: #666;
    width: 200px;
    border: none;
    border-bottom: 1px solid #666;
}

.vehicleCreateForm select option {
    color: #666;
}

.vehicleCreateForm input[type="date"] {
    border: none;
    color: #666;
    width: 200px;
    border-bottom: 1px solid #666;
}


.buttons {
    display: flex;
    justify-content: center;
    gap: 20px;
}

#vehicleStatus {
    background-color: #656565;
    border: none;
}

</style>
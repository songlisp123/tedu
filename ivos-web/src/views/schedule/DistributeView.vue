<template>
    <div style="height: 6vh;background-color:#fff;padding:10px 20px;">
    <span style="line-height:60px;font-size:20px;">车辆分配列表</span>
  </div>
  <!-- 搜索表单 -->
  <el-card style="margin: 20px;">
    <el-form style="padding-top:10px;">
      <el-row :gutter="30">
        <el-col :span="10">
          <el-form-item label="工单申请人">
            <el-input placeholder="请输入内容" v-model="searchForm.username"
            @keydown.enter.prevent="loadApplication()" 
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item>
            <el-button @click="reset()">重置</el-button>
            <el-button type="primary" 
            @click="loadApplication()">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>

  <!-- 工单申请表单 -->
  <el-card style="margin:20px">
    <el-radio-group style="margin-bottom: 15px;" v-model="type"
     @change="loadApplication()">
      <el-radio-button value="50" size="large">待分配</el-radio-button>
      <el-radio-button value="60" size="large">已分配</el-radio-button>
    </el-radio-group>
    <el-table :data="applicationArray">
      <el-table-column type="index" width="80" align="center" prop="id" label="编号"></el-table-column>
      <el-table-column align="center" prop="username" label="申请人"></el-table-column>
      <el-table-column align="center" prop="createTime" label="申请时间"></el-table-column>
      <el-table-column align="center" prop="departureAddr" label="出发地"></el-table-column>
      <el-table-column align="center" prop="destinationAddr" label="目的地"></el-table-column>
      <el-table-column align="center" prop="reason" label="用车原因"></el-table-column>
      <el-table-column align="center" prop="startTime" label="使用开始时间"></el-table-column>
      <el-table-column align="center" prop="endTime" label="使用结束时间"></el-table-column>
      <el-table-column label="操作" width="120" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" link v-if="type== 50"
          @click="loadGeofence(scope.row.id)"
          >分配用车</el-button>
          <el-button type="primary" size="small" link  v-if="type== 60"
          @click="back(scope.row)"
          >还车</el-button>
        </template>
      </el-table-column>
    </el-table>

  </el-card>

   <!-- 围栏车辆概览弹窗 -->
  <el-dialog title="围栏列表" v-model="dialogVisible" style="padding:40px;">
    <el-form>
      <el-row :gutter="10">
        <el-col :span="8">
          <el-form-item label="围栏名称:">
            <el-input placeholder="请输入" v-model="geofenceSearchForm.name"
            @keydown.enter.prevent="loadGeofence(id)"
            ></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="8">
          <el-form-item label="围栏状态">
            <el-select placeholder="请选择" 
            v-model="geofenceSearchForm.status" @change="loadGeofence(id)">
              <el-option label="启用" value="1"/>
              <el-option label="禁用" value="0"/>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="4"></el-col>
        <el-col :span="4">
          <el-form-item>
            <el-button-group>
              <el-button @click="resetGeofenceSearchForm()">重置</el-button>
              <el-button type="primary"@click=loadGeofence(id)>查询</el-button>
            </el-button-group>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <el-table style="margin:20px 0" :data="geofenceArray">
      <el-table-column label="编号" type="index" width="80" align="center"></el-table-column>
      <el-table-column label="围栏名称" prop="name" width="200" align="center"></el-table-column>
      <el-table-column label="车辆总数" prop="totalNum" width="200" align="center"></el-table-column>
      <el-table-column label="可用车辆" prop="availableNum" align="center"></el-table-column>
      <el-table-column label="围栏状态" prop="status" align="center"></el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template #default="scope">
          <el-button type="primary" link 
          @click="showVehicle(scope.row.id)">查看车辆</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>


  <!-- 分配车辆弹窗 -->
  <el-dialog title="分配车辆" style="padding:40px;" v-model="vehicleDialogVisible">
    <el-table style="margin:20px 0" :data="vehicleArray">
      <el-table-column label="编号" type="index" width="80" align="center"></el-table-column>
      <el-table-column label="车辆品牌" prop="brand" width="200" align="center"></el-table-column>
      <el-table-column label="车牌号" prop="license" width="200" align="center"></el-table-column>
      <el-table-column label="车辆类型" prop="type" align="center"></el-table-column>
      <el-table-column label="车辆状态" prop="status" align="center"></el-table-column>
      <el-table-column label="操作" width="100" align="center">
        <template #default="scope">
          <el-button type="primary" link
          @click="allocate(scope.row.id)"
          v-if="scope.row.status==1" >分配</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';


//定义常量保存申请单状态（50已审批，等待分配车，60已分配车，等待还车）
const type = ref('50');

//定义变量保存用户提交的数据
const searchForm = ref({
    username:''
});

//定义变量接受申请单 列表
const applicationArray = ref([]);

//定义围栏弹窗是否出现
const  dialogVisible = ref(false);

//定义汽车弹窗是否出现
const vehicleDialogVisible = ref(false);

//定义变量保存围栏搜索表单数据
const geofenceSearchForm = ref({
    name:'',
    status:''
});

//定义数组保存围栏信息
const geofenceArray = ref([]);

//定义变量保存当前审批单id
var applicationId;

//定义数组保存汽车列表
const vehicleArray = ref([]);


onMounted(()=>{loadApplication()});


function loadApplication() {
    searchForm.value.status = type.value;
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/app/query?'+data)
    .then((response)=>{
        if (response.data.code === 2000) {
            ElMessage.success('查询成功！');
            applicationArray.value = response.data.data;
        }

        else {
            ElMessage.error('查询失败');
        }
    });
}

function reset() {
    searchForm.value.username ='';
    console.log(searchForm.value);
    loadApplication();
}

function loadGeofence(id) {
    console.log(id);
    dialogVisible.value = true;
    applicationId = id;
    console.log(geofenceSearchForm.value);
    let data = qs.stringify(geofenceSearchForm.value);
    axios.get(BASE_URL+'/v1/geofence/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            geofenceArray.value = response.data.data;
        }
        else {
            ElMessage.error('查询失败');
        }
    });
}

function resetGeofenceSearchForm() {
    geofenceSearchForm.value = {};
    loadGeofence(applicationId);
}

function showVehicle(geofenceId) {
    vehicleDialogVisible.value = true;
    console.log(geofenceId);//当前围栏id
    axios.get(BASE_URL+'/v1/vehicle/query?geofenceId='+geofenceId)
    .then((response)=>{
        if (response.data.code == 2000) {
            vehicleArray.value = response.data.data;
        }
        else {
            ElMessage.error('闪避！');
        }
    });
};

function allocate(id) {
    console.log('汽车id %d',id);//汽车id
    console.log('申请单id, %d',applicationId);
    axios.post(BASE_URL+'/v1/app/allocate/'+applicationId+'/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            vehicleDialogVisible.value = false;
            dialogVisible.value = false;
            loadApplication();
            ElMessage.success('分配成功！');
        }
        else {
            ElMessage.error('错误！');
        }
    });
}


function back(obj) {
    console.log(obj);
    axios.post(BASE_URL+'/v1/app/back/'+obj.id+'/'+obj.vehicleId)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('还车成功！');
            loadApplication();
        }
        else {
            ElMessage.error('还车失败！');
        }
    });
}
</script>

<style></style>
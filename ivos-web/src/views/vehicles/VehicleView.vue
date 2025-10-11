<template>
    
    <div style="height: 6vh;background-color: #fff; padding: 10px 20px;overflow: hidden;">
        <p style="position: relative; margin: 0; float: left; margin-top: 25px;">车辆管理</p>
        <el-button type="primary" style="float: right; margin-right: 20px;margin-top: 20px;" @click="beforeAndVehicle">新增车辆</el-button>
    </div>

    <!-- 搜索卡片 -->
    <el-card style="margin: 10px; height: 70px;box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.35);">
        <el-form :inline="true">
            <el-form-item label="车辆品牌:">
                <el-input placeholder="输入车辆品牌" v-model="queryPara.brand" @keydown.enter="submit()"></el-input>
            </el-form-item>
            <el-form-item label="车牌号:">
                <el-input placeholder="输入车牌号" v-model="queryPara.license" @keydown.enter="submit()"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button @click="reset()">
                    取消
                </el-button>
                <el-button type="info" @click="reset()">
                    重置
                </el-button>
                <el-button type="primary" @click="submit()">
                    确定
                </el-button>
            </el-form-item>
        </el-form>
    </el-card>
    <el-card style="margin: 10px; margin-top: 20px; box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.35);">
        <el-table :data="vehicleArray">
            <el-table-column label="编号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="车辆品牌" prop="brand" align="center" ></el-table-column>
            <el-table-column label="车牌号" prop="license" align="center" ></el-table-column>
            <el-table-column label="车辆识别码" prop="code" align="center" ></el-table-column>
            <el-table-column label="车辆类型" prop="type" align="center" :formatter="vehicleTypeFormatter"></el-table-column>
            <el-table-column label="购买价格" prop="price" align="center" ></el-table-column>
            <el-table-column label="上牌时间" prop="regTime" align="center" ></el-table-column>
            <el-table-column label="电池类型" prop="batteryType" align="center" :formatter="batteryTypeFormatter"></el-table-column>
            <el-table-column label="车辆状态" prop="status" align="center" :formatter="vehicleStatusFormatter"></el-table-column>
            <el-table-column label="操作"align="center" >
                <template #default="scope">
                    <el-button type="primary" link @click="edit(scope.row.id)">编辑</el-button>
                    <el-button type="primary" link @click="deleteVehicle(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
    <el-dialog
    v-model="dialogVisible"
    :title="diglogTitle"
    style="padding: 20px;width: 1000px;"
    
    >
        <el-form  label-position="top">
            <el-row :gutter="15">
                <el-col :span="12">
                    <el-form-item label="车辆品牌">
                        <el-input placeholder="请输入车辆品牌" v-model="savaPara.brand"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="车辆牌号">
                        <el-input placeholder="请输入车牌号" v-model="savaPara.license"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="15">
                <el-col :span="12">
                    <el-form-item label="车辆型号">
                        <el-input placeholder="请输入车辆型号" v-model="savaPara.model"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="车辆识别码">
                        <el-input placeholder="请输入车辆识别码" v-model="savaPara.code"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="15">
                <el-col :span="12">
                    <el-form-item label="车辆排放量">
                        <el-select v-model="savaPara.displacement">
                            <el-option label="1.6T" value="1"></el-option>
                            <el-option label="1T" value="2"></el-option>
                            <el-option label="1.8T" value="3"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="车辆类型">
                        <el-select v-model="savaPara.type">
                            <el-option label="轿车" value="10"></el-option>
                            <el-option label="客车" value="20"></el-option>
                            <el-option label="货车" value="30"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="15">
                <el-col :span="12">
                    <el-form-item label="车辆颜色">
                        <el-select v-model="savaPara.color">
                            <el-option label="黄色" value="10"></el-option>
                            <el-option label="绿色" value="20"></el-option>
                            <el-option label="蓝色" value="30"></el-option>
                        </el-select>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="里程数">
                        <el-input placeholder="请输入里程数" v-model="savaPara.kilometers"></el-input>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="30">
                <el-col :span="12">
                    <el-form-item label="购车时间">
                        <el-date-picker type="date" format="YYYY-MM-DD" value-format="YYYY-MM-DD" v-model="savaPara.buyTime" />
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="上牌时间">
                        <el-date-picker type="date" format="YYYY-MM-DD" value-format="YYYY-MM-DD" v-model="savaPara.regTime"/>
                    </el-form-item>
                </el-col>
            </el-row>
            <el-row :gutter="15">
                <el-col :span="12">
                    <el-form-item label="价格">
                        <el-input placeholder="购买价格" v-model="savaPara.price"></el-input>
                    </el-form-item>
                </el-col>
                <el-col :span="12">
                    <el-form-item label="电池类型">
                        <el-select v-model="savaPara.batteryType">
                            <el-option label="铅酸锂电池" value="10"></el-option>
                            <el-option label="镍氢电池" value="20"></el-option>
                            <el-option label="钠硫电池" value="30"></el-option>
                            <el-option label="二次锂电池" value="40"></el-option>
                            <el-option label="空气电池" value="50"></el-option>
                            <el-option label="三元锂电池" value="60"></el-option>
                            <el-option label="碱性燃料电池" value="70"></el-option>

                        </el-select>
                    </el-form-item>
                </el-col>
            </el-row>
        </el-form>
        <template #footer>
            <el-button @click="handlerClose()">取消</el-button>
            <el-button type="primary" @click="savaVehicle()">提交</el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';

const vehicleArray = ref([
    // {brand:'小米',license:'空车',code:'2015',type:'纯电',price:'12500',regTime:'20225',batteryType:'锂电池',status:'1'},
    // {brand:'小米',license:'空车',code:'2015',type:'纯电',price:'12500',regTime:'20225',batteryType:'锂电池',status:'0'}
]);

function loadVehicle() {
    axios.get(BASE_URL+'/v1/vehicle/query')
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('查询成功！');
            vehicleArray.value = response.data.data;
        } else {
            ElMessage.error('查询失败！');
        }
    });
};

onMounted(()=>{loadVehicle()});

//定义变量接收数据
const queryPara = ref({
    brand:'',
    license:''
});

function submit() {
   let data = qs.stringify(queryPara.value);
   axios.get(BASE_URL+'/v1/vehicle/query?'+data)
   .then((response)=>{
    if (response.data.code == 2000) {
        vehicleArray.value = response.data.data;
        if (vehicleArray.value.length == 0) {
            ElMessage.info('暂无更多数据');
        }else {
            ElMessage.success('查询成功！');
        }
    } else {
        ElMessage.error('发生错误！请稍后重试！')
    }
   });
};

function reset() {
    queryPara.value = {};
}

const dialogVisible = ref(false);

function showDialog() {
    dialogVisible.value = true;
}

//定义显示文字转换:
const vehicleTypeFormatter =(row,column,cellValue,index)=>{
    // console.log(row,column);
  if(cellValue == 10){
    cellValue = '轿车';
  }else if(cellValue == 20){
    cellValue = '客车';
  }else if(cellValue == 30){
    cellValue = '货车';
  }else if(cellValue == 40) {
    cellValue = '挂车';
  }
  return cellValue;
}

//对车辆状态显示内容进行文字转换
const vehicleStatusFormatter = (row,column,cellValue,index)=>{
  if(cellValue == 1){
    cellValue = '空闲';
  }else if(cellValue == 2){
    cellValue = '占用';
  }
  return cellValue;
}

//对电池类型显示内容进行文字转换
const batteryTypeFormatter = (row,column,cellValue,index)=>{
  if(cellValue == 10){
    cellValue = '铅酸蓄电池';
  }else if(cellValue == 20){
    cellValue = '镍氢电池';
  }else if(cellValue == 30){
    cellValue = '钠硫电池';
  }else if(cellValue == 40){
    cellValue = '二次锂电池';
  }else if(cellValue == 50){
    cellValue = '空气电池';
  }else if(cellValue == 60){
    cellValue = '三元锂电池';
  }else if(cellValue == 70){
    cellValue = '碱性燃料电池';
  }
  return cellValue;
}

const savaPara = ref({
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

function savaVehicle() {
    if(confirm('你确定提交此表单吗？')) {
        // console.log(savaPara.value);
        axios.post(BASE_URL+'/v1/vehicle/save',savaPara.value)
        .then((response)=>{
            if (response.data.code == 2000) {
                ElMessage.success('添加车辆成功！');
                savaPara.value = {};
                dialogVisible.value = false;
                loadVehicle();
            }else {
                ElMessage.error('添加失败!请稍后重试！');
            }
        });
    }
};

function handlerClose() {
    savaPara.value = {};
    dialogVisible.value = false;
}

const diglogTitle = ref('新建车辆');

function edit(id) {
    diglogTitle.value = '编辑车辆';
    dialogVisible.value = true;
    axios.get(BASE_URL+'/v1/vehicle/query?id='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            savaPara.value = response.data.data[0];
        }
    });
}

function beforeAndVehicle() {
    diglogTitle.value = '新建车辆';
    savaPara.value = {};
    dialogVisible.value = true;
}

function deleteVehicle(id) {
    if (confirm('你是否想要删除此车辆？')) {
        axios.post(BASE_URL+'/v1/vehicle/delete/'+id)
        .then((response)=>{
            if (response.data.code == 2000) {
                ElMessage.success('删除成功！');
                loadVehicle();
            }else {
                ElMessage.error('删除失败！');
            }
        });
    }
}



</script>

<style></style>
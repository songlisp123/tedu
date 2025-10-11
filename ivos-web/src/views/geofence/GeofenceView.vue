<template>
    <!-- <h1>xxxx电子围栏02xxxxx</h1> -->
     <div id="head">
        <p style="font-size: 15px; font-weight: bold;margin-top: 15px;">围栏管理</p>
        <el-button type="primary">新建围栏</el-button>
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
        <el-table :data="genfence">
            <el-table-column label="编号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="围栏名称" prop="name"  align="center" ></el-table-column>
            <el-table-column label="围栏坐标点" prop="position"  align="center" ></el-table-column>
            <el-table-column label="创建时间" prop="createTime"  align="center" ></el-table-column>
            <el-table-column label="更新时间" prop="updateTime"  align="center" ></el-table-column>
            <el-table-column label="状态"  align="center" >
                <template #default="scope">
                    <el-switch v-model="scope.row.status" active-value="1" inactive-value="0"></el-switch>
                </template>
            </el-table-column>
            <el-table-column label="操作" align="center">
                <template #default="scope">
                    <el-button type="primary" link>管理车辆</el-button>
                    <el-button type="primary" link>查看围栏</el-button>
                    <el-button type="primary" link>删除</el-button>
                </template>
            </el-table-column>
            
        </el-table>
     </el-card>

</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { onMounted, ref } from 'vue';
import qs from 'qs';

const genfence =ref([]);

//定义绑定对象获取输入
const searchForm= ref({
    name:'',
    status:''
});

//定义提交方法
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

onMounted(()=>{loadGeofence()});
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
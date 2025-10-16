<template>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">围栏列表</span>
            <el-button type="primary" style="float:right;margin-top:13px;">新增围栏</el-button>
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
    <el-card style="margin: 10px 5%; padding: 10px 20px; box-shadow: rgba(0, 0, 0, 0.2);">
        <el-table :data="geofenceArray">
            <el-table-column label="index" type="index" width="80" align="center"></el-table-column>
            <el-table-column label="名称" prop="name"  align="center"></el-table-column>
            <el-table-column label="位置" prop="position" align="center"></el-table-column>
            <el-table-column label="创建时间" prop="createTime" align="center"></el-table-column>
            <el-table-column label="状态" prop="status" align="center">
                <el-switcher :inactive="0" :active="1"></el-switcher>
            </el-table-column>
            <el-table-column label="操作">
                <template #default="scope">
                    <el-button type="primary" link size="small">管理车辆</el-button>
                    <el-button type="primary" link size="small">查看围栏</el-button>
                    <el-button type="primary" link size="small">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';



//定义变量存放搜索表单
const searchForm = ref({
    name:'',
    status:''
});

onMounted(()=>{loadGeo()});
//定义数组保存围栏数据
const geofenceArray = ref([]);



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

</style>
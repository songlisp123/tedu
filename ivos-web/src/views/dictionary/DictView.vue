<template>
    <div style="height: 6vh;background-color: #fff; padding: 10px 20px;overflow: hidden;">
        <p style="position: relative; margin: 0; float: left; margin-top: 25px;">字典管理</p>
        <el-button type="primary" style="float: right; 
        margin-right: 20px;margin-top: 20px;" @click="newDict()"
        >新增字典</el-button>
    </div>
    <!-- 搜索卡片 -->
    <el-card style="margin: 10px; height: 70px;padding-left: 15px;box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.35);">
        <el-form :inline="true">
            <el-form-item label="字典名称:">
                <el-input placeholder="请输入" v-model="searchForm.name"
                 @keydown.enter="loadDict()"></el-input>
            </el-form-item>
            <el-form-item label="字典编码:">
                <el-input placeholder="请输入" v-model="searchForm.code"
                @keydown.enter="loadDict()"></el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="reset()">
                    重置
                </el-button>
                <el-button type="primary" @click="loadDict()">
                    确定
                </el-button>
            </el-form-item>
        </el-form>
    </el-card>
    <el-card style="margin: 10px; box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.35);">
        <el-table :data="dictArray">
            <el-table-column label="编号" type="index" width="80" align="center"></el-table-column>
            <el-table-column label="字典名称"  align="center" prop="name"></el-table-column>
            <el-table-column label="字典编码"  align="center" >
                <template #default="scope">
                    <el-button type="primary" link size="small"
                     @click="showDictOption(scope.row.id)">{{ scope.row.code }}</el-button>
                </template>
            </el-table-column>
            <el-table-column label="备注信息"  align="center" prop="remark"></el-table-column>
            <el-table-column label="创建时间"  align="center" prop="createTime"></el-table-column>
            <el-table-column label="操作"  align="center">
                <template #default="scope">
                    <el-button type="primary" link @click="edit(scope.row.id)">编辑</el-button>
                    <el-button type="primary" link @click="deleteDict(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>
    <el-dialog :title="dialogTitle"
    v-model="dialogVisible"
    style="padding: 30px; width: 800px;"
    >
        <el-form label-position="top">
            <el-form-item label="字典名称：*">
                <el-input placeholeder="亲输入" v-model="saveForm.name"></el-input>
            </el-form-item>
            <el-form-item label="字典编码：*">
                <el-input placeholeder="请输入" v-model="saveForm.code"></el-input>
            </el-form-item>
            <el-form-item label="备注信息：*">
                <el-input placeholeder="请输入" v-model="saveForm.remark"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button type="info" @click="close()">取消</el-button>
            <el-button type="primary" @click="submit()">确定</el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import qs from 'qs';
import { ElMessage } from 'element-plus';
import router from '@/router';

//定义变量存放数据
const dictArray = ref([]);

//定义变量存放用户搜索数据
const searchForm = ref({
    name:'',
    code:''
});

//定义变量保存保存表单数据
const saveForm = ref({
    name:'',
    code:'',
    remark:''
});

var dictId;

//定义变量控制弹窗出现与否
const dialogVisible = ref(false);

//定义变量控制弹窗的标题
const dialogTitle = ref('新建字典');


onMounted(()=>{loadDict()});

function loadDict() {
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/dict/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('查询成功！');
            dictArray.value = response.data.data;
        }
        else {
            ElMessage.error('查询失败，请重试！');
        }
    });
}

function reset() {
    searchForm.value = {};
    loadDict();
}

function edit(id) {
    console.log(id);
    dictId = id;
    dialogTitle.value = '编辑车辆';
    dialogVisible.value = true;
    axios.get(BASE_URL+'/v1/dict/query?id='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            saveForm.value = response.data.data[0];
        }
        else {
            ElMessage.error('查询失败');
        }
    });
}

function submit() {
    console.log(saveForm.value);
    let data = qs.stringify(saveForm.value);
    axios.post(BASE_URL+'/v1/dict/save?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('插入成功！');
            loadDict();
            dialogVisible.value = false;
            dictId = '';
        }
        else {
            ElMessage.error('操作失败');
        }
    });
}

function close() {
    saveForm.value = {};
    dialogVisible.value = false;
}

/**
 * 定义点击新增按钮时调用的方法
 */
function newDict() {
    dialogTitle.value = '新增字典';
    dialogVisible.value = true;
}
/***
 * 定义删除字典的方法
 */
function deleteDict(id) {
    axios.post(BASE_URL+'/v1/dict/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('删除成功！');
            loadDict();
        }

        else {
            ElMessage.error('删除失败');
        }
    });
}

function showDictOption(id) {
    //携带当前字典id来跳转到字典页面
    router.push('dictOption?id='+id);
};
</script>

<style></style>
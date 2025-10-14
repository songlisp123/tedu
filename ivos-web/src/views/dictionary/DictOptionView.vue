<template>
    <!-- 字典项界面 -->
    <div style="height: 6vh; background-color: #fff;padding: 10px 20px;">
        <span style="line-height: 60px; font-size: 20px;">字典项管理</span>
        <el-button type="primary" 
         style="float: right; margin-top: 13px;" @click="router.go(-1)">返回</el-button>
    </div>

    <!-- 搜索表单 -->
    <el-card 
    style="margin: 10px; height: 70px;padding-left: 15px;
    box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.35);">
        <el-form :inline="true">
            <el-form-item label="字典项名称:">
                <el-input placeholder="请输入" @keydown.enter="loadDictOption()"
                v-model="searchForm.label">
                </el-input>
            </el-form-item>
            <el-form-item>
                <el-button type="info" @click="reset()">
                    重置
                </el-button>
                <el-button type="primary" @click="loadDictOption()">
                    确定
                </el-button>
            </el-form-item>
        </el-form>
    </el-card>
    <el-card style="padding: 20px; margin: 10px;">
        <div style="margin:10px 0px 40px 10px;">
            字典项列表
            <el-button type="primary" @click="dialogVisible = true" style="float: 
            right;margin-top: -3px;">新增字典项</el-button>
        </div>
        <el-table :data="dictOptionArr">
            <el-table-column label="编号" type="index" width="80" align="center"></el-table-column>
            <el-table-column label="字典项名称" prop="label"  align="center"></el-table-column>
            <el-table-column label="字典项值"  prop="value" align="center"></el-table-column>
            <el-table-column label="字典项排序" prop="sort" align="center"></el-table-column>
            <el-table-column label="备注信息" prop="remark" align="center"></el-table-column>
            <el-table-column label="创建时间" prop="createTime" align="center"></el-table-column>
            <el-table-column label="操作"  align="center">
                <template #default="scope">
                    <el-button type="primary" link @click="edit(scope.row.id)">编辑</el-button>
                    <el-button type="primary" link @click="deleteOpt(scope.row.id)">删除</el-button>
                </template>
            </el-table-column>
        </el-table>
    </el-card>

    <el-dialog :title="dialogTitle"
    v-model="dialogVisible"
    style="width: 800px; padding: 40px;"
    >
        <el-form label-position="top" label-width="80px">
            <el-form-item label="字典名称">
                <el-input v-model="saveForm.label" placeholder="请输入……"></el-input>
            </el-form-item>
            <el-form-item label="字典编码">
                <el-input v-model="saveForm.value" placeholder="请输入……"></el-input>
            </el-form-item>
            <el-form-item label="字典项顺序">
                <el-input v-model="saveForm.sort" placeholder="请输入……"></el-input>
            </el-form-item>
            <el-form-item  label="备注信息">
                <el-input v-model="saveForm.remark" placeholder="请输入……"></el-input>
            </el-form-item>
        </el-form>
        <template #footer>
            <el-button type="info" @click="close()">取消</el-button>
            <el-button type="primary" @click="submit()">确定</el-button>
        </template>
    </el-dialog>
</template>

<script setup>
import router from '@/router';
import axios from 'axios';
import { onMounted, ref } from 'vue';
import qs from 'qs';
import { ElMessage } from 'element-plus';

//定义字典项数组用以保存字典项响应
const dictOptionArr = ref([]);

//定义变量控制弹窗的更新
const dialogVisible = ref(false);

//定义变量控制弹窗的标题
const dialogTitle = ref('新增字典项');

//定义一个变量从搜索路径中获取id参数
const dictId = new URLSearchParams(window.location.search).get('id');

//定义变量存放保存表单
const saveForm = ref({
    label:'',
    value:'',
    sort:'',
    remark:'',
    dictId:dictId
});

//定义变量保存搜索表单
const searchForm = ref({
    label:'',
    dictId:dictId
});


onMounted(()=>{loadDictOption()});

function loadDictOption() {
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/dicOpt/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            dictOptionArr.value = response.data.data;
        }
        else {
            ElMessage.error('发生错误');
        }
    });
};


function reset() {
    searchForm.value.label = '';
    loadDictOption();
};

function edit(id) {};

function deleteOpt(id) {};

function close() {
    saveForm.value = {};
    dialogVisible.value = false;
};

function submit() {
    console.log(saveForm.value);
};
</script>

<style></style>
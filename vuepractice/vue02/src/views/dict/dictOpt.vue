<template>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">字典项管理</span>
            <el-button type="primary" @click="router.go(-1)"
            style="float:right;margin-top:13px;"
            >返回</el-button>
    </div>

    <!-- 搜索卡片 -->
     <!-- //搜索卡片 -->
    <div class="vehicleSearchCard">
            <div class="info">
                <label for="label">字典项名称:</label>
                <input type="text" id="label" placeholder="请输入名称"
                v-model="searchForm.label" @keydown.enter.prevent="loadDictOpt()">
            </div>

            <div>
                <el-button type="primary" @click="loadDictOpt()">提交</el-button>
                <el-button type="info" @click="reset()">重置</el-button>
                <el-button type="warning" @click="addDictOpt()">新增字典项</el-button>
            </div>
    </div>


    <!-- 字典项卡片 -->
    <div class="item-wrapper">
        <div class="item p3" v-for="option in dictOpt">
            <div  class="touxiang" >
                <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
            </div>
            <p class="text">字典项名称:{{ option.label }} </p>
            <p class="tag">字典项值: {{ option.value }}</p>
            <p class="tag">字典项顺序: {{ option.sort }}</p>
            <p class="tag">备注信息: {{ option.remark }}</p>
            <p class="createTime">创建时间: {{ option.createTime }}</p>
            <p id="buttons">
                <el-button type="primary"  
                size="small" @click="edit(option.id)">编辑</el-button>
                <el-button type="warning"  size="small" @click="deleteOpt(option.id)">删除</el-button>
            </p>
        </div>
    </div>


    <!-- 增加字典项弹窗 -->
    <el-dialog :title="dialogTitle" v-model="dialogVisible"
    style="width: 1000px; padding: 40px;padding-left: 5%;">
        <div class="info">
            <label for="label">字典名称：
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="label" 
                placeholder="请输入字典项名字"
                v-model="saveForm.label"
                required
            >
        </div>
        <div class="info">
            <label for="value">字典项值
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="value" 
                placeholder="请输入字典项值"
                v-model="saveForm.value"
                required
            >
        </div>
        <div class="info">
            <label for="sort">字典项排序
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="sort" 
                placeholder="请输入字典项排序值"
                v-model="saveForm.sort"
                required
            >
        </div>
        <div class="info">
            <label for="remark">备注信息
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="remark" 
                placeholder="备注信息"
                v-model="saveForm.remark"
                required
            >
        </div>
        <template #footer>
            <el-button type="info" @click="cancel()">取消</el-button>
            <el-button type="primary" @click="save()">提交</el-button>
        </template>
    </el-dialog>



</template>

<script setup>
import router from '@/router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';

//定义一个变量从搜索路径中获取id参数
const dictId = new URLSearchParams(window.location.search).get('id');
//定义变量保存字典项搜索卡片

const  searchForm = ref({
    label:'',
    dictId:dictId
});

//定义变量存放字典项项目列表
const dictOpt = ref([]);

//定义新增字典项的弹窗可见性
const dialogVisible = ref(false);

//定义字典项弹窗的标题
const dialogTitle = ref('新增字典项');

//定义变量保存用户输入的值
const saveForm = ref({
    label:'',
    value:'',
    sort:'',
    remark:'',
    dictId:dictId
});



onMounted(()=>{loadDictOpt()});

function loadDictOpt() {
    //定义函数
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/dictOpt/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('查找成功！');
            dictOpt.value = response.data.data;
        }
        else {
            ElMessage.error('查找失败！');
        }
    });

}

function reset() {
    searchForm.value.label =  '';
    loadDictOpt();
}

function addDictOpt() {
    console.log(dictId);
    dialogTitle.value = '新增字典项';
    dialogVisible.value = true;
}

function save() {
    console.log(saveForm.value);
    let data = qs.stringify(saveForm.value);
    axios.post(BASE_URL+'/v1/dictOpt/save',data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('操作成功！');
            loadDictOpt();
            dialogVisible.value = false;
            saveForm.value = {
                    label:'',
                    value:'',
                    sort:'',
                    remark:'',
                    dictId:dictId
            };
        }
        else {
            ElMessage.error('操作失败！');
        }
    });
}

function cancel() {
    dialogVisible.value = false;
    saveForm.value = {
            label:'',
            value:'',
            sort:'',
            remark:'',
            dictId:dictId
            };
}

function edit(id) {
    console.log('当前编辑的字典项id=%d',id);
    dialogTitle.value = '编辑字典项';
    dialogVisible.value = true;
    axios.get(BASE_URL+'/v1/dictOpt/query?id='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            saveForm.value = response.data.data[0];
        }
        else {
            ElMessage.error('查询错误！');
        }
    });
}

function deleteOpt(id) {
    console.log('将要删除的字典项id={}',id);
    axios.post(BASE_URL+'/v1/dictOpt/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('删除成功！');
            loadDictOpt();
        }
        else {
            ElMessage.error('删除失败！');
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

.info {
    margin-bottom: 25px;
}

.info input {
    border: none;
    width: 500px;
    height: 40px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgba(255, 255, 255,0.1);
    
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
.item-wrapper {
    display: grid;
    margin-left: 10%;
    margin-right: 10%;
    grid-template-columns: auto auto auto auto;
    gap: 20px;
}

.info label {
    font-size: 1.2em;
    font-weight: bold;
    color: #666;
}

.info input:valid {
    border-bottom-color: var(--valid-color);
}

.info input:invalid {
    border-bottom-color: var(--invalid-color);
}
</style>
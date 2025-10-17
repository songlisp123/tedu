<template>
   <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">字典管理</span>
            <el-button type="primary" style="float:right;margin-top:13px;"
            @click="addDict()"
            >新增字典</el-button>
    </div>

    <!-- //搜索卡片 -->
    <div class="vehicleSearchCard">
            <div class="info">
                <label for="brand">围栏名称：</label>
                <input type="text" id="name" placeholder="请输入名称"
                v-model="searchForm.name" @keydown.enter="loadDict()"
                >
            </div>
            <div class="info">
                <label for="brand">字典编码:</label>
                <input type="text" id="code" placeholder="请输入编码"
                v-model="searchForm.code" @keydown.enter="loadDict()"
                 >
            </div>

            <div>
                <el-button type="primary" @click="loadDict()">提交</el-button>
                <el-button type="info" @click="reset()">重置</el-button>
            </div>
    </div>

    <!-- 字典列表 -->
    <div class="item-wrapper">
        <div class="item p3" v-for="dict in dictArray">
            <div  class="touxiang" >
                <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
            </div>
            <p class="text">字典名称:{{ dict.name }}</p>
            <p class="tag">字典编码:
                <el-button
                 type="primary" @click="router.push('/dictOption?id='+dict.id)"
                size="small" link><span style="color: aqua;">{{ dict.code }}</span></el-button>
            </p>
            <p class="tag">备注信息:{{ dict.remark }}</p>
            <p class="createTime">创建时间:{{ dict.createTime }}</p>
            <p>字典状态：
                <el-switch inactive-value="0" active-value="1" 
                v-model="dict.status"
                @click="updateStatus(dict.id,dict.status)"></el-switch>
            </p>
            <p id="buttons">
                <el-button type="primary"  size="small" @click="edit(dict.id)">编辑</el-button>
                <el-button type="warning"  size="small" @click="deleteDict(dict.id)">删除</el-button>
            </p>
        </div>
    </div>

    <!-- 弹出弹窗来让用户保存、更新 -->
    <el-dialog v-model="dialogVisible" :title="dialogTitle"
    style="width: 1000px; padding: 40px;">
        <div class="info">
            <label for="name">字典名称：
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="name" 
                placeholder="请输入字典名字"
                v-model="saveForm.name"
                required
            >
        </div>
        <div class="info">
            <label for="code">字典编码：
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="name" 
                placeholder="请输入字典编码"
                v-model="saveForm.code"
                required
            >
        </div>
        <div class="info">
            <label for="name">备注信息
                <span style="color: var(--invalid-color);">*</span></label> <br>
            <input type="text" id="name" 
                placeholder="请输入字典备注信息"
                v-model="saveForm.remark"
                required
            >
        </div>
        <template #footer>
            <el-button type="info" @click="cancel()">取消</el-button>
            <el-button type="warning" @click="submit()">提交</el-button>
        </template>

    </el-dialog>

        <!-- 这是分页器 -->
    <div style="margin:30px;float: right;" >
      <!--layout="total(总数), sizes(每页总数), prev(上一页), pager(当前页), next(下一页), jumper(跳转页)"
       :pager-count="4" 表示显示4个页码
       :page-sizes="[5, 10, 15, 20]" 表示一共有几个"每页显示多少条"选项
       current-page当前页码 page-size当前页码的条数 total总条数 这三个变量需要和实际数据双向绑定
       :total="400" 表示总条数，需要双向绑定
       @size-change="handleSizeChange" 表示每页显示多少条改变时触发的事件
       @current-change="handleCurrentChange" 表示当前页码改变时触发的事件 -->
      <el-pagination
          layout="prev, pager, next"
          background
          v-model:current-page="currentPage"
          v-model:page-size="pageSize"
          v-model:total="total"
          @current-change="handlerCurrentChange"
          @size-change="handlerSizeChange"
          size="large"
          :total="500"/>
    </div>


</template>

<script setup>
import router from '@/router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import qs from 'qs';
import { onMounted, ref } from 'vue';

//定义变量存放字典列表
const dictArray = ref([]);

//定义绑定搜索表单保存数据
const searchForm = ref({
    name:'',
    code:''
});

//定义变量保存当前页码数
const currentPage = ref(1);

//定义变量显示当前需要显示的条目数量
const pageSize = ref(3);

//定义变量显示搜索的全部条目数量
const total = ref(200);

//定义变量存放保存表单的数据
const saveForm = ref({
    name:'',
    code:'',
    remark:''
});

//定义弹窗出与否
const dialogVisible = ref(false);

//定义弹窗的标题
const dialogTitle = ref('新增数据字典');

onMounted(()=>{loadDict()});

function loadDict() {
    //查询前先将页码和本页显示多少数量条目传递给服务器
    //当前页码
    searchForm.value.currentPage = currentPage.value;
    //每页显示多少个条目
    searchForm.value.pageSize = pageSize.value;
    console.log(searchForm.value);
    let data = qs.stringify(searchForm.value);
    axios.get(BASE_URL+'/v1/dict/query?'+data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('查询成功！');
            dictArray.value = response.data.data;
        }
        else {
            ElMessage.error('查询失败');
        }
    });
}

function reset() {
    searchForm.value = {};
    loadDict();
}

function addDict() {
    dialogTitle.value = '新增字典';
    dialogVisible.value = true;
}

function submit() {
    console.log(saveForm.value);
    let data = qs.stringify(saveForm.value);
    axios.post(BASE_URL+'/v1/dict/save',data)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('操作成功！');
            loadDict();
            dialogVisible.value = false;
        }
        else {
            ElMessage.error('插入失败！');
        }
    });
}

function cancel() {
    dialogVisible.value = false;
    saveForm.value = {};
}

function edit(id) {
    console.log('当前字典Id号码:%d',id);
    dialogTitle.value = '编辑字典';
    dialogVisible.value = true;
    axios.get(BASE_URL+'/v1/dict/query?id='+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            saveForm.value = response.data.data[0];
        }
        else {
            ElMessage.error('查询失败！');
        }
    });
}

function updateStatus(id,status) {
    console.log('字典id=%d,字典状态:%d',id,status)
    axios.post(BASE_URL+'/v1/dict/update/'+id+'/'+status)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('更新成功！');
            loadDict();
        }
        else {
            ElMessage.error('更新失败！');
        }
    });
}

function deleteDict(id) {
    console.log('当前字典id=%d',id);
    axios.post(BASE_URL+'/v1/dict/delete/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('删除成功！');
            loadDict();
        }
        else {
            ElMessage.error('删除失败！');
        }
    });
}

function handlerCurrentChange(number) {
    console.log(number);
    loadDict();
}

function handlerSizeChange(number) {
    console.log(number);
    loadDict();

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

.item {
    background-color: #666;
    border-radius: 10px;
}



p {
    text-align: center;
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

.info {
    margin-bottom: 20px;
}

.info label {
    font-size: 1.1em;
    font-weight: var( --font-weight);
    color: var(--web-color);
}

.info input {
    border: none;
    width: 200px;
    height: 30px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgba(255, 255, 255,0.1);
    
}

.info input:valid {
    border-bottom-color: var(--valid-color);
}

.info input:invalid {
    border-bottom-color: var(--invalid-color);
}

</style>
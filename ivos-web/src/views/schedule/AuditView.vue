<!--申请审批页-->
<template>
  <!-- 顶部条 -->
  <div style="height: 6vh;background-color:#fff;padding:10px 20px;">
    <span style="line-height:60px;font-size:20px;">审批列表</span>
  </div>
  <!-- 审批搜索卡片 -->
  <el-card style="margin:20px;height: 70px;">
    <el-form :inline="true">
      <el-form-item label="用车人">
        <el-input placeholder="请输入用车人" v-model="searchForm.username"
        @keydown.enter.prevent="loadAudit()"
        style="width:220px;"></el-input>
      </el-form-item>
      <el-form-item>
        <el-button type="info" @click="reset()">重置</el-button>
        <el-button type="primary" @click="loadAudit()">查询</el-button>
      </el-form-item>
    </el-form>
  </el-card>

  <!-- 审批单主体 -->
  <el-card style="margin:20px;">
    <!--  审批状态项  -->
    <el-radio-group style="margin-bottom: 15px;" v-model="type" @change="loadAudit()">
      <el-radio-button value="10" size="large">待我审核</el-radio-button>
      <el-radio-button value="20" size="large">待他人审核</el-radio-button>
      <el-radio-button value="30" size="large">已审核</el-radio-button>
      <el-radio-button value="40" size="large">驳回</el-radio-button>
    </el-radio-group>
    <!--  审批列表  -->
    <el-table :data="auditArray">
      <el-table-column label="编号" prop="id" align="center" width="55" type="index"></el-table-column>
      <el-table-column label="用车人" prop="username" align="center" width="110"></el-table-column>
      <el-table-column label="开始时间" prop="startTime"  align="center"></el-table-column>
      <el-table-column label="结束时间" prop="endTime"  align="center"></el-table-column>
      <el-table-column label="用车事由" prop="reason"  align="center"></el-table-column>
      <el-table-column label="审批人" prop="auditUsernameList"  align="center"></el-table-column>
      <el-table-column label="出发地" prop="departureAddr"  align="center"></el-table-column>
      <el-table-column label="目的地" prop="destinationAddr"  align="center"></el-table-column>
      <el-table-column label="操作" width="100" align="center" 
      v-if="type==10||type==40" :key="audit">
        <template #default="scope">
          <!-- 审批10：在待我审核页签下显示，需要当前登录用户进行审批 -->
          <el-button type="primary" link v-if="type==10"
           @click="auditing(scope.row.id)">审批</el-button>
          <!-- 查看40：在驳回页签下显示，可以查看已驳回申请的驳回原因 -->
          <el-button type="primary" link 
          @click="auditing(scope.row.id)"
          v-if="type==40">查看</el-button>
        </template>
      </el-table-column>
    </el-table>
  </el-card>

  <!-- 审批/查看弹窗 -->
  <el-dialog :title="dialogTitle" v-model="auditDialogVisible">
    <el-descriptions direction="horizontal" :column="2" border>
      <el-descriptions-item label="用车人">
        {{ auditDialogData.username }}
      </el-descriptions-item>
      <el-descriptions-item label="用车事由">
        {{ auditDialogData.reason }}
      </el-descriptions-item>
      <el-descriptions-item label="使用开始时间">
        {{ auditDialogData.startTime }}
      </el-descriptions-item>
      <el-descriptions-item label="使用结束时间">
        {{ auditDialogData.endTime }}
      </el-descriptions-item>
      <el-descriptions-item label="车辆出发地">
        {{ auditDialogData.departureAddr }}
      </el-descriptions-item>
      <el-descriptions-item label="车辆目的地">
        {{ auditDialogData.destinationAddr }}
      </el-descriptions-item>
      <el-descriptions-item label="驾照图片">
        <img :src="BASE_URL+auditDialogData.imgUrl" style="width:150px;">
      </el-descriptions-item>
      <el-descriptions-item label="备注">{{ auditDialogData.remark }}</el-descriptions-item>
      <!--  驳回原因要在点击驳回才显示 -->
      <el-descriptions-item label="驳回原因"
      v-if="auditDialogData.auditStatus == 40"
      >{{ auditDialogData.rejectReason }}</el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <el-button @click="cancel()">取消</el-button>
      <el-button type="primary" plain @click="rejectInnerDialogVisible=true">驳回</el-button>
      <el-button type="primary" @click="pass()">通过</el-button>
    </template>
  </el-dialog>

  <!-- 驳回原因弹窗 -->
  <el-dialog title="驳回 查看" 
  v-model="rejectInnerDialogVisible" style="margin-top: 37vh;">
    <el-descriptions direction="horizontal" border>
      <el-descriptions-item label="驳回原因">
        <el-input placeholder="请输入驳回原因" v-model="rejectReason"></el-input>
      </el-descriptions-item>
    </el-descriptions>
    <template #footer>
      <el-button type="primary" plain @click="rejectCancel()">取消</el-button>
      <el-button type="primary" @click="reject()">确定</el-button>
    </template>
  </el-dialog>
</template>


<script setup>
import {onMounted, ref} from "vue";
import qs from 'qs';
import axios from "axios";
import { ElMessage } from "element-plus";

//控制审批弹窗标题
const dialogTitle = ref("待审批详情");
//控制审批弹窗是否显示
const auditDialogVisible = ref(false);
//控制驳回原因弹窗是否显示
const rejectInnerDialogVisible = ref(false);

//定义变量存放搜索表单得到值
const searchForm = ref({
    username:''
});

//定义数组来保存审核单数据
const auditArray = ref([]);

//定义变量存放当前用户值
const currentUser = ref(window.getUser());

//定义变量存放审批单状态
const type = ref('10'); //默认为待我审核

//定义变量绑定后端数据
const auditDialogData = ref({
  username:'',
  reason:'',
  startTime:'',
  endTime:'',
  departureAddr:'',
  destinationAddr:'',
  imgUrl:'',
  auditStatus:'',
  rejectReason:'',
  remark:''
});

//定义变量用来保存驳回原因
const rejectReason = ref('');

onMounted(()=>{loadAudit()});
function loadAudit() {
  searchForm.value.auditUserId = currentUser.value.id;
  searchForm.value.auditStatus = type.value;
  console.log(searchForm.value);
  let data = qs.stringify(searchForm.value);
  console.log(data)
  axios.get(BASE_URL+'/v1/audit/query?'+data)
  .then((response)=>{
    if (response.data.code == 2000) {
      ElMessage.success('查询成功！');
      auditArray.value = response.data.data;
    }
    else {
      ElMessage.error('查训失败');
    }
  });
  
}

function reset() {
  searchForm.value.username = '';
  loadAudit();
}


/**
 * 审批查看弹窗
 * @param id 当前审批单id
 */
function auditing(id) {
 
  axios.get(BASE_URL+'/v1/audit/query?id='+id)
  .then((response)=>{
    if (response.data.code == 2000) {
      auditDialogData.value = response.data.data[0];
      if (auditDialogData.value.auditStatus == 10) {
        dialogTitle.value = '待审批状态';
      }
      else {
        dialogTitle.value = '驳回详情';
      }
    }

    else {
      ElMessage.error('查询失败');
    }
  });
   auditDialogVisible.value = true;

}

function pass() {
  auditDialogData.value.auditStatus = 30;
  console.log(auditDialogData.value);
  let data = qs.stringify(auditDialogData.value);
  console.log(data);
  axios.post(BASE_URL+'/v1/audit/update',data)
  .then((response)=>{
    if (response.data.code == 2000) {
      ElMessage.success('审批通过');
      auditDialogData.value = {};
      auditDialogVisible.value = false;
      loadAudit();
    }
    else {
      ElMessage.error('审核失败');
    }
  });
}

function reject() {
  if (!rejectReason.value || rejectReason.value.trim() == '') {
    ElMessage.error('必须输入驳回元婴！');
    return;
  }
  auditDialogData.value.auditStatus = 40;
  auditDialogData.value.rejectReason = rejectReason.value;
  rejectInnerDialogVisible.value = false;
  console.log(auditDialogData.value);
  let data = qs.stringify(auditDialogData.value);
  axios.post(BASE_URL+'/v1/audit/update',data)
  .then((response)=>{
    if (response.data.code == 2000) {
      ElMessage.success('驳回成功！');
      rejectReason.value = '';
      rejectInnerDialogVisible.value = false;
      auditDialogData.value = {};
      auditDialogVisible.value = false;
      loadAudit();
    }
    else {
      ElMessage.error('驳回失败');
    }
  });
}


function rejectCancel() {
  if (confirm('你是否要驳回？')) {
    rejectReason.value = '';
    rejectInnerDialogVisible.value = false;
  }
}

function cancel() {
  auditDialogVisible.value = false;
}
</script>

<style></style>
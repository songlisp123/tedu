<!--用车申请页-->
<template>
  <div style="height: 6vh;background-color:#fff;padding:10px 20px;">
    <span style="line-height:60px;font-size:20px;">申请单列表</span>
    <el-button type="primary" style="float:right;margin-top:13px;" 
    v-if="user.parentId != null"
    @click="addApplicationDialogVisible=true">申请用车</el-button>
  </div>
  <!-- 申请用车弹窗 -->
  <el-dialog title="创建申请单" v-model="addApplicationDialogVisible" style="width:1000px;padding:40px;">
    <el-form label-width="80px" label-position="top">
      <el-row :gutter="30">
        <el-col :span="12">
          <el-form-item label="用车人">
            <el-input readonly :value="user.username" disabled></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="使用时间">
            <el-date-picker
                type="datetimerange"
                range-separator="至"
                start-placeholder="开始日期"
                end-placeholder="结束日期"
                value-format="YYYY-MM-DD HH:mm:ss"
                format="YYYY-MM-DD HH:mm:ss"
                v-model="times"
            ></el-date-picker>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="30">
        <el-col :span="12">
          <el-form-item label="车辆出发地">
            <el-input placeholder="请输入" v-model="addForm.departureAddr"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="车辆目的地">
            <el-input placeholder="请输入" v-model="addForm.destinationAddr"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="30">
        <el-col :span="12">
          <el-form-item label="用车事由">
            <el-input placeholder="请输入" 
            v-model="addForm.reason"
            type="textarea" resize="none" :rows="3"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="12">
          <el-form-item label="备注">
            <el-input placeholder="请输入" 
            v-model="addForm.remark"
            type="textarea" resize="none" :rows="3"></el-input>
          </el-form-item>
        </el-col>
      </el-row>
      <el-row :gutter="30">
        <el-col :span="12">
          <el-form-item label="驾照">
          <!-- 处理图片的方法 -->
           <!-- 其中的属性的含义是：
            v-model:file-list="fileList"：这代表着将用户上传的图片保存到fileList的数组变量中
            v-model:action="actionUrl"：这个属性绑定了图片上传的时间，它会根据actionUrl
            变量的值请求
            name="file"：表示该控件的命名空间
            limit="1"：限制一次只能上床一个图片
            :on-preview="handlePictureCardPreview"：点击预览按钮时会调用的handlePictureCardPreview
            函数
            :on-remove="handleRemove"：点击删除的时候会调用handleRemove函数

           -->
            <el-upload
                v-model:file-list="fileList"
                v-model:action="actionUrl"
                name="file"
                limit="1"
                list-type="picture-card"
                :on-preview="handlePictureCardPreview"
                :on-remove="handleRemove"
            >
              <el-icon>
                <Plus/>
              </el-icon>
            </el-upload>
            <!-- 关于img属性的说明
             w-full：完全宽度，相当于width=100%
             :src:属性绑定js变量
            -->
            <el-dialog v-model="dialogVisible">
              <img w-full :src="dialogImageUrl"/>
            </el-dialog>
          </el-form-item>
        </el-col>
        <el-col :span="12" v-if="auditUserOpts.length>0">
          <el-form-item label="审批人">
            <el-row :gutter="10">
              <el-col :span="12">
                <el-tag type="primary">
                  <el-icon style="margin-right:5px;position:relative;top:2px;">
                    <User/>
                  </el-icon>
                  <span>{{ auditUserOpts[0].username }}</span>
                </el-tag>
              </el-col>
              <el-col :span="12" v-if="auditUserOpts.length>1">
                <el-tag type="primary">
                  <el-icon style="margin-right:5px;position:relative;top:2px;">
                    <User/>
                  </el-icon>
                  <span>{{ auditUserOpts[1].username }}</span>
                </el-tag>
              </el-col>
            </el-row>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
    <template #footer>
      <el-button type="info" @click="cancel()">取消</el-button>
      <el-button type="primary" @click="submit()">确定</el-button>
    </template>
  </el-dialog>

  <!-- 用车申请列表 搜索卡片 -->
  <el-card style="margin: 20px;">
    <el-form style="padding-top:10px;">
      <el-row :gutter="30">
        <el-col :span="5">
          <el-form-item label="出发地">
            <el-input placeholder="请输入出发地"
            @keydown="loadApplication()"
            v-model="searchForm.departureAddr"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="目的地">
            <el-input placeholder="请输入目的地"
            @keydown="loadApplication()"
            v-model="searchForm.destinationAddr"></el-input>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item label="申请单状态">
            <el-select placeholder="请选择"
            @change="loadApplication()"
            v-model="searchForm.status">
              <el-option label="已发起" value="10"></el-option>
              <el-option label="撤销" value="20"></el-option>
              <el-option label="审核中" value="30"></el-option>
              <el-option label="驳回" value="40"></el-option>
              <el-option label="已通过" value="50"></el-option>
              <el-option label="分配用车" value="60"></el-option>
              <el-option label="工单结束" value="70"></el-option>
            </el-select>
          </el-form-item>
        </el-col>
        <el-col :span="5">
          <el-form-item>
            <el-button @click="reset()">重置</el-button>
            <el-button type="primary" @click="loadApplication()">查询</el-button>
          </el-form-item>
        </el-col>
      </el-row>
    </el-form>
  </el-card>
  <!-- 用车申请表格 -->
  <el-card style="margin:20px">
    <el-table :data="applicationArray">
      <el-table-column type="index" width="80" align="center" prop="id" label="编号"></el-table-column>
      <el-table-column align="center" prop="username" label="申请人"></el-table-column>
      <el-table-column align="center" prop="departureAddr" label="出发地"></el-table-column>
      <el-table-column align="center" prop="destinationAddr" label="目的地"></el-table-column>
      <el-table-column align="center" prop="reason" label="用车原因"></el-table-column>
      <el-table-column align="center" prop="auditUsernameList" label="审批人"></el-table-column>
      <el-table-column align="center" prop="startTime" label="使用开始时间"></el-table-column>
      <el-table-column align="center" prop="endTime" label="使用结束时间"></el-table-column>
      <el-table-column align="center" prop="status" label="申请单状态"></el-table-column>
      <el-table-column label="操作" width="120" align="center">
        <template #default="scope">
          <el-button type="primary" size="small" link 
          @click="chexiao(scope.row.id)"
          :disabled="scope.row.status != 10">撤销</el-button>
        </template>
      </el-table-column>
    </el-table>

  </el-card>
</template>
<script setup>
import {onMounted, ref} from "vue";
import axios from "axios";
import {ElMessage} from "element-plus";
import qs from 'qs';
//定义变量控制创建申请单弹窗是否出现
const addApplicationDialogVisible = ref(false);

//定义当前用户信息
const user = ref(window.getUser());

//定义数组保存审批人的对象
const auditUserOpts = ref([]);

//定义数组用来保存多个审批人id
const auditUserIdList = ref([]);

/**        图片上传相关代码开始        **/

const fileList = ref([]);
const dialogImageUrl = ref('');
const dialogVisible = ref(false);
//定义变量用来保存图片上传给哪个后端接口地址
const actionUrl = ref(BASE_URL + '/v1/file/upload');

//定义一个数组用来存放时间变量
const times = ref([]);

//定义添加申请表单的数据绑定
const addForm = ref({
  departureAddr:'',
  destinationAddr:'',
  reason:'',
  remark:''
});

//定义数组用来存放搜索表单
const searchForm = ref({
  departureAddr:'',
  destinationAddr:'',
  status:''
});

//定义数组用来存放查询数据
const applicationArray = ref([]);

onMounted(()=>{loadApplication()});

//移除图片
const handleRemove = (uploadFile, uploadFiles) => {
  console.log(uploadFile, uploadFiles);
  //1.得到要删除的图片路径
  let imgUrl = fileList.value[0].response.data;
  //2.给后端发请求,删除图片
  axios.post(BASE_URL+'/v1/file/delete?imgUrl='+imgUrl)
      .then((response)=>{
        if (response.data.code==2000){
          // ElMessage.success('删除成功!');
          console.log('删除成功！');
        }
      })
}

onMounted(()=>{loadAuditUser()});
//图片上传后预览
const handlePictureCardPreview = (uploadFile) => {
  dialogImageUrl.value = uploadFile.url
  dialogVisible.value = true;
  console.log(uploadFile);
  //console.log(fileList.value[0].response.data);
}


/**        图片上传相关代码结束         **/


function loadAuditUser() {
    //获取当前登录人的直属领导
    let parentId = user.value.parentId;
    //像后端发送请求，查询当前上级领导
    axios.get(BASE_URL+'/v1/user/audit/'+parentId)
    .then((response)=>{
        if (response.data.code == 2000) {
            auditUserOpts.value = response.data.data;
            for (let i =0;i<auditUserOpts.value.length;i++) {
                auditUserIdList.value.push(auditUserOpts.value[i].id);
            }
        }
        else {
            ElMessage.error('查询失败！');
        }
    });
}

function submit() {
  if (fileList.value.length == 0) {
    ElMessage.error('不建议您无证驾驶！');
    return;
  }
  addForm.value.startTime = times.value[0];
  addForm.value.endTime = times.value[1];
  addForm.value.imgUrl = fileList.value[0].response.data;
  addForm.value.userId = user.value.id;
  addForm.value.username = user.value.username;
  addForm.value.auditUserIdList = auditUserIdList.value;
  console.log(addForm.value);

  let data = qs.stringify(addForm.value);
  axios.post(BASE_URL+'/v1/app/save',data)
  .then((response)=>{
    if (response.data.code == 2000) {
      ElMessage.success('添加成功');
      times.value = [];
      fileList.value = [];
      addForm.value = {};
      addApplicationDialogVisible.value = false;
    }
    else {
      ElMessage.error('添加失败！请稍后重试！');
    }
  }); 
}

function cancel() {
  addForm.value = {};
  times.value = [];
  if (fileList.value.length>0) {
    handleRemove();
  }
  fileList.value = [];
  addApplicationDialogVisible.value = false;
}

function loadApplication() {
  let data = qs.stringify(searchForm.value);
  axios.get(BASE_URL+'/v1/app/query?'+data)
  .then((response)=>{
    if (response.data.code == 2000) {
      ElMessage.success('查询成功!');
      applicationArray.value = response.data.data;
    }
    else {
      ElMessage.error('查询失败 ！');
    }
  });

}

function reset() {
  searchForm.value = {};
  loadApplication();
}

function chexiao(id) {
  if (confirm('你是否想要撤销该申请单？')) {
    axios.post(BASE_URL+'/v1/app/cancel/'+id)
    .then((response)=>{
      if (response.data.code == 2000) {
        ElMessage.success('撤销成功');
        loadApplication();
      }
      else {
        ElMessage.error('撤销失败');
      }
    });
  }
}
</script>

<style scoped>
</style>
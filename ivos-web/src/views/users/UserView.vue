<template>
    <!-- 用户模块 -->
     <div style="height: 7vh;background-color: #fff; padding: 10px 20px;">
        <span style="font-size: 20px; line-height: 60px; font-weight: bold;">用户</span>
        <el-button @click="beforeAndUser()" type="primary" style="float: right; margin-top: 20px;">
            新建用户
        </el-button>
     </div>
     <!-- 搜索卡片
      box-shadow:边框阴影
      @keydown.enter:enter按钮备案下时候会触发的事件
      @change:多选框,下拉框,修改状态的字段一旦被更改就会调用此事件
     -->

      <el-card style="background-color: #fff;
      margin:10px; 
      height: 70px;
      box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);">
        <el-form :inline="true">
            <el-form-item label="用户:">
                <el-input placeholder="请输入您的名字" v-model="searchForm.username"
                  @keydown.enter="loadUser()"  style="width: 350px;"></el-input>
            </el-form-item>
            <el-form-item label="用户状态:">
                <el-select style="width: 350px;" v-model="searchForm.status"
                    @change="loadUser()">
                    <el-option label="启用" value="1"></el-option>
                    <el-option label="禁止" value="0"></el-option>
                </el-select>
            </el-form-item>
            <el-form-item>
                <el-button @click="reset()">重置</el-button>
                <el-button type="primary" @click="loadUser()">查询</el-button>
            </el-form-item>
        </el-form>
      </el-card>
      <!-- 用户视图模块 -->
       <el-card style="margin: 10px; margin-top: 20px;
        background-color: #fff;
       box-shadow: 0px 8px 15px rgba(0, 0, 0, 0.1);">
        <!-- el-table表示表格组件，其中主要的属性是：
        :data ---  表示要绑定的对象，该对象的状态对应于表格的列
        每一列有一个prop属性与绑定对象的字段一一对应 
        其子元素el-table-column对应于捏个对象的属性
        label属性表示该列名
        type属性表示该列具有其他类型，比如index列具有递增的编号
        align属性要求内容居中显示
        prop属性将会绑定到对象
        template标签代表着将会从vue框架内插入一段预设模板
        -->
        <el-table :data="userA">
            <el-table-column label="编号" type="index" align="center" width="80"></el-table-column>
            <el-table-column label="用户名" prop="username" align="center"></el-table-column>
            <el-table-column label="手机号" prop="phone" align="center"></el-table-column>
            <el-table-column label="加入时间" prop="createTime" align="center"></el-table-column>
            <el-table-column label="用户状态" align="center">
                <template #default="scope">
                    <el-switch  v-model="scope.row.status" 
                    active-value="1" inactive-value="0"
                        @change="updateUserStatus(scope.row.id,scope.row.status)" 
                        :disabled="scope.row.level == 40"></el-switch>
                </template>
            </el-table-column>
            <el-table-column label="操作"  align="center">
                <template #default="scope">
                    <el-button type="primary" link :disabled="scope.row.level == 40" 
                    @click="resetpassword(scope.row.id)">重置密码</el-button>
                    <el-button type="primary" link :disabled="scope.row.level == 40" 
                    @click="deleteUser(scope.row.id)">删除 </el-button>
                    <el-button type="primary" link 
                    @click="edit(scope.row.id)" :disabled="scope.row.level == 40">
                        编辑
                    </el-button>
                </template>
            </el-table-column>
        </el-table>
       </el-card>
       <!-- 这里我想要的说的是：
       这是一个用户注册弹窗的实现
       绑定了一个boolean变量用来显示当前弹窗
       主要的一个属相是： 
       :befofe-close：这是一个处理要关闭弹窗的属性，他会在用户想要关闭弹窗的时候调用
       调用此方法的事件是：
       1、点击关闭按钮
       2、点击取消按钮
       3、点击弹窗外的区域
       title:此弹窗的标题
       label-position:设置标题的位置,一般来说,有上下左右等
       gutter:间距,当前行元素之间的间距
       span:次元素占据的宽度
       -->
       <el-dialog v-model="dialogVisible"
       :title="dialogTitle" style="width: 1000px; padding: 40px;">
            <el-form label-position="top">
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="用户名称:">
                            <el-input placeholder="请输入您的用户名称：" v-model="regPara.username" :disabled="regPara.id != null"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用户手机号:">
                            <el-input placeholder="请输入您的手机号：" v-model="regPara.phone"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row :gutter="10">
                    <el-col :span="12">
                        <el-form-item label="用户邮箱:">
                            <el-input placeholder="请输入您的用户邮箱：" v-model="regPara.email"></el-input>
                        </el-form-item>
                    </el-col>
                    <el-col :span="12">
                        <el-form-item label="用户年龄:">
                            <el-input placeholder="请输入您的年龄：" v-model="regPara.age"></el-input>
                        </el-form-item>
                    </el-col>
                </el-row>
                <el-row>
                    <el-col :span="6">
                        <el-form-item label="职级:">
                            <el-select placeholder="请选择职级:" 
                            v-model="regPara.level"  
                            @change="leaderOption()">
                                <el-option v-for="level in levelArray" 
                                :label="level.label" 
                                :value="level.value"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="6">
                        <el-form-item label="上级领导:">
                            <el-select placeholder="请选择领导:" 
                            v-model="regPara.parentId">
                                <el-option v-for="item in lingdao" 
                                :label="item.username" 
                                :value="item.id"></el-option>
                            </el-select>
                        </el-form-item>
                    </el-col>
                    <el-col :span="4">
                        <el-form-item label="性别">
                            <el-radio-group v-model="regPara.gender">
                                <el-radio-button label="男" value="1"></el-radio-button>
                                <el-radio-button label="女" value="0"></el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                    <el-col :span="8">
                        <el-form-item label="用户状态">
                            <el-radio-group v-model="regPara.status">
                                <el-radio-button label="启用" value="1"></el-radio-button>
                                <el-radio-button label="禁用" value="0"></el-radio-button>
                            </el-radio-group>
                        </el-form-item>
                    </el-col>
                </el-row>
            </el-form>
            <template #footer>
                <el-button @click="handlerClose()">取消</el-button>
                <el-button type="primary" @click="reguser()">确定</el-button>
            </template>
       </el-dialog>
</template>

<script setup>
import axios from 'axios';
import { onMounted, ref } from 'vue';
import qs from "qs";
import { BAR_MAP, ElMessage } from 'element-plus';

//定义数组保存用户状态
let userA = ref([]);
// //像后端发送请求
// axios.get(BASE_URL+'/v1/user/list?')
//     .then((response)=>{
//         userA.value = response.data.data;
//     });

//定义一个变量绑定动态绑定用户的输入
const searchForm = ref({
    username:'',
    status:''
});


//定义数组用来查询员工职级
const levelArray = ref([
    {label:'职员',value:'10'},
    {label:'高级员工',value:'20'},
    {label:'经理',value:'30'},
    {label:'高级经理',value:'40'},
]);

const lingdao = ref([
    // {username:'xxx',id:'101'},
    // {username:'yyy',id:'101'}
]);

//定义变量接收有用户输入的参数
const regPara = ref({
    username:'',
    phone:'',
    email:'',
    age:'',
    level:'',
    parentId:'',
    gender:'',
    status:''
});


//新建变量控制弹窗是否出现
//这个变量会绑定到新建按钮上
const dialogVisible = ref(false);

//定义变量保存当前弹窗标题
const dialogTitle = ref('新增员工');

//随后为input空间和button绑定函数
const loadUser = () => {
    console.log(searchForm.value);
    //获得数据后向后端发送请求
    let data = qs.stringify(searchForm.value);
    console.log(data);
    axios.get(BASE_URL+'/v1/user/lists?'+data)
    .then((response)=>{
        if(response.data.code == 2000) {
            userA.value = response.data.data;
            if (userA.value.length == 0) {
                ElMessage.warning("暂无更多数据！");
            }
            else {
            ElMessage.success("搜索成功！")
            }
        }else {
            ElMessage.error(response.data.message);
        }
    });


};


//onMounted函数的意义是：当页面第一次被挂载完毕的时候会触发该事件
//该事件只用于第一次进入页面的时候被使用
onMounted(()=>{loadUser()});

/**
 * 定义重置搜索表单的方法
 */
function reset() {
    searchForm.value.username = '';
    searchForm.value.status = '';
    loadUser();
}


/*****************  用户注册功能 *************/

function reguser() {
    console.log(regPara.value);
    axios.post(BASE_URL+'/v1/user/reg',regPara.value)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('添加用户成功！');
            regPara.value = {};
            dialogVisible.value = false;
            loadUser();
        }
        else {
            ElMessage.error('操作失败！');
        }
    });
};


//处理弹窗关闭的方法
/**
 * 处理关闭弹窗的提示信息
 */
function handlerClose() {
    if (confirm('你确定要关闭吗？')) {
        regPara.value = {};
        dialogVisible.value = false;
    }
};

/**
 * 这是一个搜索上级领导的函数,主要是使用了level+10的
 * 技巧,查找上级领导
 */
const leaderOption = () => {
    regPara.value.parentId = '';
    lingdao.value = [];
    let level = parseInt(regPara.value.level)+10;
    console.log(level);
    axios.get(BASE_URL+'/v1/user/lists?level='+level)
    .then((response)=>{
        if (response.data.code == 2000) {
            console.log(response.data.data);
            lingdao.value = response.data.data;
        } else {
            ElMessage.error('未知错误！');
        }
    });
};

/******************       用户编辑模块     *******************/

/**
 * 定义新建员工的方法，修改弹窗的标题和可见性
 */
function beforeAndUser() {
    dialogTitle.value = '新建员工';
    dialogVisible.value = true;
}

/**
 * 创建编辑员工的方法
 * @param id  员工id号码
 */
function edit(id) {
    dialogTitle.value = '编辑员工';
   dialogVisible.value = true;
   //向后端发送请求并回显数据
   axios.get(BASE_URL+'/v1/user/lists?id='+id)
   .then((response)=>{
    if (response.data.code == 2000) {
        regPara.value = response.data.data[0];
        let level = parseInt(regPara.value.level)+10;
        console.log(level);
        axios.get(BASE_URL+'/v1/user/lists?level='+level)
        .then((response)=>{
        if (response.data.code == 2000) {
            console.log(response.data.data);
            lingdao.value = response.data.data;
        } else {
            ElMessage.error('未知错误！');
        }
    });
    } else {
        ElMessage.warning('你可能正在操作空用户……');
    }
   });
}

/******************       xx用户编辑模块结束xx    *******************/


/******************       用户重置密码阶段     *******************/
function resetpassword(id) {
    axios.post(BASE_URL+'/v1/user/update/password/'+id)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('更新成功！');
        }
        else {
            ElMessage.error('操作失败！请稍后再试！');
        }
    });

}

/******************       用户状态修改     *******************/
function updateUserStatus(id,status) {
    console.log("修改状态");
    console.log(id,status);
    axios.post(BASE_URL+'/v1/user/update/status/'+id+'/'+status)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('更新用户状态成功！');
        }else {
            ElMessage.error('更改错误！请稍后再试！')
        }
    });
}

/******************       用户删除     *******************/
function deleteUser(id) {
    if (confirm('你是否想要删除这个用户？')) {
        console.log('执行删除任务。');
        axios.post(BASE_URL+'/v1/user/delete/'+id)
        .then((response)=>{
            if (response.data.code == 2000) {
                ElMessage.success('删除成功！');
                loadUser();
            } else {
                ElMessage.error('操作失败！请稍候再试！');
            }
        });
    }
}

/******************       梳理逻辑     *******************/
/**
 * 此用户视图
 */
</script>

<style></style>



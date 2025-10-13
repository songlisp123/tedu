<template>
    <!-- <h1>这是用户列表</h1> -->
     <!-- <div>这是一个简单的页面</div> -->
    <div class="search-card">
        <div>
            <label for="username">用户名搜索：</label>
            <input type="text" name="username" id="username" 
            v-model="searchForm.username" @keydown.enter="submit()">
        </div>
          
        <div class="status">
            <label for="status">状态：</label>
            <div class="select-wrapper">
                <select v-model="searchForm.status" id="status" @change="submit()"> 
                    <option value="0">禁用</option>
                    <option value="1">启用</option>
                </select> 
            </div>
                       
        </div>

        <div>
            <el-button type="primary" @click="submit()">提交</el-button>
            <el-button type="info" @click="reset()">重置</el-button>
        </div>
    </div>

    <div class="item-wrapper">
        <div class="item p3" v-for="user in users">
            <div  class="touxiang" >
                <img src="/svg/documents.svg" alt="咋还能weizhaodaoz">
            </div>
            <p class="text">学子 {{ user.username }}</p>
            <p class="tag">性别:{{ user.gender }}</p>
            <p class="email">邮箱:{{ user.email }}</p>
            <p class="email">状态:{{ user.status }}</p>
            <p class="email">职级:{{ user.level }}</p>
            <p class="createTime">入职时间:{{ user.createTime }}</p>
            <p class="updateTime">最近更新:{{ user.updateTime }}</p>
            <p class="contributor">特别感谢：<br>
              <span>亲戚朋友们的支持</span>
            </p>
            <p v-if="currentUser.username == user.username" id="buttons">
                <el-button type="info" @click="edit(currentUser.id)">编辑</el-button>
                <el-button type="primary">修改</el-button>
            </p>
        </div>
    </div>
    <div class="dialogToEditUser" v-if="dialogToEditUserVisible">
        <fieldset>
            <div>
                <label for="username">用户名称</label> <br>
                <input type="text" id="username" name="username"
                    placeholder="请输入名字"
                    v-model="currentUser.username"   
                    disabled 
                >
            </div>
            <!-- <div>
                <label for="password">密码</label> <br>
                <input type="text" id="password" name="password"
                    placeholder="请输入密码" 
                    v-model="currentUser.password"   
                >              
            </div> -->
            <div>
                <label for="email">邮箱</label> <br>
                <input type="text" id="email" name="email"
                    placeholder="请输入邮箱" 
                    v-model="currentUser.email"   
                >
            </div>
            <div>
                <label for="gender">性别</label> <br>
                <select id="gender" v-model="currentUser.gender">
                    <option value="男">♂</option>
                    <option value="女">♀</option>
                </select>
            </div>
            <div>
                <label for="level">职级</label> <br>
                <select id="level" v-model="currentUser.level">
                    <option value="10">职员</option>
                    <option value="20">经理</option>
                </select>
            </div>
            <div>
                <label for="status">状态</label> <br>
                <select id="status" v-model="currentUser.status">
                    <option value="1">启用</option>
                    <option value="0">禁用</option>
                </select>
            </div>
            <div class="editButton"> 
                <el-button type="info" @click="dialogToEditUserVisible=false">取消</el-button>
                <el-button type="primary" @click="detemine()">确认</el-button>
            </div>
        </fieldset>
    </div>
</template>

<script setup>
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { onMounted, ref } from 'vue';
import qs from 'qs'


const searchForm = ref({
   username:'',
   status:'' 
});


var currentUser = window.getUser();
const users = ref([]);

const dialogToEditUserVisible = ref(false);

function submit() {
   console.log(searchForm.value);
   let data = qs.stringify(searchForm.value);
   axios.get(BASE_URL+'/v1/user/query?'+data)
   .then((response)=>{
    if (response.data.code == 2000) {
        ElMessage.success('检索成功！');
        users.value = response.data.data;
    }
    else {
        ElMessage.error('检索失败！');  
    }
   });
}

function loadUsers() {
    axios.get(BASE_URL+'/v1/user/query')
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('操作成功！');
            users.value = response.data.data;
            console.log(users.value);
        }
        else {
            ElMessage.error('错误操作！');
        }
    });
}

onMounted(()=>{loadUsers()});

function edit(id) {
    console.log(id);
    dialogToEditUserVisible.value = true;
}

function detemine() {
    console.log(currentUser);
    axios.post(BASE_URL+'/v1/user/save',currentUser)
    .then((response)=>{
        if (response.data.code == 2000) {
            ElMessage.success('更新成功！');
            dialogToEditUserVisible.value = false;
            loadUsers();
        }else {
            ElMessage.error('错误！');
        }
    });
}

function deleteUser(id) {
    if (confirm('你是否想要删除此用户？')) {
        axios.post(BASE_URL+'/v1/user/delete/'+id)
        .then((response)=>{
            if (response.data.code == 2000) {
                ElMessage.success('删除成功');
                loadUsers();
            }
            else {
                ElMessage.error('删除失败');
            }
        });
    }

}


function reset() {
    searchForm.value = {};
    loadUsers();
}

</script>

<style>

.search-card {
    margin: 40px 0;
    padding: auto;
    display: flex;
    justify-content: center;
    gap: 20px;
    
}

.search-card label {
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

#buttons {
    background-color: #666;
}

#username,
#email {
    border: none;
    width: 200px;
    height: 30px;
    border-bottom: 1px solid #666;
    color: #666;
    background-color: rgb(244, 244, 244);
}

#username:valid,
#email:valid {
    border-bottom-color: var(--valid-color);
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


.dialogToEditUser {
    position: fixed;
    top: 20%;
    bottom: 20%;
    left: 30%;
    right: 30%;
    height: 60%;
    padding: 20px;
    background-color: #666;
    border-radius: 10px;
}



</style>
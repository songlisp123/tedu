<template>
    <el-container style="height: 100vh;background-image: linear-gradient(135deg,#1352f2,#3c82f5);">
        <el-header style="padding: 20px;min-height: 15vh; height: 10vh;">
            <el-row :gutter="10">
                <el-col :span="3" >
                    <img src="../../public/imgs/login/logo.png" alt="图片为加载">
                </el-col>
                <el-col :span="1">
                    <img src="../../public/imgs/login/auto.png" alt="图片为加载">
                </el-col>
                <el-col :span="1">
                    <img src="../../public/imgs/login/verticalLine.png" 
                    style="width: 50px;" alt="未加载"> </el-col>
                <el-col :span="19">
                    <p style="text-align: left;font-size: 20px;">智能车辆管理</p>
                </el-col>
            </el-row>
        </el-header>
        <el-main>
            <el-row>
                <el-col :span="16" :xs="24" :sm="24" :md="16" :lg="16" :xl="16" >
                    <img src="../../public/imgs/login/bg.png" alt="图片未加载" 
                    style="width: 100%;margin-top: 60px;">
                </el-col>
                <el-col :spqn="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                    <el-card>
                        <h3 style="text-align: center;padding: 10px 0;;">登陆界面</h3>
                        <el-form>
                            <el-form-item>
                                <label for="userlogin">用户名(邮箱):</label> <br>
                                <el-input placeholder="请输入您的用户名称或邮箱:" id="userlogin"
                                v-model="userloginpara.loginPara"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <label for="password">密码：</label>
                                <el-input placeholder="输入密码:" id="password" show-password
                                v-model="userloginpara.password"></el-input>
                            </el-form-item>
                                <p><el-button type="primary" @click="login()">登录</el-button></p>
                        </el-form>
                            <p>测试用户：tom,密码或者邮箱：132456(tom@example.org)</p>
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
    </el-container>
</template>

<script setup>
import axios from 'axios';
import { ref } from 'vue';
//定义一个参数接收用户输入的值
const userloginpara = ref({
    loginPara:'',
    password:''
});

//定义函数
function login() {
    console.log(userloginpara.value);
    axios.post(BASE_URL+'/v1/user/login',userloginpara.value)
    .then((response)=>{
        if (response.data.code == 2000) {
            console.log('登录成功！');
            localStorage.setItem('user',JSON.stringify(response.data.data));
            userloginpara.value = {};
        }else {
            console.log('登录失败');
        }
    });
};

//登录界面已经ok现在开始写后端


</script>

<style>
label {
    font-size: 15px;
}


</style>
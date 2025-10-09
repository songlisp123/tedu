<template>
    <!-- <h1>登录界面</h1> -->
    <!-- 接下来就是写需求！ -->
     <!-- 需求就是
      1、登录界面，盛满全屏高
      2、全屏背景色
      3、溢出隐藏 -->
      <el-container style="height: 100vh; background-image: linear-gradient(135deg,#1352f2,#3c82f5);overflow: hidden;">
        <el-header style="padding: 20px;">
            <el-row :gutter="10">
                <el-col :span="3">
                    <img src="../../public/imgs/login/logo.png" alt="图片暂未找到">
                </el-col>
                <el-col :span="1">
                    <img src="../../public/imgs/login/auto.png" alt="图片暂未找到">
                </el-col>
                <el-col :span="1">
                    <img src="../../public/imgs/login/verticalLine.png" alt="图片暂未找到" style="height: 50px;">
                </el-col>
                <el-col :span="19">
                    <p>智慧车辆运营管理</p>
                </el-col>
            </el-row>
        </el-header>
        <el-main>
            <el-row>
                <el-col :span="16" :xs="24" :sm="24" :md="16" :lg="16" :xl="16">
                    <img src="../../public/imgs/login/bg.png" alt="图片未加载"
                    style="width: 100%;margin-top: 60px;">
                </el-col>
                <el-col :span="8" :xs="24" :sm="24" :md="8" :lg="8" :xl="8">
                    <el-card :span="8">
                        <h3 style="text-align: center;">用户登录</h3>
                        <el-form label-position="top">
                            <el-form-item label="用户名">
                                <el-input placeholder="请输入用户名" v-model="user.username"></el-input>
                            </el-form-item>
                            <el-form-item label="密码">
                                <el-input placeholder="请输入密码" show-password v-model="user.password"></el-input>
                            </el-form-item>
                            <el-form-item>
                                <el-button @click="f1()" style="width: 100%; background-color: #666; color: #fff;">登录</el-button>
                            </el-form-item>
                        </el-form>
                        <div style="text-align: center; font-size: 14px; color: #666; padding: 15px 0;">
                            测试用户：{{ user.username }},密码：{{ user.password }}
                        </div>
                    </el-card>
                </el-col>
            </el-row>
        </el-main>
      </el-container>
</template>

<script setup>
import router from '@/router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
const user = ref({
    username:'',
    password:''
});
function f1() {
    console.log(user.value);
    axios.post(BASE_URL+"/v1/user/login",user.value)
    .then((response)=>{
        console.log(Response.data);
        console.log(response.data);
        // 如果用户名和密码正确则重定向到根目录
        if (response.data.code == 2000) {
            ElMessage.success("登录成功！");
            //将登陆用户的信息查询出来！
            localStorage.setItem('user',JSON.stringify(response.data.data));
            router.push("/"); //重定向到根页面
        } else if (response.data.code == 3005) {
            ElMessage.error(response.data.message);
        }else {
            ElMessage.error(response.data.data);
        };
    });
};

</script>

<style>
el-form-item {
    border: 0;
    border-bottom: 1px solid gray;
}
</style>
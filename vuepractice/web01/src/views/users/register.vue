<template>
<div>
    <h1>注册</h1>
    <fieldset>
        <div class="top">
            <label for="username">用户名：<span>*</span></label> <br>
            <input type="text" id="username"
            name="username" placeholder="请输入用户名称" required>
        </div>
        <div>
            <label for="password">密码：<span>*</span></label> <br>
            <input type="text" id="password"
            name="password" placeholder="请输入用户密码" required>
        </div>
        <div>
            <label for="password">确认密码：<span>*</span></label> <br>
            <input type="text" id="confirmPassword"
            name="confirmPassword" placeholder="请输入用户密码" required>
        </div>
        <div>
            <label for="nickname">昵称：</label><br>
            <input type="text" id="nickname"
            name="nicaname" placeholder="请输入用户昵称：" required>
        </div>
        <div>
            <label for="phone">手机号码：</label><br>
            <input type="text" id="phone" 
            name="phone" placeholder="输入手机号" required>
        </div>
        <div>
            <label for="email">电子邮箱：</label><br>
            <input type="text" id="email" 
            name="email" placeholder="输入邮箱号" required>
        </div>
        <div>
            <button @click="f1()">提交</button>
        </div>
    </fieldset>
</div>
</template>

<script setup>
import { ref } from 'vue';

//第一步，获取用户输入框内的所有参数，并绑定到用户参数上
//这一步必须十分小心，因为作为请求体中的数据，将会与后端注册参数一一绑定
const userReg = ref({
    username:'',
    password:'',
    nickname:'',
    phone:'',
    email:''
});

function f1() {
    //第二步让我们从dom中获取用户输入的参数，因为我们在username
    //password和confirmpassword中设置了input的required属性，所以我们不需要着重
    //验证，我们主要验证其余的字段。首先获取所有的input标签
    
    let username = document.getElementById('username');
    let password = document.getElementById('password');
    let nickname = document.getElementById('nickname');
    let phone = document.getElementById('phone');
    let email = document.getElementById('email');

    //然后为userreg参数赋值
    userReg.value.username = username.value;
    userReg.value.password = password.value;
    userReg.value.nickname = nickname.value;
    userReg.value.phone = phone.value;
    userReg.value.email = email.value;

    //向后端发送请求
    fetch("http://localhost:8085/v1/user/register",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"   
        },
        body:JSON.stringify(userReg.value)
    })
    .then(res => res.json())
    .then(data => {
        console.log(data.data);
    });
}
</script>

<style>
button {
    background-color: #666;
    border: 0;
    color: white;
    height: 50px;
    width: 50px;
    border-radius: 50%;
    transition: 0.5s;
    text-transform: uppercase;
}

button:hover {
    background-color: orange;
}
</style>
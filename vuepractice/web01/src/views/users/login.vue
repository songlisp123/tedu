<template>
    <div class="login">
        <!-- <p v-if="inputValid==1" class="message"><span>欢迎您,{{ user.auth }}</span></p> -->
        <p v-if="inputValid == -1" class="message error"><span>登录失败</span></p>
        
        <fieldset>
            <h1>登录</h1>
            <div class="top">
                <label for="username">用户名(邮箱):<span>*</span>  </label> <br>
                <input type="text" name="username" id="username" placeholder="请输入用户名称(邮箱)：" required>
            </div>
            <div>
                <label for="password">密码:<span>*</span>  </label> <br>
                <input type="text" name="password" id="password" placeholder="请输入用户密码：" required>
            </div>
            <button id="button" @click="f1()">提交</button>
        </fieldset>
    </div>
</template>

<script setup>
import {ref} from "vue";
import router from '@/router';
import axios from 'axios';

    const user = {
        auth:'',
        password:''
    };
    var inputValid = ref(0);

    var time = ref('');

    function f1() {
        let auth = document.getElementById('username');
        let password = document.getElementById('password');

        user.auth = auth.value;
        user.password = password.value;


        fetch("http://localhost:8085/v1/user/login",{
            method:"POST",
            headers:{
                "Content-Type":"application/json"
            },
            body:JSON.stringify(user)
        })
        .then(res => res.json())
        .then(data => {
            if (data.data != null) {
                inputValid.value = 1;
                //此处的第一个data是服务端反送给客户端的数据
                //第二个data则是封装的返回参数
                //建议对需要返回数据的内容进行封装  
                localStorage.setItem('user',JSON.stringify(data.data));
                //读取封装的用户信息
                user.auth = data.data.username
                //获取当前时间戳
                //在我们的程序中，这个时间戳的作用是，将报错信息持续化显示。
                const now = new Date();
                time.value = now;
                //settimeout函数是一个回调函数，当时间超过设置的毫秒的时候，
                //就会调用此函数
                setTimeout(() => {
                    inputValid.value = 0;
                    time.value = '';
                },2000);
                // return;
                //rount.push函数将会重定向到给定的url路径
                router.push("/");
            }
            console.log(inputValid);
            console.log(user);
            const now =  new Date();
            time.value = now;
            inputValid.value = -1;
            setTimeout(() => {
                    inputValid.value = 0;
                    time.value = '';
                },2000);
            auth.value = '';
            password.value = '';
        });
    };

    

</script>

<style>

        .login p {
            margin-top: 15px;
            font-size: 15px;
            font-weight: lighter;
        }

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
        

</style>
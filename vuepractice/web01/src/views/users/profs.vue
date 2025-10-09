<template>
    <h1>个人主页</h1>
    <p>用户名：{{ user.username }}</p>
    
    <p>邮箱：{{ user.email }}</p>

    <p>电话：{{ user.phone }}</p>
    <div class="buttongroup">
        <button @click="f1()">修改信息</button>
        <button @click="f2()">修改密码</button>
    </div>

</template>

<script setup>
import router from '@/router';
import { ref } from 'vue';

    const user = ref('');
    console.log(user.value);
    user.value = JSON.parse(localStorage.user);
    console.log(user.value);

    function f1() {
        console.log('跳转到修改信息页面……');
        router.push('/changeprofiles');
    };

    function f2() {
        console.log('用户进入到修改密码的信息页面……');
        router.push('/changepasswordconfirm')
    }
</script>

<style>

    .buttongroup {
        text-align: center;
    }

    button {
        background-color: #ffffff;
        height: 50px;
        width: 100px;
        color: #666;
        font-size: 15px;
        border: 0;
        border-bottom: 1px solid rgb(12,75,31);
        margin-right: 15px;
    }

    button:hover {
        color: rgb(0, 0, 0);
        cursor: pointer;
    }
</style>
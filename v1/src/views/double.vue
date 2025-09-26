<template>
    <h1>双向绑定</h1>
    <input type="text" v-model="info">
    <button @click="console.log(info)">难受</button>
    <br>
    <button @click="f1()">点击我试试？</button>
    <br>
    <input type="text" placeholder="默认是？" v-model="user.username"> <br>
    <input type="text" placeholder="默认是？" v-model="user.password"> <br>
    <br>
    <hr>
    <input type="text" placeholder="默认是？" v-model="user.nickname">
    <br>
    <button @click="f2()">点击我？</button>
    <br>
    
    <input type="radio" name="gender" value="0" v-model="user.gender" id="woman">
    <label for="woman">女：</label>
    
    <input type="radio" name="gender" value="1" v-model="user.gender" id="man">
    <label for="man">男：</label>
    <br>
    <button @click="f3()">登录？</button>
</template>

<script setup>
    let j = 0;
    import { ref } from 'vue';
    const info = ref('这是一个字符串');
    function f1() {
        j++;
        info.value=j;
    }
    const user = ref({
        username:'',
        password:'',
        nickname:'',
        gender:''
    });
    function f2() {
        user.value.username='宋宁龙';
        user.value.password='A0116659';
        user.value.nickname='小宋';
    };
    function f3() {
        console.log(user.value);
    }
</script>

<style>

</style>
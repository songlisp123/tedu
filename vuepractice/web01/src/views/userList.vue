<template>
    <h2>登录成功！</h2>
    <br>
    <button @click="getUserList">提交</button>
    <table>
        <tr>
            <th>用户编号</th>
            <th>用户名称</th>
            <th>用户密码</th>
            <th>用户电话</th>
        </tr>
        <tr v-for="user in users">
            <td>{{ user.id }}</td>
            <td>{{ user.username }}</td>
            <td>{{ user.password }}</td>
            <td>{{ user.phone }}</td>
        </tr>
    </table>
</template>

<script setup>
import { ref } from 'vue'
const users = ref([]);
function getUserList() {
    fetch("http://localhost:8085/v1/user/list")
    .then(res => res.json())
    .then(data => {
        users.value = data.data;
    });
}
</script>

<style>

</style>
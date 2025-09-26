<template>
    <div class="container">
        <h1>家庭作业</h1>
        <label for="username">姓名：</label>
        <input type="text" v-model="user.usernmae" id="username" required>
        <br>
        <label for="salary">薪资：</label>
        <input type="text" v-model="user.salary" id="salary" required>
        <br>
        <label for="job">工作职位：</label>
        <input type="text" v-model="user.job" id="job" required>
        <br>
        <button type="submit">提交</button>
        <table border="1">
            <caption>表头</caption>
            <tr>
                <th>名称</th>
                <th>薪水</th>
                <th>工作</th>
            </tr>
            <tr>
                <td>{{ user.usernmae }}</td>
                <td>{{ user.salary }}</td>
                <td>{{ user.job }}</td>
            </tr>
        </table>
        <br>
        <button @click="f1()">点击我？</button>
    </div>

</template>

<script setup>
import { ref } from 'vue';
const user = ref({
    usernmae:'',
    salary:'',
    job:''
});
function f1() {

};
</script>

<style>
    .container {
        display: flex;
        flex-direction: column;
        align-items: center;
    }
    table {
        text-align: center;
    }
</style>
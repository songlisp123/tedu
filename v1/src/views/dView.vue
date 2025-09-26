<template>
    <h1>循环练习？</h1>
    <ul>
        <li v-for="item in 3">{{ info }}{{ item }}</li>
    </ul>
    <ul v-for="value in 5">
        <li>你好{{ value }}</li>
        <li>你好{{ value }}</li>
        <li>你好{{ value }}</li>
    </ul>
    <table border="1">
        <caption>表头</caption>
        <tr>
            <th>编号</th>
            <th>名称</th>
            <th>价格</th>
            <th>库存</th>
        </tr>
        <tr v-for="(e,index) in arr">
            <td>{{ index+1 }}</td>
            <td>{{ e.title }}</td>
            <td>{{ e.price }}</td>
            <td>{{ e.num }}</td>
            <td><button @click="del(index)">删除</button></td>
        </tr>
    </table>
</template>

<script setup>
import { ref } from 'vue';
const info = ref('探索，世界');
const arr = ref([
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
    {title:'小米',price:12500,num:100},
]);

function del(index) {
    if (confirm('确定删除？'))
        arr.value.splice(index,1);
}
</script>


<style>

</style>
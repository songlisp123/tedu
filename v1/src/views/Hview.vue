<template>
    <h1>猜数字练习</h1>
    <table border="1">
        <caption>个人简历</caption>
        <tr>
            <td>照片</td>
            <td><img :src="person.imgUrl" alt="图片加载失败"></td>
        </tr>
        <tr>
            <td>姓名</td>
            <td>{{ person.name }}</td>
        </tr>
        <tr>
            <td>年龄</td>
            <td>{{ person.age }}</td>
        </tr>
        <tr>
            <td>好友</td>
            <td>
                <ul>
                    <li v-for="f in person.friends">
                        {{ f }}
                    </li>
                </ul>
            </td>
        </tr>
    </table>
    <br>
    <button @click="f1()">点击加载数据</button>
</template>

<script setup>
import { ref } from 'vue';
const person = ref({
    name:'',
    age:'',
    imgUrl:'',
    friends:[]
});

function f1() {
    person.value.name='宋宁龙';
    person.value.age=18;
    person.value.imgUrl='2.png';
    person.value.friends = ['1','2','3','4'];
};
</script>

<style>
    * {
        text-align: center;
        align-items: center;
    }
    img {
        width: 100px;
    }
    ul {
        list-style: none;
        margin: 0;
        padding: 0;
    }
</style>
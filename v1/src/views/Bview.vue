<template>
    <h2>属性绑定指令</h2>
    <input type="text" value="username">
    <br>
    <input type="text" v-bind:value="username">
    <br>
    <input type="text" :value="username">
    <br>
    <a :href="url">京东？</a>
</template>

<script setup>
//导包
import { ref } from 'vue';
const username = ref('rose');
const url = ref('https://www.jd.com');
</script>

<style scoped>
</style> 
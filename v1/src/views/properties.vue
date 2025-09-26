<template>
    <h1>属性绑定练习</h1>
    <p>{{ info }}</p>
    <p v-html="info"></p>
    <hr>
    <br>
    <input type="text" v-bind:value="info">
    <input type="text":value="info" />
    <hr>

</template>

<script setup>
import { ref } from 'vue';
const info = ref('你这个傻逼');

</script>

<style>
</style>
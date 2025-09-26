<template>
    <h1>事件绑定效果</h1>
    <button @click="f1()">点击</button>
    <input type="text" placeholder="你妈？" @keydown.enter="f2()">
    <br>
    <hr>
    <label for="date">当前日期：</label>
    <input type="date"  @click="f3()" id="date">
</template>

<script setup>
function f1() {
    console.log('点击按钮');
};
function f2() {
    console.log('安吉你妈？');
};
function f3() {
    console.log('正在挑选时间？');
};
</script>

<style></style>
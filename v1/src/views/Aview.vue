<template> 
    <h1>这是一个测试</h1>
    <h2>我们的第一个组件</h2>
    <span>{{ info }}</span>
    <hr>
    <button @click="f1()">点击我</button>
    <hr>
    <label for="count">当前数字：</label>
    <p id="count">{{ count }}</p>
    <button @click="f2()">点击我？</button>
    <button @click="f3()">点击？</button>
    <hr>
    <p>{{ message }}</p>
    <p v-text="message"></p>
    <p v-html="message"></p>
</template>

<script setup>
    import { ref } from 'vue';
    const info = ref('你好，师姐');
    function f1() {
        console.log(info);
        console.log(info.value);
        info.value='这是一个新的值';
    }

    const count = ref(0);
    function f2() {
        console.log(count.value);
        count.value++;
    }

    function f3() {
        count.value--;
    }

    const message = ref('你妈的<b>逼！</b>')



</script>

<style scoped>
</style>
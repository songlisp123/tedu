<template>
    <div style="height: 6vh;background-color:rgb(12, 75, 51);padding:10px 10%;">
            <span style="line-height:60px;font-size:20px;">围栏地图</span>
            <el-button type="primary" style="float:right;margin-top:13px;"
            @click="router.go(-1)">返回</el-button>
    </div>
    <div style="width: 100vw; height: 80vh; " id="mapContainer"></div>
</template>

<script setup>
import router from '@/router';


</script>

<style>
</style>
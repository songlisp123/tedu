<template>
    <h1>猜数字练习</h1>
    <p>爱谁猜谁猜</p>
    <button>按钮</button>
    <el-button>
        按钮
    </el-button>
    <!-- <div>
    <el-button type="primary" :icon="Edit" />
    <el-button type="primary" :icon="Share" />
    <el-button type="primary" :icon="Delete" />
    <el-button type="primary" :icon="Search">Search</el-button>
    <el-button type="primary">
      Upload<el-icon class="el-icon--right"><Upload /></el-icon>
    </el-button>
  </div> -->
   <div class="button-row">
      <el-button :icon="Search" circle />
      <el-button type="primary" :icon="Edit" circle />
      <el-button type="success" :icon="Check" circle />
      <el-button type="info" :icon="Message" circle />
      <el-button type="warning" :icon="Star" circle />
      <el-button type="danger" :icon="Delete" circle />
    </div>
</template>

<script setup>
import { Delete, Edit, Search, Share, Upload } from '@element-plus/icons-vue'
</script>

<style></style>
<template>
    <h1>这是一个简单的element-plus练习</h1>
    <el-button>按钮</el-button>
    <hr>
    <br>
      <!-- 开关只支持布尔类型的值
  如果想要扩展为数字或字符串类型，需要设置active-value和inactive-value属性来控制 -->
  <el-switch v-model="value1" :active-value="1" :inactive-value="0"></el-switch>
  <el-switch v-model="value2" :active-value="'1'" :inactive-value="'0'"></el-switch>
  <el-switch v-model="value2" active-value="1" inactive-value="0" ></el-switch>
  <br>
  
</template>
<script setup>
import {ref} from "vue";

const isShow = ref(true);
const value1 = ref(1);
const value2 = ref('1');
function f() {
    console.log('开关状态被修改了……');
};

</script>

<style></style>
<!-- 这是一个公共组件控制除登录页的公共头部以外的所有子组件和左侧菜单栏 -->
<template>
  <!-- <h2>这是登录后要去的主页</h2> -->
  <div style="display: flex;">
    <div :style="{width:(isCollapse ? '64px' : '208px')}">
      
        <!-- 左侧菜单栏 -->
         <!-- 关于歌属性的说明 -->
          <!-- router属性：可以为子菜单设置的index属性与路径绑定，并跳转到该路径 -->
          <!-- active-text-color：当点击事件发生时，会触发该事件源的字体颜色为给定的颜色 -->
           <!-- :collapse一个时间绑定，随时监听isCollapse属性的值，并根据值调整页面的布局 -->
           <!-- collapse-transition="false":属性折叠过度效果这个效果是取消当前过渡时间  -->
           <!-- unique-opened：这个属性是在同时只能打开一个菜单，对于具有多个菜单的项目，请使用此致 -->
         <el-menu router
          background-color="#3282f5"
          style="width:100%;height:100vh;"
          text-color="#fff"
          active-text-color="#ff0"
          :collapse="isCollapse"
          :collapse-transition="false"
          unique-opened>
        <!--  左侧菜单栏顶部条  -->
          <!-- el-row便签代表着当前元素独占一行 -->
        <el-row style="padding-top: 10px;">
          <!-- 左侧占位元素 -->
          <el-col :span="2"></el-col>
          <el-col :span="7" style="padding-left: 7px;">
            <!-- 元素avatar的作用是：将用户上传的图片自动剪切为圆形图片 -->
            <el-avatar src="/imgs/admin/logo.png" style="width: 35px;height: 35px;"></el-avatar>
          </el-col>
          <el-col :span="isCollapse ? 0 : 13">
            <span style="color:#fff;font-weight: bolder;position:relative;top:8px;left: 2px;">
              智慧车辆
            </span>
          </el-col>
        </el-row>
        <!-- 顶级子菜单 -->
         <!-- index属性表示菜单的顺序，默认按照升序操作 -->
          <el-sub-menu index="1">
            <!-- template便签中的#title属性代表着该菜单的显示标题 -->
            <template #title> 
              <!-- el-icon是element中的素材库 -->
              <el-icon style="font-size: 20px;"><User /></el-icon><span>用户</span>  
            </template>
            <!-- 子菜单中的子项目 -->
            <el-menu-item index="/user">用户列表</el-menu-item>  
          </el-sub-menu>
          <el-sub-menu index="2">
            <template #title>
              <el-icon style="font-size: 20px;"><Van /></el-icon><span>车辆</span>  
            </template>
            <el-menu-item index="/vehicle">车辆列表</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="3">
            <template #title>
              <el-icon style="font-size: 20px;"><Location /></el-icon><span>围栏</span>  
            </template>
            <el-menu-item index="/geofence">围栏列表</el-menu-item>
            
          </el-sub-menu>
          <el-sub-menu index="4">
            <template #title>
              <el-icon style="font-size: 20px;"><Location /></el-icon><span>调度管理</span>  
            </template>
            <el-menu-item index="/application">用车申请</el-menu-item>
            <el-menu-item index="/audit">申请匹配</el-menu-item>
            <el-menu-item index="/distribute">车辆分配</el-menu-item>
          </el-sub-menu>
          <el-sub-menu index="5">
            <template #title>
              <el-icon style="font-size: 20px;"><Location /></el-icon><span>字典</span>  
            </template>
            <el-menu-item index="/dict">字典xx</el-menu-item>
            
          </el-sub-menu>
         </el-menu>
    </div>
    <div :style="{width:(isCollapse ? 'calc(100% - 64px)' : 'calc(100% - 208px')}">
        <!-- 右侧菜单栏 -->
         <!-- 一个顶部条框包含菜单按钮和用户显示 -->
          <el-header style="padding-top: 22px;height: 10vh;background-color: #fff;">
            <el-row>
              <el-col :span="18">
                <el-icon style="font-size: 20px;" @click="changeCollapse()"><Expand /></el-icon>
              </el-col>
              <el-col :span="6" style="position: relative;">
                <!-- el-dropdown标签主要作用有以下：
                 一个用以下菜单的选项标签和下拉箭头
                 一个用来显示下拉操作的下拉框
                  -->
                <el-dropdown style="position: absolute;right: 20px;">
                  <span style="font-size: 16px;font-weight: bold;">
                    {{ user.username }}
                    <el-icon><arrow-down /></el-icon>
                  </span>
                  <template #dropdown>
                    <el-dropdown-menu>
                      <el-dropdown-item @click="logout()" class="logout">退出登录</el-dropdown-item>
                    </el-dropdown-menu>
                  </template>
                </el-dropdown>
              </el-col>
            </el-row>
            <!--  面包屑导航 -->
            <el-breadcrumb separator=">" style="position: relative;top: 20px;">
              <el-breadcrumb-item>首页</el-breadcrumb-item>
              <el-breadcrumb-item v-for="item in breadcrumb">{{ item }}</el-breadcrumb-item>
            </el-breadcrumb>
          </el-header>
         <!-- 一个主要页面用来显示主要内容 -->
          <el-main style="height: 90hv;padding: 0;overflow-y: auto;">
            <router-view />
          </el-main>
    </div>
  </div>
</template>

<!-- 总结一点： -->
<!-- menu 》sub》menu-item -->

<script setup>
import router from '@/router';

import { onMounted, ref } from 'vue';
import { useRoute, useRouter } from 'vue-router';

// @ is an alias to /src
// import HelloWorld from '@/components/HelloWorld.vue'

// export default {
//   name: 'HomeView',
//   components: {
//     HelloWorld
//   }
// }

const isCollapse = ref(false);
function changeCollapse() {
  isCollapse.value = !isCollapse.value;
}

//获取当前用户
const user = ref(getUser());

/**
 * 清空当前用户登录数据的方法，用于用户退出登录
 */
const logout = () => {
  localStorage.removeItem('user');
  user.value='';
  router.push('/login');
};

const breadcrumb = ref([]);
let map = {
  '/user':['用户','用户列表'],
  '/vehicle':['车辆','车辆列表'],
  '/geofence':['围栏','电子围栏'],
  '/application':['调度','申请'],
  '/audit':['调度','审核'],
  '/distribute':['调度','分配'],
  '/dict':['字典','字典列表'],
  '/geofenceMap':['围栏管理','绘制围栏'],
  '/dictOption':['字典管理','字典项管理'],

};

//获取当前用户路由对象默认的是url端口后面的地址
const route = useRoute();
const updateBread = (route)=>{
  //获取接口的路径
  let path = route.path;
  console.log(path);  
  //从路由映射中找到当前的路径列表
  breadcrumb.value = map[path];
};

//页面渲染完毕后会触发该事件
onMounted(()=>{
  updateBread(route);
});

//获取路由守卫
const arouter = useRouter();
arouter.beforeEach((to,from,next)=>{
  updateBread(to);
  next();
});
</script>


<style>

</style>

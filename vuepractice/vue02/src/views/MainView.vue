<template>
    <div id="container">
      <!-- 左侧导航栏 -->
       <div class="left">
         <aside>
            <div id="menu-header">
               <div>
               <img src="../../public/svg/documents.svg" alt="Documents Menu" class="icon doc">
               <div>
                  <span>Docs</span>
                  <span>email@email.com</span>
               </div>
               </div>
               <img src="../../public/svg/left.right.svg" alt="Open/Close Menu" class="icon open-close">
            </div>
            <ul>
               <li><a href="/users"><img src="../../public/svg/new.svg" alt="New Document" class="menu-icon"><span>用户管理</span></a></li>
               <li><a href="/vehivle"><img src="../../public/svg/recent.svg" alt="Open recent" class="menu-icon"><span>车辆管理</span></a></li>
               <li><a href="/geofence"><img src="../../public/svg/recent.svg" alt="Open recent" class="menu-icon"><span>围栏管理</span></a></li>
               <li><a href="/dict"><img src="../../public/svg/recent.svg" alt="Open recent" class="menu-icon"><span>字典管理</span></a></li>
               <li><a href="#"><img src="../../public/svg/search.svg" alt="Search documents" class="menu-icon"><span>Search</span></a></li>

            </ul>
         </aside>
       </div>
       <!-- 右侧内容区 -->
        <div class="right">
            <p class="hello" v-if="user == null">
               <a href="/login">登录</a>  | <a href="#">注册</a></p>
            <p class="hello" v-if="user != null">欢迎您，{{ user.username }}  |
               <a href="/change/password/confirm">重置密码</a>  | <button @click="logout()" id="logout">退出登录</button>
                 | <a href="/">注销此用户</a>
            </p>
            <div style="padding: 0 10%; border-bottom: 1px solid #fff;"></div>
            <div>
               <router-view />
            </div>
        </div>
    </div>
    <!-- 这个区域是可变区域 -->

</template>

<script setup>
import router from '@/router';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';
const user = ref({});
user.value = window.getUser();

function logout() {
   localStorage.removeItem('user');
   ElMessage.success('退出登录成功！');
   user.value = window.getUser();
}
</script>

<style>
* {

  margin: 0;
  padding: 0;
  box-sizing: border-box;

}

:root {
  font-size: 62.5%;
}

body {
  font-family: 'Roboto', sans-serif;
  font-size: 1.6rem;
  line-height: 1.6;
  background-color: #f4f4f4;
  color: #333;
}

.right {
   width: 100vw;


}
aside {
  width: 34rem;
  background-color: #093c9b;
  color: #fff;
  padding: 1rem;
  height: 100vh;
  position: absolute;
    left: -27rem; 
  transition: all 0.7s ease;
  overflow: hidden;
  z-index: 100;

  
}

article {
  position: relative;
  padding: 1rem;
  left: 8rem;
}

a {
  color: #fff;
  text-decoration: none;
}

.icon {
   width: 20px;
  height: 20px;
}

#menu-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  padding: 1rem;
  background-color: #093c9b;
  border-radius: 1rem;
  cursor: pointer;
}

#menu-header img {
  width: 30px;
  height: 30px;
}

#menu-header div:nth-child(1) {
  display: flex;
  gap: 1.7rem;
  align-items: center;
  justify-content: left;
}
#menu-header div:nth-child(1) div {
  display: flex;
  flex-direction: column;
  align-items: left;
}



ul li {
  list-style: none;
  padding: 1rem 1.5rem;
  background-color: #053081;
  border-radius: 1rem;
  margin: 10px 60px 0 0px;
  transition: all 0.7s ease;

}

ul li a {
  display: flex;
  gap: 2rem;
  align-items: center;
  justify-content: center;
}
.menu-icon, .icon {  
   width: 20px;
  height: 20px;
  transition: all 0.7s ease;
  transform: scale(1);
}

.doc {

  color: #0275c2;
}

/* ANIMATIONS STARTS HERE */

aside:hover  {
  transform: translateY(100);
  left: 0rem;
}

ul li:hover {
  background-color: #0275c2; 
  box-shadow: 5px 5px 5px rgba(0, 0, 0, 0.2);
  transform: scale(1.02);
}

ul li:hover .menu-icon, .icon:hover {  
  transform: scale(1.2);
  filter: none
}

/* 这是自定义的css代码 */
.hello {
   padding: 20px 0;
   padding-right: 20%;
   text-align: right;
   color: #fff;
   font-size: 1.1em;
   font-weight: bold;
   background-color: rgb(12, 75, 51);
}

.hello a:hover {
   text-decoration: underline;
   font-size: 0.95em;
}



#logout {
   background-color: rgba(0, 0, 0, 0);
   border: none;
   font-size: 1em;
}

#logout:hover {
   text-decoration: underline;
}



</style>
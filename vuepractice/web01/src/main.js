import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'

createApp(App).use(store).use(router).mount('#app')


//10.获取当前登录人数据的方法
//登录成功后,会将用户数据以字符串键值对的方式存入localStorage中
//设置一个全局方法,如果获取到了登录人数据,返回登录人JS对象,否则返回null
// window.getUser = ()=>{
//     return localStorage.user ? JSON.parse(localStorage.user) : null;
// }

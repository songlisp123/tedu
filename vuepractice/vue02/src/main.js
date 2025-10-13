import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//你必须导入这两样才能使用该库
import ElementPlus, { ElMessage } from 'element-plus'
import 'element-plus/dist/index.css'
createApp(App).use(ElementPlus).use(store).use(router).mount('#app')


const BASE_URL = 'http://localhost:8060'
window.BASE_URL = BASE_URL;

//10.获取当前登录人数据的方法
//登录成功后,会将用户数据以字符串键值对的方式存入localStorage中
//设置一个全局方法,如果获取到了登录人数据,返回登录人JS对象,否则返回null
window.getUser = ()=>{
    return localStorage.user ? JSON.parse(localStorage.user) : null;
}


//8.配置路由守卫
//to 即将要去的路由对象
//from 当前即将离开的路由对象
//next() 放行函数,必须调用才能跳转
router.beforeEach((to, from, next) => {
    //获取当前登录的用户数据
    let user = localStorage.user;
    //如果当前要进入的路由不是登录页,并且当前用户没有登录,则强制跳转到登录页
    if(to.path === '/realhome' || to.path === '/home'){
        next();//如果用户去的就是登录页或用户已登录,则直接放行
    }
    else if(to.path !== '/login' && !user){
        next({path:'/login'},ElMessage.warning('请先登录！'));
    }
    else {
        next();
    }
})
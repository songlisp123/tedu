import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//1.引入element-plus组件库与其样式
import ElementPlus, { ElMessage } from 'element-plus'
import 'element-plus/dist/index.css'
//2.引入element-plus图标库,并起别名为ElementPlusIconsVue
import * as ElementPlusIconsVue from '@element-plus/icons-vue'
//6.1修改elementPlus默认中文
import zhCn from 'element-plus/dist/locale/zh-cn.mjs'
import axios from 'axios'

const app = createApp(App)/* 创建vue实例,并起名为app */

//配置服务器ip地址
const BASE_URL = 'http://localhost:8080'

//配置属性，可以再全局script中使用
window.BASE_URL = BASE_URL;

//配置模版属性，方便模版调用
app.config.globalProperties.BASE_URL = BASE_URL;
//3.将所有导入的图标组件变为键值对数组,并依次遍历取出每一个图标组件[key, component]
for (const [key, component] of Object.entries(ElementPlusIconsVue)) {
    // 4.将当前遍历到的图标组件注册为当前vue实例的全局组件
    app.component(key, component)
}
//5.为前vue实例应用element-plus组件库
//6.2 app.use(ElementPlus,{ locale: zhCn })将El本地化为中文
app.use(ElementPlus,{ locale: zhCn }).use(store).use(router).mount('#app')

//7.解决ResizeObserver Error
const debounce = (fn, delay) => {
    let timer = null;
    return function () {
        let context = this;
        let args = arguments;
        clearTimeout(timer);
        timer = setTimeout(function () {
            fn.apply(context, args);
        }, delay);
    }
}
const _ResizeObserver = window.ResizeObserver;
window.ResizeObserver = class ResizeObserver extends _ResizeObserver {
    constructor(callback) {
        callback = debounce(callback, 16);
        super(callback);
    }
}

//配置路由守卫
//to参数：当前的路径
// form参数：即将定向的路径
//next : 放行函数，必须调用才能用此函数
router.beforeEach((to,from,next) => {
    //当用户未登录且尝试访问需要登录的界面的时候，重定向到登录界面
    let user = localStorage.user;
    if (to.path !== '/login' && !user) {
        next({path:'/login'},ElMessage.error('请先登录！'));
    } 
    //如果用户已经登录，则放行,
    else {
        next();
    }
});


/**
 * 设置一个全局的获取用户的方法，该方法会将字符串的user对象
 * 转换成json对象并赋值给localStorage的用户
 * @returns user对象如果该对象存在，否则返回{@code null}
 */
window.getUser = ()=> {
    return localStorage.user ? JSON.parse(localStorage.user) : null;
}


/**
 * 调用后端根据字典code查看对应的字典项的方法
 * @param {*} obj 存放字典项数据
 * @param {*} dictCode 查询参数
 */
window.loadDictOption = (obj,dictCode)=>{
    axios.get(BASE_URL+'/v1/dicOpt/select/'+dictCode)
    .then((response)=>{
        if (response.data.code == 2000) {
            obj.value = response.data.data;
        }
        else {
            ElMessage.error('操作失败！');
        }
    });
};
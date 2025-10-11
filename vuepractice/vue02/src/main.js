import { createApp } from 'vue'
import App from './App.vue'
import router from './router'
import store from './store'
//你必须导入这两样才能使用该库
import ElementPlus from 'element-plus'
import 'element-plus/dist/index.css'
createApp(App).use(ElementPlus).use(store).use(router).mount('#app')


const BASE_URL = 'http://localhost:8060'
window.BASE_URL = BASE_URL;

//配置模版属性，方便模版调用
app.config.globalProperties.BASE_URL = BASE_URL;

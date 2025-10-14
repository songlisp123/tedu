<template>
    <div class="test" v-if="!dialogVisible">
        <p class="info">你正在修改密码，你可以使用以下表单提交，或者使用更安全的方法
            
            生成密码。☞<button id="securityPassword" @click="dialogVisible=true">点击我实现安全密码</button>
        </p>
        <div class="passwordForm">
            <fieldset>
                <p>修改密码：</p>
                <div>
                    <label for="password">确认密码：</label>
                    <input type="password" required id="password" 
                    placeholder="确认您的密码:" v-model="passwordForm.oldpassword">
                </div>
                <div>
                    <label for="newpassword">输入新密码：</label>
                    <input type="password" required id="newpassword" 
                    placeholder="输入新的密码：" v-model="passwordForm.password">
                </div>
                <div>
                    <label for="newpassword">确认新密码：</label>
                    <input type="password" required id="newpasswordconfirm" 
                    placeholder="确认新的密码" v-model="passwordForm.newpasswordConfirm">
                </div>
                <button id="passwordButton" @click="submit(user.id)">提交</button>
            </fieldset>
        </div>   
    </div>
    <div class="visible" v-if="dialogVisible">
         <div class="container" >
            <h1>随机获取密码的程序：</h1>
            <label for="len">密码长度：</label>
            <input type="number" placeholder="请选择密码长度：" id="len" min="6" max="30" value="6">
            <br>
            
            <input type="checkbox" id="upper" checked />
            <label for="upper">包含大写字母</label><br>
            <input type="checkbox" id="number" checked />
            <label for="number">包含数字</label><br>
            <input type="checkbox" id="special" checked />
            <label for="special">包含特殊符号</label><br>
            <button @click="generatePassword()">生成密码</button>
            <button @click="reset()">重置密码</button>
            <div class="output">
                你的密码将会在这里显示
            </div>
            <div>
                <el-button @click="pass()" type="info">提交</el-button>
                <el-button @click="backup()" type="info">返回</el-button>
            </div>
        </div>
    </div>

</template>


<script setup>
import router from '@/router';
import axios from 'axios';
import { ElMessage } from 'element-plus';
import { ref } from 'vue';

//定义表单数据绑定
const passwordForm = ref({
    oldpassword:'',
    password:'',
    newpasswordConfirm:''
});

const passwordSubmit = ref({
    id:'',
    password:''
});

//定义弹窗属性
const dialogVisible = ref(false);

//获取当前用户
var user = window.getUser();

function submit(id) {
    console.log(id,passwordForm.value);
    if (user.password !== passwordForm.value.oldpassword) {
        ElMessage.error('密码错误，请重试');
        passwordForm.value = {};
    }
    else {
        if (passwordForm.value.password !== passwordForm.value.newpasswordConfirm) {
            ElMessage.error('密码不匹配，请检查密码！');
        }
        else {
            passwordSubmit.value.password = passwordForm.value.password;
            passwordSubmit.value.id = id;
            console.log(passwordSubmit.value);
            axios.post(BASE_URL+'/v1/user/updatePassword',passwordSubmit.value)
            .then((response)=>{
                if (response.data.code == 2000) {
                    ElMessage.success('更新成功');
                    passwordForm.value = {};
                    passwordSubmit.value = {};
                    router.push('/change/password/complete');
                }
                else {
                    ElMessage.error('更新失败！请稍后重试！');
                }
            });
        }
    }
}


function genPass(len,upper,number,special) {
    //声明大小写和数字密码
    const lowerchars = 'abcdefghijklmnopqrstuvwxyz';
    const upperchars = lowerchars.toUpperCase();
    const numbers = '0123456789';
    const specialchars = "!@#$%^&*()-_=+[]{}|;:,.<>?";
    //首先默认定义为小写
    let char = lowerchars;

    //判断是否选择了这些东西
    if (upper) char += upperchars;
    if (number) char += numbers;
    if (special) char += specialchars;

    //随机生成密码的程序
    let pass = "";
    for (let i = 0;i<len;i++) {
        const randomIndex = Math.floor(Math.random()*char.length);
        pass += char[randomIndex];
    };
    return pass;
}


function generatePassword() {
    const len = parseInt(document.getElementById('len').value);
    console.log(len);
    const upper = document.getElementById('upper').checked;
    const number = document.getElementById('number').checked;
    const special = document.getElementById('special').checked;
    document.querySelector('.output').textContent = genPass(len,upper,number,special);
    passwordSubmit.value.password = genPass(len,upper,number,special);
}
function reset() {
    document.getElementById('len').value = 15;
    document.getElementById('upper').checked = true;
    document.getElementById('number').checked = true;
    document.getElementById('special').checked = true;
    document.querySelector('.output').textContent = "您的密码在这里！";
}

function pass() {
    if (!passwordSubmit.value.password) {
        ElMessage.error('密码非空！');
        return;
    }
    passwordSubmit.value.id = user.id;
    console.log(passwordSubmit.value); 
    axios.post(BASE_URL+'/v1/user/updatePassword',passwordSubmit.value)
            .then((response)=>{
                if (response.data.code == 2000) {
                    ElMessage.success('更新成功');
                    passwordSubmit.value = {};
                    router.push('/change/password/complete');
                }
                else {
                    ElMessage.error('更新失败！请稍后重试！');
                }
            });
}

function backup() {
    dialogVisible.value = false;
}
</script>

<style>

.test {
    background-color: #666;
    height: 93vh;
    color: #fff;
}

.info {
    padding-top: 20px;
    padding-left: 10%;
    font-size: 1.2em;
    font-weight: bold;
    margin-bottom: 10px;
}



.passwordForm {
    text-align: center;
    margin: auto auto;
    height: 50vh;
    align-content: center;

}

fieldset {
    border: none;
    display: flex;
    flex-direction: column;
    gap: 30px;
    align-items: center;
    padding: 20px;
}

fieldset>p {
    font-size: 1.5em;
    font-weight: bold;
    margin-bottom: 10px;
}

.passwordForm label {
    font-size: 1.15em;
}

.passwordForm input {
    width: 200px;
    height: 30px;
    border: none;
    border-bottom: 1px solid #666;
} 

#passwordButton {
    width: 50px;
    height: 50px;
    border-radius: 50%;
    transition: 0.3s ease;
}
#passwordButton:hover {
    background-color: lightgoldenrodyellow;
    cursor: pointer;
}

#securityPassword {
    border: 0;
    background-color: rgba(255, 255, 255, 0);
    font-size: 1em;
    transition: 0.3s ease-in;
}

#securityPassword:hover {
    cursor: pointer;
    color: blue;
    border-bottom: 1px solid blue;
}

.visible {
    font-family: Arial, sans-serif;
    background: linear-gradient(135deg, #6a11cb, #2575fc);
    color: #fff;
    display: flex;
    justify-content: center;
    align-items: center;
    height: 93vh;
    margin: 0;
}

.container {
    background: rgba(255, 255, 255, 0.1);
    padding: 20px;
    border-radius: 10px;
    box-shadow: 0 4px 15px rgba(0, 0, 0, 0.2);
    text-align: center;
    width: 450px;
}

.container h1 {
    margin-bottom: 20px;
}

.container input[type="number"] {
    width: 50px;
    margin-bottom: 20px;
}

.container input[type="checkbox"] {
    margin-right: 10px;
}

.output {
    margin: 20px 0;
    font-size: 1.2em;
    background: rgba(255, 255, 255, 0.2);
    padding: 10px;
    border-radius: 5px;
    word-wrap: break-word;
}

.container button {
    background-color: #6a11cb;
    border: none;
    color: white;
    padding: 10px 20px;
    border-radius: 5px;
    cursor: pointer;
    transition: 0.3s;
    margin: 5px;
}


.container button:hover {
    background-color: #2575fc;
}


</style>
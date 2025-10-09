<template>
    
    <p class="message warning" v-if="WarinningMessageShow">
        请注意，你正在修改密码，请确保您的安全。
        <button id="cancle" @click="WarinningMessageShow=false">x</button></p>
    <p class="message error" v-if="isError">{{ message }}</p>
    <!-- <p class="message" v-if="isPass"></p> -->
    <fieldset>
        <!-- <p>用户修改密码阶段</p> -->
        <div class="top">
            <label for="password">修改密码：</label> <br>
            <input type="password" name="password" id="password" placeholder="请输入您的密码：" required>
        </div>
        <div>
            <label for="confirmpassword">确认密码：</label> <br>
            <input type="password" name="password" id="confirmpassword" placeholder="请确定您的密码：" required >
        </div>
        <div>
            <label for="newpassword">新密码：</label> <br>
            <input type="password" name="password" id="newpassword" placeholder="请确定您的新密码：" required >
        </div>
        <div>
            <button @click="f1()">提交</button>
        </div>
    </fieldset>
</template>

<script setup>
import router from '@/router';
import { ref } from 'vue';
//然后获取当前用户
var user = JSON.parse(localStorage.user);

//设置一个参数，这个参数将会传递到后端
var userDto = {
    id:'',
    password:''
};

//定义一个变量用来显示警告信息
const WarinningMessageShow = ref(true);

//继续定义一个变量用来显示报错信息
const isError = ref(false);


//定义一个字符串来储存信息
const message = ref('');

//用户每次进入时候都会调用此函数。
function timeout(refValue) {
    setTimeout(() => {
    refValue.value = false;
}, 5000);
};

//判断用户输入密码与保存的密码一样的？
function judgePassword(password,password2) {
    if (password == password2) return true;
    else return false;
}

//主逻辑
function f1() {
    console.log('提交了表单');
    //先获取标签
    let password = document.getElementById('password');
    let confirmpassword = document.getElementById('confirmpassword');
    let newpassword = document.getElementById('newpassword');
    if (!password.value.trim() || !judgePassword(password.value,confirmpassword.value) || !newpassword.value.trim()) {
        //为用户提示错误消息，并返回
        console.log("参数错误！");
        message.value = "参数错误！请仔细检查您的参数！";
        isError.value = true;
        timeout(isError);
        return;
    };
    if (!judgePassword(password.value,user.password)) {
        console.log("密码不一致错误！");
        message.value="密码不一致错误！";
        isError.value = true;
        timeout(isError);
        return;
    }
    userDto.id = user.id;
    userDto.password = newpassword.value;
    //如果用户没有输入错误那么就应该向后端发送请求并接收数据
    fetch("http://localhost:8085/v1/user/changePasswordConfirm",{
        method:"POST",
        headers:{
            "Content-Type":"application/json"
        },
        body:JSON.stringify(userDto)
    })
    .then(res => res.json())
    .then(data => {
        console.log(data);
        if (data.code == 2000) {
            //修改当前用户的密码
            user.password=newpassword.value;
            //清除当前用户
            localStorage.clear('user');
            //重定向到新的验证界面
            router.push("/password/change/complete");
        } else {
            message.value="发生未知错误！密码修改失败！请稍后再试"
            timeout(isError);
        }
    });
};

</script>

<style>


        input {
            padding: 10px;
            border: 0;
            border-bottom: 1px solid gray;
            width: 200px;
        }

        input:hover {
            border-color: lightskyblue;
        }



        button {
            background-color: rgb(30, 30, 30);
            border: 0;
            border-bottom: 1px solid #666;
            font-size: 15px;
            border-radius: 5px;
            height: 50px;
            width: 50px;
            color: #666;
            transition: 0.5s;
        }

        button:hover {
            color: #000;
            cursor:pointer;
        }

        button::after {
            content: '>';
        }
        .error {
            top: 16%;
        }

        .warning {
            width: 30%;
            right: 35%;
        }

        #cancle {
            border: 0;
            background-color: rgba(167, 34, 34,0);
            color: rgb(172, 142, 142);
        }
        
</style>
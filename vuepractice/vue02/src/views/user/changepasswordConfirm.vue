<template>
    <div class="test">
        <p class="info">你正在修改密码，你可以使用以下表单提交，或者使用更安全的方法
            生成密码。
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

</script>

<style>

.info {
    margin-top: 20px;
    padding-left: 10%;
    font-size: 1.2em;
    font-weight: bold;
    margin-bottom: 10px;
}

.passwordForm {
    text-align: center;
    margin: auto auto;

}

fieldset {
    border: none;
    display: flex;
    flex-direction: column;
    gap: 20px;
    align-items: center;
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
</style>
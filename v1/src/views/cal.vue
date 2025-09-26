<template>
    <div class="mianer">
        <h1>学习？</h1>
        <label for="a1">a1的值：</label>
        <input type="text" v-model="a1" id="a1">
        <br>
        <label for="a1">a2的值：</label>
        <input type="text" v-model="a2" id="a1">
        <br>
        <div class="cal">
            <button id="add" @click="cal('+')">加</button>
            <button id="sub" @click="cal('-')">减</button>
            <button id="ch" @click="cal('x')">乘</button>
            <button id="cu" @click="cal('/')">除</button>
        </div>
        

        <br>
        <p>当前数值为：{{ result }}</p>
    </div>

</template>

<script setup>
import { ref } from 'vue';
const a1 = ref('');
const a2 = ref('');
const result = ref(0);
function cal(string) {
    let x = parseFloat(a1.value);
    let y = parseFloat(a2.value);
    // let c = a1.value + a2.value; 不能使用此值，因为这是字符串拼接
    switch(string) {
        case '+' :
            result.value = x+y;
            break;
        case '-':
            result.value = x - y;
            break;
        case 'x' :
            result.value = x * y;
            break;
        case '/' :
            result.value = x / y;
            break;

    }
    return result.value;  
};

</script>

<style>
.cal {
    margin-top: 50px;
    display: flex;
    justify-content: center;
    gap: 30px;

}
    .cal>button {
        width: 50px;
        height: 50px;
        font-size: 15px;
        border: 0;
        border-radius: 50%;
        color: azure;
        line-height: 50px;
        transition: 0.5s;
    }
    #add {
        background-color: #666;
    }
    #add:hover {
        background-color: #a79120;
    }
    #sub {
        background-color: #a79120;
    }
    #sub:hover {
        background-color: rgb(18, 163, 216);
    }
    #ch {
        background-color: #ababab;
    }
    #ch:hover {
        opacity: 0.5;
        background-color: lightpink;
    }
    #cu {
        background-color: rgb(216, 147, 18);
    }
    #cu:hover {
        background-color: rgb(216, 18, 193);
    }
</style>
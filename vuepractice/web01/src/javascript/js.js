// localStorage.getItem
import router from '@/router';
import { ref } from 'vue';
const user = ref('');
console.log(user.value);
user.value = localStorage.getItem('user');

user.value=JSON.parse(user.value);

function f2() {
    localStorage.clear('user');
    user.value='';
    console.log(localStorage.user);
    router.push("/");
}
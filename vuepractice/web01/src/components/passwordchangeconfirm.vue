<template>
    <h1>密码修改成功！</h1>
    <p>为了确保您的安全,请继续尝试<a href="/login">登录</a>，为你造成的不便，我们深感抱歉！</p>
</template>

<script>
export default {
    name:"PasswordChangeComplete"
}
</script>

<style>

    p a {
        color: lightgreen;
        background-color: var(--web-color);
    }
    p a:hover {
        background-color: var(--text-color);
        text-decoration: underline;
    }
</style>
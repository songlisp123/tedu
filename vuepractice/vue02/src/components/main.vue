<template>
<div class="main">
    <div class="center">
      <!-- <header>
        <div class="top">
          <p class="logo">Blog</p>
          <div class="nav">
            <nav>
              <ul>
                <li><a href="#">概论</a></li>
                <li><a href="#">下载</a></li>
                <li><a href="#">文档</a></li>
                <li><a href="#">信息</a></li>
                <li><a href="#">社区</a></li>
                <li><a href="#">代码</a></li>
                <li><a href="#">问题</a></li>
                <li><a href="#">关于</a></li>
                <li><a href="#">💗捐赠💗</a></li>
              </ul>
            </nav>
          </div>
        </div>
        <div class="bottom">
          <p>文档</p>
          <div class="searchbar">
            <span>搜索(版本号:xxxxx)</span>
            <a href="#">🔍</a>
          </div>
        </div>
      </header> -->
      //下一步面包屑
      <!-- 网站主要内容 -->
      <main>
        <div class="left">
          <section id="i1">
            <p>💗站长推荐💗</p>
            <Picturewall />
          </section>
          <section class="section02">
            <p>看饿了？</p>
            <p>设计你自己的av？</p>
          </section>
        </div>
        <!-- <div class="right"></div> -->
      </main>
    </div>
</div>
</template>
<script>

import Picturewall from './picturewall.vue';



export default {
    name:'Main',
    components:{
        Picturewall
    }
};
</script>
<style>
</style>
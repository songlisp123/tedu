<template>
  <Header />
  <Main />
  <Footer />
</template>

<script>
// @ is an alias to /src
import Header from '@/components/header.vue';
import Footer from '@/components/footer.vue';
import Main from '@/components/main.vue';

export default {
  name: 'HomeView',
  components: {
    Header,Footer,Main
  }
}
</script>

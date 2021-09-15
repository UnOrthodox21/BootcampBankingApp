<template>
  <HomeBanner v-bind:user="user"/>
  <HomeMenu v-bind:user="user" v-bind:bankAccounts="bankAccounts"/>
</template>

<script>
  import HomeBanner from "../components/home/HomeBanner.vue";
  import HomeMenu from "../components/home/HomeMenu.vue"

  export default {
    name: 'Home',
    components: {
      HomeBanner,
      HomeMenu
    },
    props: ["user", "bankAccounts"]
  }
</script>

<style scoped>
</style>

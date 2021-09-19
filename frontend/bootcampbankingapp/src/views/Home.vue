<template>
<div v-if="jwt == ''">
  <h1>Hello there</h1>
</div>

<div v-else>
  <HomeBanner v-bind:user="user"/>
  <HomeMenu v-bind:user="user" v-bind:bankAccounts="bankAccounts"/>
</div>
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
    props: ["user", "bankAccounts", "jwt"]
  }
</script>

<style scoped>
</style>

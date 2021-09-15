<template>
  <Header v-bind:user="user"/>
  <router-view v-bind:user="user"/>
  <Footer/>
</template>


<script>
import Header from "./components/layout/Header.vue"
import Footer from "./components/layout/Footer.vue"

export default {
    name: "app",
    components: {
      Header,
      Footer
    },
    data() {
      return {
        user: [],
        bankAccount: [],
      }
    },
     methods: {
      setUser() {
        this.$http.get(process.env.VUE_APP_API_URL + "/users/1")
        .then((response) => { this.user = response.data })
        .catch(err => console.log(err));
      }
    },
    mounted() {
      this.setUser();
    }
  }
</script>


<style>
#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  /* font-family: 'Lato', sans-serif; */
  font-family: 'Roboto Condensed', sans-serif; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(248, 248, 248);
  background-image: url("assets/background.jpg");
}

#nav {
  padding: 30px;
}

#nav a {
  font-weight: bold;
  color: #2c3e50;
}

#nav a.router-link-exact-active {
  color: #42b983;
}
</style>

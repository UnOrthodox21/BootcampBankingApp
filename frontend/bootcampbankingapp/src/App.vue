<template>
  <Header v-bind:user="user"/>
  <router-view v-bind:user="user" v-bind:bankAccounts="bankAccounts"/>
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
        bankAccounts: [],
      }
    },
     methods: {
      setUser() {
        this.$http.get(process.env.VUE_APP_API_URL + "/users/newuser1")
        .then((response) => { this.user = response.data })
        .catch(err => console.log(err));
      },
      setBankAccount() {
        this.$http.get(process.env.VUE_APP_API_URL + "/bank-accounts/2")
        .then((response) => { this.bankAccounts = response.data })
        .catch(err => console.log(err));
      }
    },
    mounted() {
      this.setUser();
      this.setBankAccount();
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

.btn-transaction {
    color: rgb(248, 248, 248);
    text-align: center;
    vertical-align: center;
    background-color: rgb(111, 201, 76);
    font-size: 1.3em;
    height: 3.5em;
    width: 40em;
    padding: 1em;
}

.btn-transaction:hover {
    background-color: rgb(118, 221, 77);
    color: rgb(248, 248, 248);
  }
</style>

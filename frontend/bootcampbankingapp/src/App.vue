<template>
<div class="content">
  <Header v-bind:user="user" v-bind:jwt="jwt"/>
  <router-view v-bind:user="user" v-bind:bankAccounts="bankAccounts" v-bind:jwt="jwt" 
  v-bind:transactions="transactions" v-bind:selectedUsersBankAccounts="selectedUsersBankAccounts" 
  v-bind:selectedBankAccountsTransactions="selectedBankAccountsTransactions"/>
  </div>
  <Footer/>
</template>

<script>
import Header from "./components/layout/Header.vue"
import Footer from "./components/layout/Footer.vue"
import {useRouter} from "vue-router";
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
        users: [],
        router: useRouter(),
        jwt: "",
        transactions: [],
        selectedUsersBankAccounts: [],
        selectedBankAccountsTransactions: []
      }
    },
  
    created() {
        this.jwt = this.getCookie("Token");
        let jwt = this.jwt;
        this.setupHeaderInterceptor();
       if (jwt !== undefined && jwt.length) {
            this.setUser(jwt);
        }
    },
     methods: {
      setUser(jwt) {
        this.$http.get(process.env.VUE_APP_API_URL + "/users/getUserByJwt/" + jwt)
        .then((response) => { 
          this.user = response.data;
          this.setBankAccounts(this.user.id);
        })
        .catch(err => console.log(err));
      },
      setBankAccounts() {
        this.$http.get(process.env.VUE_APP_API_URL + "/bank-accounts/getByUserId/" + this.user.id)
        .then((response) => { this.bankAccounts = response.data })
        .catch(err => console.log(err));
      },
      setSelectedUsersBankAccounts(userId) {
         this.$http.get(process.env.VUE_APP_API_URL + "/bank-accounts/getByUserId/" + userId)
        .then((response) => { this.selectedUsersBankAccounts = response.data })
        .catch(err => console.log(err));
      },
      setJwt(jwt) {
        let d = new Date();
        d.setTime(d.getTime() + 1 * 24 * 60 * 60 * 1000);
        let expires = "expires=" + d.toUTCString();
        document.cookie ="Token=" + jwt + ";" + expires + ";path=/";
        this.jwt = jwt;
      },
      setTransactions(bankAccountNumber) {
          this.$http.get(process.env.VUE_APP_API_URL + "/transactions/find/" + bankAccountNumber)
        .then((response) => { this.transactions = response.data })
        .catch(err => console.log(err));
      },
       setSelectedBankAccountsTransactions(bankAccountNumber) {
          this.$http.get(process.env.VUE_APP_API_URL + "/transactions/find/" + bankAccountNumber)
        .then((response) => { this.selectedBankAccountsTransactions = response.data })
        .catch(err => console.log(err));
      },
      setupHeaderInterceptor() {
        this.$http.defaults.headers.common['Authorization'] = "Bearer " + this.getCookie("Token"); // for all requests
      },
      getCookie(name) {
        const value = `; ${document.cookie}`;
        const parts = value.split(`; ${name}=`);
        if (parts.length === 2) return parts.pop().split(';').shift();
      },
      clearCookie() {
        document.cookie = "Token=; expires=Thu, 01 Jan 1970 00:00:00 UTC; path=/;";
      },
      login(username, jwt) {
        // Add a request intercepto
        this.setJwt(jwt);
        this.setupHeaderInterceptor();
        const newUserData = {
            jwt,
            enabled: true
        }
        this.$http.put(process.env.VUE_APP_API_URL + "/users/" + username, newUserData)
        .then(() => {  
          this.setUser(jwt);
          this.setBankAccounts(12345);
          this.router.push({ name: 'Home' });
         }).catch(err => console.log(err));
      },
      logout() {
        this.user = [],
        this.bankAccounts = [],
        this.jwt = '';
        this.clearCookie();
        this.router.push({ name: 'Login'});
      }

    }
  }
</script>


<style>
html, body {
  min-height: 100%;
  background-color:  rgb(240, 240, 240);
  background-size: cover;
}
.content {
  flex: 1 0 auto;
  margin-bottom: 10rem;
}
.footer {
  flex-shrink: 0;
}
#app {
  /* font-family: Avenir, Helvetica, Arial, sans-serif; */
  /* font-family: 'Lato', sans-serif; */
  font-family: 'Roboto Condensed', sans-serif; 
  -webkit-font-smoothing: antialiased;
  -moz-osx-font-smoothing: grayscale;
  text-align: center;
  color: rgb(248, 248, 248);
  background-image: url("assets/background.jpg");
  background-size: cover;
  background-repeat: no-repeat;
  background-position: center center;
  min-height: 100%;
  display: flex;
  flex-direction: column;
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
    .btn-login {
      color: rgb(248, 248, 248);
      font-size: 1.2em;
      padding: 1em;
      height: 3.5em;
      width: 100%;
      text-align: center;
      vertical-align: bottom;
      margin-top: 3.85em;
      background-color: rgb(25, 25, 25);
    }
    .btn-login:hover {
      color: rgb(248, 248, 248);
      background-color: rgb(32, 32, 34);
    }
</style>

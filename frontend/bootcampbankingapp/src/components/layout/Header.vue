<template>
<nav class="navbar navbar-expand-lg navbar-light">
      <div class="container-fluid">
        <router-link class="navbar-brand" to="/">Navbar</router-link>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNavDropdown" aria-controls="navbarNavDropdown" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div v-if="jwt === undefined || jwt === ''" class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav w-100">
              <li class="nav-item">
              <router-link class="nav-link active" aria-current="page" to="/login">Login</router-link>
            </li> 
            <li class="nav-item">
              <router-link class="nav-link" to="/register">Register</router-link>
            </li>
          </ul>
        </div>

        <div v-else-if="jwt !== undefined && jwt !== '' && this.user.roles !== 'Admin'" class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav w-100">
              <li class="nav-item">
              <router-link class="nav-link active" aria-current="page" to="/">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/transactions">Transactions</router-link>
            </li>
            <li class="nav-item dropdown ml-auto">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-expanded="false">{{user.firstName}} {{user.lastName}}</a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li>
                  <router-link class="dropdown-item" to="/settings">Settings</router-link>
                </li>
                <li>
                  <router-link class="dropdown-item" to="/bank-accounts">Bank accounts</router-link>
                </li>
                   <li>
                  <router-link class="dropdown-item" @click="logout" to="/login" >Log out</router-link>
                </li>
              </ul>
            </li>
          </ul>
        </div>


        
        <div v-else-if="jwt !== undefined && jwt !== '' && this.user.roles === 'Admin'" class="collapse navbar-collapse" id="navbarNavDropdown">
          <ul class="navbar-nav w-100">
              <li class="nav-item">
              <router-link class="nav-link active" aria-current="page" to="/">Home</router-link>
            </li>
            <li class="nav-item">
              <router-link class="nav-link" to="/admin/users">Manage Users</router-link>
            </li>
            <li class="nav-item dropdown ml-auto">
              <a class="nav-link dropdown-toggle" href="#" id="navbarDropdownMenuLink" role="button" data-toggle="dropdown" aria-expanded="false">{{user.firstName}} {{user.lastName}}</a>
              <ul class="dropdown-menu" aria-labelledby="navbarDropdownMenuLink">
                <li>
                  <router-link class="dropdown-item" to="/settings">Settings</router-link>
                </li>
                <li>
                  <router-link class="dropdown-item" to="/bank-accounts">Bank accounts</router-link>
                </li>
                   <li>
                  <router-link class="dropdown-item" @click="logout" to="/login" >Log out</router-link>
                </li>
              </ul>
            </li>
          </ul>
        </div>



      </div>
    </nav>

</template>


<script>

export default {
    name: "Header",
    props: ["user", "jwt"],
    mounted() {
      console.log("jwt from navbar: " + this.jwt);
    },
    methods: {
      logout(e) {
        e.preventDefault();
        this.$parent.logout();
      }

    }

}
</script>


<style scoped>
    .navbar-light {
        background-color: rgb(245, 245, 245);
    }

    .nav-item {
        font-size: 1.40em;
    }

     .active {
        font-weight: bold;
    }


</style>

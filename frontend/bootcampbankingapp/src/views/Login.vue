<template>
<div class="container mt-5">
  <div class="row">
    <div class="col-4 mx-auto">
    <h1 class="h1 mb-3 fw-normal my-5 text-center">Please Log In</h1>
        <form @submit="authenticate">
  <div class="form-group">
    <label for="usernameInput" class="my-3">Username</label>
    <input v-model="this.username" type="text" class="form-control" id="usernameInput" placeholder="Username" required>  
    </div>
  <div class="form-group mt-5">
    <label for="passwordInput" class="my-3">Password</label>
    <input v-model="this.password" type="password" class="form-control" id="passwordInput" placeholder="Password" required>
  </div>
  <button type="submit" class="btn btn-login">Submit</button>
</form>
    </div>
  </div>
  </div>

</template>

<script>
export default {
    name: "Login",
    data() {
        return {
            username: '',
            password: ''
        }
    },
    methods: {
        authenticate(e) {
            e.preventDefault();

            const loginUser = {
                username: this.username,
                password: this.password
            }

            var instance = this.$http.create();
            delete instance.defaults.headers.common['Authorization'];

            instance.post(process.env.VUE_APP_API_URL + "/users/authenticate", loginUser)
            .then((response) => {
                if (response.data.jwt != undefined) {
                    this.$parent.$parent.login(loginUser.username, response.data.jwt);
                }
            }).catch(err => console.log(err));
        },

    }
  
}
</script>



<style scoped>

    label {
        font-size: 1.25em;
    }

     input {
        font-size: 1.15em;
    }

</style>
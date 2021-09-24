<template>
<div class="container-fluid mt-0">
    <div class="row">
        <div class="col-12">
  <form v-on:submit="submitChanges"> 
  <div class="form-group"  style="width:300px;margin:auto;">
    <label for="emailInput">Email address </label>
    <input type="email" class="form-control" v-model="email" id="emailInput" minlength="9" maxlength="30" required>
  </div>

   <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="nameInput">Name:</label>
    <input type="text"  class="form-control" v-model="firstName" id="nameInput" minlength="2" maxlength="20" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="surnameInput">Last name:</label>
    <input type="text" class="form-control" v-model="lastName" id="surnameInput" minlength="2" maxlength="20" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="phoneInput">Phone number:</label>
    <input type="number" class="form-control" v-model="phone" id="phoneInput" minlength="8"  maxlength="20" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="addressInput">Address:</label>
    <input type="text" class="form-control" v-model="address" id="addressInput" minlength="8" maxlength="40" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="passwordInput">New password:</label>
    <input type="password" class="form-control" v-model="passwordFirst" id="passwordInput" minlength="8" maxlength="40" >
  </div>

 <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="passwordInputTwo">Repeat new password:</label>
    <input type="password" class="form-control" v-model="passwordSecond"  id="passwordInputTwo" minlength="8" maxlength="40" >
  </div>

   <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="imageInput">User image:</label>
    <input type="file" class="form-control" id="imageInput" accept="image/png, image/jpeg">
  </div>

   <button @click="submitChanges()" class="btn btn-login mt-4" type="submit">Submit form</button>
</form>
        </div>
    </div>
 </div>


</template>

<script>
import {useRouter} from "vue-router";
export default {
    name: "Settings Form",
    data() {
      return {
          router: useRouter(),
          email: this.user.email,
          firstName: this.user.firstName,
          lastName: this.user.lastName,
          phone: this.user.phone,
          address: this.user.address,
          image: this.user.image,
          enabled: this.user.enabled,
          passwordFirst: '',
          passwordSecond: ''
      }
    },
    created() {
      this.$parent.$parent.$parent.setUser(this.user.jwt);
    },
    props: ["user"],
    methods: {
        submitChanges(e) {

            e.preventDefault();
            
            if(this.passwordFirst === this.passwordSecond) {

            if (window.confirm("Are you sure?")) {
            const newUserData = {
            email : this.email,
            firstName : this.firstName,
            lastName : this.lastName,
            phone : this.phone,
            address : this.address,
            password : this.passwordFirst,
            image: this.image,
            enabled: this.enabled
            }


            if (this.passwordFirst === "") {
                newUserData.password = this.user.password;
            }
          
            this.$http.put(process.env.VUE_APP_API_URL + "/users/" + this.user.username , newUserData)
            .then(() => {
                 this.$parent.$parent.$parent.setUser(this.user.jwt);
                 this.router.push({ name: 'Home'});
            }).catch(err => console.log(err));

            }
        
      

    } else {
      alert("Passwords don't match!!");
    }

  }
}
}
</script>

<style scoped>
    label {
        font-size: 1.1em;
        margin-bottom: 0;
    }

    .btn-login {
      font-size: 1.2em;
      padding: 1em;
      height: 3.5em;
      width: 15%;
      text-align: center;
      vertical-align: bottom;
    }
</style> 
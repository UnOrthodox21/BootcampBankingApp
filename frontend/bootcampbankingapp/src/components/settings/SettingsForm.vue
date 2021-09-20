<template>

<div class="container">
    <div class="row">
        <div class="col-12">
<form v-on:submit="submitChanges"> 
  <div class="form-group my-5"  style="width:300px;margin:auto;">
    <label for="emailInput">Email address </label>
    <input type="email" class="form-control " id="emailInput" v-bind:value="user.email" minlength="9" maxlength="30" required>
  </div>
 
   <div class="form-group my-5" style="width:300px;margin:auto;">
       <!-- ja grib atsevisku margin bottom
    <label class="mb-5" for="exampleFormControlInput2">Name </label> -->
    <label for="nameInput"> Name </label>
    <input type="text"  class="form-control" id="nameInput"  v-bind:value="user.firstName" minlength="2" maxlength="20" required>
    
    
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="surnameInput">Surname </label>
    <input type="text" class="form-control " id="surnameInput" v-bind:value="user.lastName" minlength="2" maxlength="20" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="phoneInput">Phone number </label>
    <input type="number" class="form-control "    id="phoneInput" v-bind:value="user.phone" minlength="8"  maxlength="20" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="addressInput">Address </label>
    <input type="text" class="form-control "    id="addressInput" v-bind:value="user.address" minlength="8" maxlength="40" required>
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="passwordInput">New password</label>
    <input type="password" class="form-control "    id="passwordInput" minlength="8" maxlength="40" >
  </div>

 <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="passwordInputTwo">Repeate new password</label>
    <input type="password" class="form-control "    id="passwordInputTwo" minlength="8" maxlength="40" >
  </div>

   <button @click="submitChanges()" class="btn btn-primary" type="submit">Submit form</button>
</form>
        </div>
    </div>
 </div>


</template>

<script>

export default {
    name: "Settings Form",
    components: {
    },
    props: ["user"],
    methods: {
        submitChanges(e) {


            e.preventDefault();

            const pw1 = document.getElementById("passwordInput").value;
            const pw2 = document.getElementById("passwordInputTwo").value;

            if(pw1 === pw2){
  
            if (window.confirm("Are you sure?")) {
            const newUserData = {
            email : document.getElementById("emailInput").value,
            firstName : document.getElementById("nameInput").value,
            lastName : document.getElementById("surnameInput").value,
            phone : document.getElementById("phoneInput").value,
            address : document.getElementById("addressInput").value,
            password : pw1
            }
            if(pw1.length != 0){
              newUserData.password = pw1;
            } 
          
            this.$http.put(process.env.VUE_APP_API_URL + "/users/" + this.user.username , newUserData)
            .then(() => this.$parent.submitChanges())
            .catch(err => console.log(err));
            console.log(newUserData)
           }
            
}
else{
  alert("Passwords don't match!!");
}
}
    }
}

</script>

<style scoped>
    label {
        margin-bottom:0;
    }
</style>
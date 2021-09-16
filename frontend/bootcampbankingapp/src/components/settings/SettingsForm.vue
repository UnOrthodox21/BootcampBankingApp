<template>

<div class="container">
    <div class="row">
        <div class="col-12">
<form v-on:submit="getEl"> 
  <div class="form-group my-5"  style="width:300px;margin:auto;">
    <label for="emailInput">Email address </label>
    <input type="email" class="form-control "    id="emailInput" v-bind:value="user.email">
  </div>
 
   <div class="form-group my-5" style="width:300px;margin:auto;">
       <!-- ja grib atsevisku margin bottom
    <label class="mb-5" for="exampleFormControlInput2">Name </label> -->
    <label for="nameInput"> Name </label>
    <input type="text"  class="form-control" id="nameInput"  v-bind:value="user.firstName">
    
    
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="surnameInput">Surname </label>
    <input type="text" class="form-control " id="surnameInput" v-bind:value="user.lastName" >
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="phoneInput">Phone number </label>
    <input type="number" class="form-control "    id="phoneInput" v-bind:value="user.phone" >
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="addressInput">Address </label>
    <input type="text" class="form-control "    id="addressInput" v-bind:value="user.address">
  </div>
   
   <button class="btn btn-primary" type="submit">Submit form</button>
</form>
        </div>
    </div>
 </div>


</template>

<script>
// let fname = document.getElementById("nameInput").value;
// console.log(fname)



export default {
    name: "Settings Form",
    components: {
    },
    props: ["user"],
    // data: function() {
    //     return {
    //         name: ''
    //     }
    // },
    methods: {
        getEl(e) {
            e.preventDefault();
            let email = document.getElementById("emailInput").value;
            let firstName = document.getElementById("nameInput").value;
            let lastName = document.getElementById("surnameInput").value;
            let phone = document.getElementById("phoneInput").value;
            let address = document.getElementById("addressInput").value;
            const newProduct = {
               email: email,
               firstName: firstName,
               lastName: lastName,
               phone:phone,
               address:address
            }
            // Send up to parent
            // this.$emit('add-product', newProduct);
            this.$http.put(process.env.VUE_APP_API_URL + "/users/test/3", newProduct)
            .then(() => this.$parent.getEl())
            .catch(err => console.log(err));
            console.log(newProduct)
            // this.name='';
}
    }
}

</script>

<style scoped>
    label {
        margin-bottom:0;
    }
</style>
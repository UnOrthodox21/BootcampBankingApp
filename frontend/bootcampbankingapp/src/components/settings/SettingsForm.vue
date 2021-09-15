<template>
<div>
    <p>Settings test</p>
</div>

<div class="container">
    <div class="row">
        <div class="col-12">
<form>
  <div class="form-group my-5"  style="width:300px;margin:auto;">
    <label for="emailInput">Email address </label>
    <input type="email" class="form-control "    id="emailInput" >
  </div>
 
   <div class="form-group my-5" style="width:300px;margin:auto;">
       <!-- ja grib atsevisku margin bottom
    <label class="mb-5" for="exampleFormControlInput2">Name </label> -->
    <label for="nameInput">Name </label>
    <input type="text" class="form-control "    id="nameInput" >
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="surnameInput">Surname </label>
    <input type="text" class="form-control "    id="surnameInput" >
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="phoneInput">Phone number </label>
    <input type="number" class="form-control "    id="phoneInput" >
  </div>

  <div class="form-group my-5" style="width:300px;margin:auto;">
    <label for="addressInput">Address </label>
    <input type="text" class="form-control "    id="addressInput" >
  </div>
   
   <button class="btn btn-primary" type="submit">Submit form</button>
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
    props: ["user"]
}
</script>

<style scoped>
    label {
        margin-bottom:0;
    }
</style>

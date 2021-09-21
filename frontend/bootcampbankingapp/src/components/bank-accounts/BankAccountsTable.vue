<template>
<button type="button" class="btn btn-top-menu">Add secondary account</button>
<button type="button" class="btn btn-top-menu">Show transaction history</button>
<router-link class="btn btn-top-menu" to="/transactions">Send money</router-link>
 

   <table class="table">
  <thead>
    <tr>
      <th scope="col">Account adress</th>
      <th scope="col">Account type</th>
      <th scope="col">Balance</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody><p>suka{{user}}</p>
    <tr v-bind:for="(bankAccount,index) in bankAccounts"> 
      <th scope="row">{{ bankAccount.number }}</th>
      <td>{{ bankAccount.type }}</td>
      <td>{{ bankAccount.balance }}</td>
      <td><button type="button" class="btn btn-outline-success" @click="() => TogglePopup('buttonTrgger')">Edit</button>
      <button type="button" class="btn btn-outline-danger" @click="deleteBankAccount(user.bankAccounts[0].id)" >Remove</button></td>
    </tr>
   
  </tbody>
</table>
</template>

<script>

export default {
    name: "BankAccountsTable",
    components: {
      
    },
    props: ["user","bankAccounts"],
    methods: {
      
    





      deleteBankAccount(id){
        if(window.confirm("Are You sure?")){
          this.$http.delete(process.env.VUE_APP_API_URL + "/bank-accounts/" + id)
          .then(() => this.$parent.$parent.setUser())
          .catch(err => console.log(err));
        }

      }

    }
}
</script>



<style scoped>
    .btn-top-menu{
        border-color: rgb(248, 248, 248);
        color: rgb(248, 248, 248);
        font-size: 1.2em;
        padding: 1em;
        height: 3.5em;
        width: 12em;
        text-align: center;
        vertical-align: center;
        margin-bottom: 30px;
        margin-inline: 10px;
    }

    .btn-top-menu:hover {
          background-color: rgba(46, 46, 46, 0.884);
    }

    

    .table {
        background: rgb(240, 240, 240);
        color: black;
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
        font-size: 0,7em;
        font-weight: bold;
    }
</style>

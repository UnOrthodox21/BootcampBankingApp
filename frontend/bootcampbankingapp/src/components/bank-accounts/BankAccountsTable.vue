<template>
<button type="button" class="btn btn-top-menu">Create a new account</button>
<button type="button" class="btn btn-top-menu">Show transaction history</button>
<button type="button" class="btn btn-top-menu">Send money</button>


   <table class="table my-5 mx-auto">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Account number</th>
      <th scope="col">Account type</th>
      <th scope="col">Balance</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    <tr v-bind:key="bankAccount.id" v-for="(bankAccount, index) in bankAccounts">
      <td>{{ index + 1 }}</td>
      <td>{{ bankAccount.number }}</td>
      <td>{{ bankAccount.type}} </td>
      <td>{{ $filters.formatCurrency(bankAccount.balance) }}</td>
      <td><button  @click="editBankAccountPopUp(bankAccount.id)" type="button" class="btn btn-outline-success mx-2">Edit</button>
      <button v-if="bankAccount.type === 'Secondary'" @click="deleteBankAccount(bankAccount.id)" type="button" class="btn btn-outline-danger mx-2">Remove</button></td>
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
          .then(() => this.$parent.$parent.setBankAccount())
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

<template>
<button @click="createNewBankAccount()" class="btn btn-top-menu">Create a new account</button>
<router-link to="TransactionsHistory" class="btn btn-top-menu">Show transaction history</router-link>
<router-link to="Transactions"  class="btn btn-top-menu">Send money</router-link>

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
      <td><button  type="button" class="btn btn-outline-success mx-2" data-toggle="modal" data-target="#exampleModal" data-whatever="@getbootstrap"> Edit </button>
      <button v-if="bankAccount.type === 'Secondary'" @click="deleteBankAccount(bankAccount.number)" type="button" class="btn btn-outline-danger mx-2"> Remove </button></td>
    </tr>
  </tbody>

<div class="modal fade" id="exampleModal" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="exampleModalLabel">New message</h5>
        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <div class="modal-body">
        <form>
          <div class="form-group">
            <label for="recipient-name" class="col-form-label">Recipient:</label>
            <input type="text" class="form-control" id="recipient-name">
          </div>
          <div class="form-group">
            <label for="message-text" class="col-form-label">Message:</label>
            <textarea class="form-control" id="message-text"></textarea>
          </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary">Send message</button>
      </div>
    </div>
  </div>
</div>


</table>
</template>

<script>
export default {
    name: "BankAccountsTable",
    components: {
      
    },
    props: ["user","bankAccounts"],

     methods: {
      deleteBankAccount(number){
        if(window.confirm("Are You sure?")) {
          this.$http.delete(process.env.VUE_APP_API_URL + "/bank-accounts/" + number)
          .then(() => this.$parent.$parent.$parent.setBankAccounts())
          .catch(err => console.log(err));
        }
      },

      createNewBankAccount() {
          this.$http.post(process.env.VUE_APP_API_URL + "/bank-accounts/createForUser/" + this.user.id)
          .then(() => this.$parent.$parent.$parent.setBankAccounts())
          .catch(err => console.log(err));
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
    }

    .btn-top-menu:hover {
          background-color: rgba(46, 46, 46, 0.884);
    }

    .table {
        background-color: rgb(240, 240, 240);
        color: black;
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
        font-size: 0,7em;
        font-weight: bold;
    }

</style>

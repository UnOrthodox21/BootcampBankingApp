<template>
<div class="container">
  <div class="row">
    <div class="col-12">
    <form @submit="transferFunds">
  <div class="form-group row mt-0 mb-4">
      <label for="recipientNameInput" class="col-2 col-form-label">Recipient name:</label>
    <div class="col-8">
    <input type="text" class="form-control" id="recipientNameInput" v-model="recipientName" name="recipientName" aria-describedby="recipientHelp" placeholder="Enter recipient name">
  </div>
  </div>
  <div class="form-group row my-4">
        <label for="recipientAccountInput" class="col-2 col-form-label">Recipient account:</label>
        <div class="col-8">
    <input type="text" class="form-control" id="recipientAccountInput" v-model="recipientBankAccount" name="recipientBankAccount" placeholder="Enter recipient bank account">
  </div>
  </div>
     <div class="form-group row my-4">
  <label for="userBankAccountInput" class="col-2 col-form-label">User bank accounts:</label>
      <div class="col-8">
    <select class="form-control" id="userBankAccountInput" v-model="userBankAccount" name="usertBankAccount" placeholder="Select your bank account" >
      <option v-bind:key="bankAccount.id" v-for="bankAccount in bankAccountsLocal" v-bind:value="bankAccount.id" selected="selected">{{bankAccount.number}}</option>
      </select>
  </div>
  </div>
    <div class="form-group row my-4">
    <label for="transferAmountInput" class="col-2 col-form-label">Transfer amount:</label>
    <div class="col-8">
      <!-- Ielikt Max no DB! Nevar būt vairāk nekā ir kontā -->
    <input type="number" step="0.01" min="0" max="99999999" mi class="form-control" id="transferAmountInput" v-model="transferAmount" name="transferAmount" placeholder="Enter transfer amount">
  </div>
  </div>
     <div class="form-group row my-4">
    <label for="descriptionInput" class="col-2 col-form-label">Description</label>
    <div class="col-8">
    <input type="text" class="form-control" id="descriptionInput" v-model="description" name="description" placeholder="Enter description">
  </div>
  </div>
  <div class="form-group row my-5">
    <div class="col-12">
  <button type="submit" class="btn btn-transaction">Submit:</button>
    </div>
    </div>
    </form>
    </div>
  </div>
</div>
</template>

<script>
export default {
    name: "TransactionsForm",
    components: {
    },
    props: ["bankAccounts"],
    data() {
      return {
        bankAccountsLocal: this.bankAccounts,
        recipientName: '',
        recipientBankAccount: '',
        userBankAccount: '',
        transferAmount: 0,
        description: ''
      }
    },
    methods: {
      sortBankAccounts() {
      // sort by name
      this.bankAccountsLocal.sort(function(a, b) {
      var typeA = a.type.toUpperCase(); // ignore upper and lowercase
      var typeB = b.type.toUpperCase(); // ignore upper and lowercase

      if (typeA > typeB) {
        return -1;
      }

      if (typeA < typeB) {
        return 1;
      }

      // names must be equal
      return 0;

      });
    },

    transferFunds(e) {
        e.preventDefault();
        const newTransfer= {
            idTo: this.recipientBankAccount,
            idFrom: this.userBankAccount,
            amount: this.transferAmount
        }

        this.$http.post(process.env.VUE_APP_API_URL + "/bank-accounts/transfer/test", newTransfer)
        .then(() => this.$parent.$parent.setBankAccounts())
        .catch(err => console.log(err));
    }
  },
    mounted() {
      this.sortBankAccounts();
    }
}
</script>

<style scoped>
    .btn-transaction {
        padding: 1em;
        height: 3.5em;
        width: 25em;
        text-align: center;
        vertical-align: center;
        box-shadow: 7px 7px 5px rgb(59, 50, 50);

    }

    .col-form-label {
      text-align: right;
      padding-right: 0em;
    }
</style>

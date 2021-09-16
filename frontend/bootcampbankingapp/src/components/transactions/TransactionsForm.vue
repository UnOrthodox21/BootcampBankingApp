<template>
<div class="container">
  <div class="row">
    <div class="col-12">
    <form>
  <div class="form-group row mt-0 mb-4">
      <label for="recipientNameInput" class="col-2 col-form-label">Recipient name:</label>
    <div class="col-8">
    <input type="text" class="form-control" id="recipientNameInput" aria-describedby="recipientHelp" placeholder="Enter recipient name">
  </div>
  </div>
  <div class="form-group row my-4">
        <label for="recipientAccountInput" class="col-2 col-form-label">Recipient account:</label>
        <div class="col-8">
    <input type="password" class="form-control" id="recipientAccountInput" placeholder="Enter recipient account">
  </div>
  </div>
     <div class="form-group row my-4">
  <label for="userBankAccountInput" class="col-2 col-form-label">User bank accounts:</label>
      <div class="col-8">
    <select class="form-control" id="userBankAccountInput" name="userBankAccounts" placeholder="Select bank account" >
      <option v-bind:key="bankAccount.id" v-for="bankAccount in bankAccounts" v-bind:value="bankAccount.id" selected>{{bankAccount.number}}</option>
      </select>
  </div>
  </div>
    <div class="form-group row my-4">
    <label for="transferAmountInput" class="col-2 col-form-label">Transfer amount:</label>
    <div class="col-8">

      <!-- Ielikt Max no DB! Nevar būt vairāk nekā ir kontā -->
    <input type="number" step="0.01" min="0" max="99999999" mi class="form-control" id="transferAmountInput" placeholder="Enter transfer amount">
  </div>
  </div>
     <div class="form-group row my-4">
    <label for="descriptionInput" class="col-2 col-form-label">Description</label>
    <div class="col-8">
    <input type="text" class="form-control" id="descriptionInput" placeholder="Enter description">
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
    props: ["bankAccounts"]
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

<template>

<div class="container-fluid">
  <div class="row">
    <div class="col-12">
        <BankAccountsBanner/>
        <BankAccountsTable v-bind:user="user" v-bind:bankAccounts="bankAccounts"/>
      </div>
    </div>
</div>

</template>

<script>
import BankAccountsBanner from '../components/bank-accounts/BankAccountsBanner.vue';
import BankAccountsTable from "../components/bank-accounts/BankAccountsTable.vue";

export default {
  name: "BankAccounts",
  components: {
    BankAccountsBanner,
    BankAccountsTable
  },
  props: ["user", "bankAccounts"]
}
</script>

<style scoped>
</style>
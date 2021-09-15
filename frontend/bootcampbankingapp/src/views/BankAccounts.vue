<template>
  <BankAccountsBanner/>
  <BankAccountsTable/>
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
  props: ["user"]
}
</script>

<style scoped>
</style>
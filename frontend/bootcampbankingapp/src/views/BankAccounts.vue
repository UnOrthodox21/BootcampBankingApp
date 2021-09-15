<template>
  <BankAccountsBanner/>
</template>

<script>
import BankAccountsBanner from '../components/bank-accounts/BankAccountsBanner.vue';

export default {
  name: "BankAccounts",
  components: {
    BankAccountsBanner
  },
  props: ["user"]
}
</script>

<style scoped>
</style>
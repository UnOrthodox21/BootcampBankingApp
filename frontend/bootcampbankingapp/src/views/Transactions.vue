<template>
<TransactionsBanner/>
<TransactionsForm/>
</template>

<script>
import TransactionsBanner from "../components/transactions/TransactionsBanner.vue";
import TransactionsForm from "../components/transactions/TransactionsForm.vue";

export default {
  name: "Transactions",
  components: {
    TransactionsBanner,
    TransactionsForm
  },
  props: ["user"]
}
</script>

<style scoped>

</style>


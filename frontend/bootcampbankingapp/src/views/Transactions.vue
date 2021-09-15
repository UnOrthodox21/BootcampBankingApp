<template>
<TransactionsBanner/>
 <TransactionsTable/>
</template>

<script>
import TransactionsBanner from "../components/transactions/TransactionsBanner.vue";
import TransactionsTable from "../components/transactions/TransactionsTable.vue";

export default {
  name: "Transactions",
  components: {
    TransactionsBanner,
    TransactionsTable
  },
  props: ["user"]
}
</script>

<style scoped>

</style>


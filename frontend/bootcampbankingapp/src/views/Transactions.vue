<template>
<TransactionsBanner/>
</template>

<script>
import TransactionsBanner from "../components/transactions/TransactionsBanner.vue";


export default {
  name: "Transactions",
  components: {
    TransactionsBanner
  },
  props: ["user"]
}
</script>

<style scoped>

</style>


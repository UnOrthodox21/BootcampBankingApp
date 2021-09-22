<template>
    <TransactionsHistoryBanner/>
    <TransactionsHistoryTable v-bind:transactions="transactions"/>
</template>

<script>
import TransactionsHistoryBanner from '../components/transactions-history/TransactionsHistoryBanner.vue';
import TransactionsHistoryTable from '../components/transactions-history/TransactionsHistoryTable.vue';

export default {
  name: "TransactionsHistory",
  components: {
    TransactionsHistoryBanner,
    TransactionsHistoryTable
  },
  props: ["user","transactions"]
}
</script>

<style scoped>

</style>


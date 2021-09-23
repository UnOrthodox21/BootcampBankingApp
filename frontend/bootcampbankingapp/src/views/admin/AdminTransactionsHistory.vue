<template>
    <div class="container-fluid">
  <div class="row">
    <div class="col-12">
            <AdminTransactionsHistoryBanner/>
            <AdminTransactionsHistoryTable v-bind:transactions="transactions"/>
      </div>
    </div>
    </div>
</template>

<script>
import AdminTransactionsHistoryBanner from '../../components/admin/transactions-history/AdminTransactionsHistoryBanner.vue';
import AdminTransactionsHistoryTable from '../../components/admin/transactions-history/AdminTransactionsHistoryTable.vue';

export default {
  name: "AdminTransactionsHistory",
  components: {
      AdminTransactionsHistoryBanner,
      AdminTransactionsHistoryTable
  },
  props: ["transactions"]
}
</script>

<style scoped>

</style>


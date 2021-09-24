<template>
    <div class="container-fluid">
  <div class="row">
    <div class="col-12">
            <AdminTransactionsHistoryBanner/>
            <AdminTransactionsHistoryTable v-bind:selectedBankAccountsTransactions="selectedBankAccountsTransactions"/>
      </div>
    </div>
    </div>
</template>

<script>

import {useRouter} from "vue-router";
import AdminTransactionsHistoryBanner from '../../components/admin/transactions-history/AdminTransactionsHistoryBanner.vue';
import AdminTransactionsHistoryTable from '../../components/admin/transactions-history/AdminTransactionsHistoryTable.vue';

export default {
  name: "AdminTransactionsHistory",
  components: {
      AdminTransactionsHistoryBanner,
      AdminTransactionsHistoryTable
  },
  mounted() {
    if (this.user.roles != "Admin") {
       let router = useRouter();
        router.push({ name: 'Home'});
    } 
  },
  props: ["user", "selectedBankAccountsTransactions"]
}
</script>

<style scoped>

</style>


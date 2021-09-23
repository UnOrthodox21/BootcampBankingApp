<template>
    <div class="container-fluid">
  <div class="row">
    <div class="col-12">
            <AdminBankAccountsBanner/>
            <AdminBankAccountsTable v-bind:selectedUsersBankAccounts="selectedUsersBankAccounts"/>
      </div>
    </div>
    </div>
</template>

<script>


import AdminBankAccountsBanner from '../../components/admin/bank-accounts/AdminBankAccountsBanner.vue';
import AdminBankAccountsTable from '../../components/admin/bank-accounts/AdminBankAccountsTable.vue';
export default {
  name: "AdminBankAccounts",
  components: {
    AdminBankAccountsBanner,
    AdminBankAccountsTable
  },
  props: ["selectedUsersBankAccounts"]
}
</script>

<style scoped>

</style>

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

import {useRouter} from "vue-router";
import AdminBankAccountsBanner from '../../components/admin/bank-accounts/AdminBankAccountsBanner.vue';
import AdminBankAccountsTable from '../../components/admin/bank-accounts/AdminBankAccountsTable.vue';
export default {
  name: "AdminBankAccounts",
  components: {
    AdminBankAccountsBanner,
    AdminBankAccountsTable
  },
  mounted() {
    if (this.user.roles != "Admin") {
        let router = useRouter();
        router.push({ name: 'Home'});
    } 
  },
  props: ["user", "selectedUsersBankAccounts"]
}
</script>

<style scoped>

</style>

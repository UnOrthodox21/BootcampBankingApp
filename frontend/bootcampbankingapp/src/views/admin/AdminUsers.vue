<template>
    <div class="container-fluid">
  <div class="row">
    <div class="col-12">
        <AdminUsersBanner/>
        <AdminUsersTable v-bind:selectedUsersBankAccounts="selecetedUsersBankAccounts"/>
      </div>
    </div>
    </div>
</template>

<script>

import AdminUsersBanner from '../../components/admin/users/AdminUsersBanner.vue';
import AdminUsersTable from '../../components/admin/users/AdminUsersTable.vue';

export default {
  name: "AdminUsers",
  components: {
      AdminUsersBanner,
      AdminUsersTable
  },
  props: ["selectedUsersBankAccounts"]
}
</script>

<style scoped>

</style>


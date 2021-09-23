<template>
    <div class="container-fluid">
  <div class="row">
    <div class="col-12">
        <AdminUsersBanner/>
        <AdminUsersTable v-bind:user="user" v-bind:selectedUsersBankAccounts="selecetedUsersBankAccounts"/>
      </div>
    </div>
    </div>
</template>

<script>

import {useRouter} from "vue-router";
import AdminUsersBanner from '../../components/admin/users/AdminUsersBanner.vue';
import AdminUsersTable from '../../components/admin/users/AdminUsersTable.vue';

export default {
  name: "AdminUsers",
  components: {
      AdminUsersBanner,
      AdminUsersTable
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


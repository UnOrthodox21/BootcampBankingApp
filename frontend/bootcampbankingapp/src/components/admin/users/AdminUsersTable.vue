<template>
   <table class="table my-5 mx-auto">
  <thead>
    <tr>
      <th scope="col">ID</th>
      <th scope="col">Username</th>
      <th scope="col">E-mail</th>
      <th scope="col">First name</th>
      <th scope="col">Last name</th>
      <th scope="col">Address</th>
      <th scope="col">Phone</th>
      <th scope="col">Roles</th>
      <th scope="col">Enabled</th>
      <th scope="col"></th>
    </tr>
  </thead>
  <tbody>
    <tr v-bind:key="user.id" v-for="(user, index) in allUsers">
      <td>{{ user.id }}</td>
      <td>{{ user.username }}</td>
      <td>{{ user.email }}</td>
      <td>{{ user.firstName }}</td>
      <td>{{ user.lastName }}</td>
      <td>{{ user.address }}</td>
      <td>{{ user.phone }}</td>
      <td>{{ user.roles }}</td>
      <td>{{ user.enabled }}</td>
      <td><router-link @click="this.$parent.$parent.$parent.setSelectedUsersBankAccounts(user.id)" to="/admin/users/bank-accounts" type="button" class="btn btn-outline-info mx-2"> Bank Accounts </router-link>
      <button @click="setSelectedUser(index)" type="button" class="btn btn-outline-success mx-2" data-toggle="modal" data-target="#userEditModal"> Edit </button>
      <button @click="deleteUser(user)" type="button" class="btn btn-outline-danger mx-2"> Delete </button></td>
    </tr>
  </tbody>
</table>

<div class="modal fade" id="userEditModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalLabel">Edit bank account</h5>
        <button type="button" class="close" id="closeModalButton" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form @submit="editUser">
      <div class="modal-body">
          <div class="form-group">
            <label for="userUsernameInput" class="col-form-label">Username:</label>
          <input type="text" class="form-control" id="userUsernameInput" v-model="selectedUser.username" required>
          </div>
          <div class="form-group">
            <label for="userEmailInput" class="col-form-label">E-mail:</label>
          <input type="email" class="form-control" id="userEmailInput" v-model="selectedUser.email" required>
          </div>
            <div class="form-group">
            <label for="userFirstNameInput" class="col-form-label">First name:</label>
            <input type="text" class="form-control" id="userFirstNamInput" v-model="selectedUser.firstName" required>
          </div>
             <div class="form-group">
            <label for="userLastNameInput" class="col-form-label">Last name:</label>
          <input type="text" class="form-control" id="userLastNameInput" v-model="selectedUser.lastName" required>
          </div>
             <div class="form-group">
            <label for="userAddressInput" class="col-form-label">Address:</label>
          <input type="text" class="form-control" id="userAddressInput" v-model="selectedUser.address" required>
          </div>
             <div class="form-group">
              <label for="userPhoneInput" class="col-form-label">Phone:</label>
              <input type="number" class="form-control" id="userPhoneInput" v-model="selectedUser.phone" required>
          </div>
             <div class="form-group">
             <label for="userNumberInput" class="col-form-label">Password:</label>
              <input type="password" class="form-control" id="userNumberInput" v-model="selectedUser.password" required>
            </div>
             <div class="form-group">
            <label for="userEnabledInput" class="col-form-label">Enabled:</label>
          <select class="form-control" id="userEnabledInput" v-model="selectedUser.enabled"  name="userEnabled" placeholder="Is user enabled?" >
            <option value="true" v-bind:selected="selectedUser.enabled == true"> Enabled </option>
            <option value="false" v-bind:selected="selectedUser.enabled == false"> Disabled </option>
         </select>
          </div>

      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-secondary" data-dismiss="modal">Cancel</button>
        <button type="submit" class="btn btn-primary">Submit</button>
      </div>
      </form>
    </div>
  </div>
</div>

</template>

<script>


export default {
    name: "AdminUsersTable",
    mounted() {
        this.setAllUsers();
    },
    data() {
        return {
            allUsers: [],
            selectedUser: [],
            selectedUserUsername: ''
        }
      },

    props: ["user"],
    methods: {
        setAllUsers() {
            this.$http.get(process.env.VUE_APP_API_URL + "/users/getAllUsers/" + this.user.password)
            .then((response) => { this.allUsers = response.data })
            .catch(err => console.log(err));
        },

        deleteUser(user){
        if(window.confirm("Are You sure?")) {
          this.$http.delete(process.env.VUE_APP_API_URL + "/users/delete/" + user.username)
          .then(() => { 
            this.setAllUsers();
            this.$http.get(process.env.VUE_APP_API_URL + "/bank-accounts/getByUserId/" + user.id).
            then((response) => {
                  let usersBankAccounts = response.data;
                  for (const usersBankAccount of usersBankAccounts) {
                    this.$http.delete(process.env.VUE_APP_API_URL + "/bank-accounts/" + usersBankAccount.number)
                    .catch(e => console.log(e));
                  }
                }).catch(err => console.log(err));
          })
        }
      },

      editUser(e) {
         e.preventDefault();

        this.$http.put(process.env.VUE_APP_API_URL + "/users/" + this.selectedUserUsername, this.selectedUser)
          .then(() => { 
            this.setAllUsers();
            this.$parent.$parent.$parent.setUser(this.user.jwt) ;
            document.getElementById("closeModalButton").click();
          })
          .catch(err => console.log(err));
         },


        setSelectedUser(index) {
        this.selectedUserUsername = this.allUsers[index].username;

        this.selectedUser = {
            "id": this.allUsers[index].id,
            "username": this.allUsers[index].username,
            "email": this.allUsers[index].email,
            "firstName": this.allUsers[index].firstName,
            "lastName": this.allUsers[index].lastName,
            "address": this.allUsers[index].address,
            "phone": this.allUsers[index].phone,
            "password": this.allUsers[index].password,
            "roles": this.allUsers[index].roles,
            "enabled": this.allUsers[index].enabled,
            "jwt": this.allUsers[index].jwt,
            "image": this.allUsers[index].image
        }
      },

    }
}
</script>

<style scoped>
    .table {
        background: rgb(240, 240, 240);
        color: black;
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
        font-size: 0,7em;
        font-weight: bold;
    }

    .modal-dialog {
        color: black;
        font-weight: bold;
    }
</style>

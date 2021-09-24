<template>
<button @click="createNewBankAccount()" class="btn btn-top-menu">Create a new account</button>
<router-link to="/transactions"  class="btn btn-top-menu">Send money</router-link>

   <table class="table my-5 mx-auto">
  <thead>
    <tr>
      <th scope="col">#</th>
      <th scope="col">Account number</th>
      <th scope="col">Account type</th>
      <th scope="col">Balance</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    <tr v-bind:key="bankAccount.id" v-for="(bankAccount, index) in bankAccounts">
      <td>{{ index + 1 }}</td>
      <td> {{ bankAccount.number }}</td>
      <td>{{ bankAccount.type}}</td>
      <td>{{ $filters.formatCurrency(bankAccount.balance) }}</td>
      <td><button @click="setSelectedBankAccount(index)" type="button" class="btn btn-outline-info mx-2" data-toggle="modal" data-target="#bankAccountEditModal"> Edit </button>
      <router-link @click="this.$parent.$parent.$parent.setTransactions(bankAccount.number)" to="/transactions-history" type="button" class="btn btn-outline-success mx-2"> Transaction history </router-link>
      <button v-if="bankAccount.type === 'Secondary'" @click="deleteBankAccount(bankAccount)" type="button" class="btn btn-outline-danger mx-2"> Delete </button></td>
    </tr>
  </tbody>

<div class="modal fade" id="bankAccountEditModal" tabindex="-1" role="dialog" aria-labelledby="modalLabel" aria-hidden="true">
  <div class="modal-dialog" role="document">
    <div class="modal-content">
      <div class="modal-header">
        <h5 class="modal-title" id="modalLabel">Edit bank account</h5>
        <button type="button" class="close" id="closeModalButton" data-dismiss="modal" aria-label="Close">
          <span aria-hidden="true">&times;</span>
        </button>
      </div>
      <form @submit="editBankAccount">
      <div class="modal-body">
          <div class="form-group">
            <label for="accountNumberInput" class="col-form-label">Account number:</label>
          <input type="text" class="form-control" id="accountNumberInput" v-model="selectedBankAccount.number" required>
          </div>
          <div class="form-group">
            <label for="accountTypeInput" class="col-form-label">Account type:</label>
          <select class="form-control" id="accountTypeInput" v-model="selectedBankAccount.type"  name="accountType" placeholder="Select your accountType" disabled>
            <option value="Primary" v-bind:selected="selectedBankAccount.type == 'Primary'">Primary</option>
            <option value="Secondary" v-bind:selected="selectedBankAccount.type == 'Secondary'">Secondary</option>
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


</table>
</template>

<script>
export default {
    name: "BankAccountsTable",
    data() {
      return {
        selectedBankAccount: [],
        selectedBankAccountNumber: ''
      }
    },
    components: {
      
    },
    props: ["user","bankAccounts"],
    
     methods: {
      deleteBankAccount(bankAccount){
        if (bankAccount.balance === 0) {
    
        if(window.confirm("Are You sure?")) {
          this.$http.delete(process.env.VUE_APP_API_URL + "/bank-accounts/" + bankAccount.number)
          .then(() => this.$parent.$parent.$parent.setBankAccounts())
          .catch(err => console.log(err));
        }

       } else {
         alert("Can't delete an account that holds funds. Please empty the account before deletion.")
       }
      },

      createNewBankAccount() {
          this.$http.post(process.env.VUE_APP_API_URL + "/bank-accounts/createForUser/" + this.user.id)
          .then(() => this.$parent.$parent.$parent.setBankAccounts())
          .catch(err => console.log(err));
      },
      
        editBankAccount(e) {
         e.preventDefault();

        this.$http.put(process.env.VUE_APP_API_URL + "/bank-accounts/" + this.selectedBankAccountNumber, this.selectedBankAccount)
          .then(() => { 
            this.$parent.$parent.$parent.setBankAccounts() ;
            document.getElementById("closeModalButton").click();
          })
          .catch(err => console.log(err));
         },

      setSelectedBankAccount(index) {
        this.selectedBankAccountNumber = this.bankAccounts[index].number;

        this.selectedBankAccount = {
            "id": this.bankAccounts[index].id,
            "number": this.bankAccounts[index].number,
            "type": this.bankAccounts[index].type,
            "balance": this.bankAccounts[index].balance
        }
      }
     }
}
</script>

<style scoped>
    .btn-top-menu{
        border-color: rgb(248, 248, 248);
        color: rgb(248, 248, 248);
        font-size: 1.2em;
        padding: 1em;
        height: 3.5em;
        width: 12em;
        text-align: center;
        vertical-align: center;
        margin-bottom: 30px;
    }

    .btn-top-menu:hover {
          background-color: rgba(46, 46, 46, 0.884);
    }

    .table {
        background-color: rgb(240, 240, 240);
        color: black;
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
        font-size: 0,7em;
        font-weight: bold;
    }

</style>

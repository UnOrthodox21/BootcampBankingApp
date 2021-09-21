<template>
    <div class="container" id="home-menu">
        <div class="row">
                <div class="col-6 text-left mt-3 mb-3 pt-2">
                    <div class="card">
                        <div class="card-body">
                    <h2 class="mb-4 home-menu-title">User:</h2>
                     <ul class="home-menu-details ml-0 pl-0">
                        <li><b>Name:</b> {{ user.firstName }} {{ user.lastName }}</li>
                        <li><b>Address:</b> {{ user.address }}</li>
                        <li><b>Phone:</b> {{ user.phone }}</li>
                        <li><b>E-mail:</b> {{ user.email }}</li>
                    </ul>

                    <div class="row">
                     <div class="col-12 text-center">
                    <router-link class="btn btn-user" to="/settings">User Settings</router-link>
                    </div>
                    </div>
                  
                </div>
                </div>
                </div>

                 <div class="col-6 text-left mt-3 mb-3 pt-2">
                    <div class="card">
                        <div class="card-body">

                    <div class="row">
                    <div class="col-6">
                        <h2 class="mb-4 home-menu-title">Bank Accounts:</h2>
                    </div>
                    <div class="col-6">
                         <select class="mt-1" name="bankAccounts"> 
                        <option v-bind:value="bankAccounts.id" selected>1. {{ bankAccounts.number }}</option>
                        <option value="2">2. LVHABA33535353</option>
                        <option value="3">3. LVHABA23232424</option>
                        </select>
                     </div>
                     </div>
                  
                    <div class="row">
                        <div class="col-12">
                      <ul class="home-menu-details ml-0 pl-0"> 
                        <li><b>Number:</b> {{ bankAccounts.number }}</li>
                         <li><b>Type:</b> {{ bankAccounts.type }}</li>
                        <li><b>Balance:</b> {{ bankAccounts.balance }}</li>
                    </ul>
                    </div>
                    </div>
                   
        
                    <div class="row">
                     <div class="col-12 text-center">
                        <router-link class="btn btn-bankaccount" to="/bank-accounts">Bank Accounts</router-link>
                     </div>
                     </div>
                </div>
                </div>
                </div>
        </div>
            <div class="row">
                     <div class="col-12 my-4">
                    <router-link class="btn btn-transaction mt-auto" to="/transactions">Transactions</router-link>
                </div>
            </div>
    </div>
</template>

<script>
export default {
    name: "HomeMenu",
    components: {

    },
    props: ["user", "bankAccounts"]
}
</script>

<style scoped>

    #home-menu {
        border-radius: 15px;
        background: rgb(240, 240, 240);
        color: rgb(55, 55, 55);
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
    }

    .home-menu-title {
        font-size: 1.75em;
        font-weight: bold;
    }

    .home-menu-details {
        font-size: 1.5em;
    }

    .card {
        background: rgb(248, 248, 248);
        position: relative;
        height: 23em;
        box-shadow: 7px 7px 5px rgb(220, 220, 220);
    }

    select {
        font-size: 1.25em;
        float: right;
    }

    ul {
        list-style: none;
    }

    .btn {
        color: rgb(248, 248, 248);
        font-size: 1.2em;
        padding: 1em;
        height: 3.5em;
        width: 12em;
        text-align: center;
        vertical-align: bottom;
        box-shadow: 5px 5px 5px rgb(235, 235, 235);
    }

    .btn-user {
        margin-top: 2em;
        background-color: rgb(102, 102, 102);
    }

    .btn-user:hover {
        background-color: rgb(124, 124, 124);
    }


    .btn-bankaccount {
         margin-top: 3.85em;
         background-color: rgb(102, 102, 102);
    }

    .btn-bankaccount:hover {
          background-color: rgb(124, 124, 124);
    }

    .btn-transaction {
          font-size: 1.4em;
          height: 3.5em;
          width: 45em;
          padding: 1em;
    }

</style>

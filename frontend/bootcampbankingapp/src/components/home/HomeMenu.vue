<template>
    <div class="container" id="home-menu">
        <div class="row">
                <div class="col-6 text-left mt-3 mb-3 pt-2">
                    <div class="card">
                        <div class="card-body">
                    <h2 class="mb-4 home-menu-title">User:</h2>
                     <ul class="home-menu-details">
                        <li>Name: {{user.firstName}} {{user.lastName}}</li>
                        <li>Address: {{user.address}}</li>
                        <li>Phone: {{user.phone}}</li>
                        <li>E-mail: {{user.email}}</li>
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
                    <h2 class="mb-4 home-menu-title">Bank Accounts:</h2>
                    <ul class="home-menu-details"> 
                        <li> {{bankAccounts.number}}</li>
                         <li>Type: {{bankAccounts.type}}</li>
                        <li>Balance: {{bankAccounts.balance}}</li>
                    </ul>
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
                     <div class="col-12 my-5">
                    <router-link class="btn btn-transaction" to="/transactions">Transactions</router-link>
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
        color: black;
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
        height: 23em;
    }

    .btn {
        color: rgb(248, 248, 248);
        font-size: 1.2em;
        padding: 1em;
        height: 3.5em;
        width: 12em;
        text-align: center;
        vertical-align: center;
    }

    .btn-user {
        background-color: rgb(235, 168, 45);
    
    }

    .btn-user:hover {
        background-color: rgba(247, 174, 38, 0.945);
    }


    .btn-bankaccount {
         background-color: rgb(23, 162, 218);
    }

    .btn-bankaccount:hover {
          background-color: rgba(21, 166, 223, 0.884);
    }

</style>

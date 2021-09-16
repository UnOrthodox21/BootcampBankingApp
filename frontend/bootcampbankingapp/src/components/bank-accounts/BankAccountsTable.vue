<template>
<button type="button" class="btn btn-top-menu">Add savings account</button>
<button type="button" class="btn btn-top-menu">Add new adress</button>
<button type="button" class="btn btn-top-menu">Show transaction history</button>
<button type="button" class="btn btn-top-menu">Send money</button>


   <table class="table" >
  <thead>
    <tr>
      <th scope="col">Account adress</th>
      <th scope="col">Account type</th>
      <th scope="col">Balance</th>
      <th scope="col">Actions</th>
    </tr>
  </thead>
  <tbody>
    <tr>
      <th scope="row"><ul class="transaction-table-details"><li>AccountNumber: {{user.number}}</li></ul></th>
      <td><ul class="transaction-table-details"><li>AccountType: {{user.type}}</li></ul></td>
      <td><ul class="transaction-table-details"><li>AccountBalance: {{user.balance}}</li></ul></td>
      <td><button type="button" class="btn btn-outline-success">Edit</button>
      <button type="button" class="btn btn-outline-danger">Remove</button></td>
    </tr>
    <tr>
     <th scope="row">LV55HABA231584965215</th>
      <td>Savings</td>
      <td>int</td>
      <td><button type="button" class="btn btn-outline-success">Edit</button>
      <button type="button" class="btn btn-outline-danger">Remove</button></td>
    </tr>
    <tr>
     <th scope="row">LV55HABA231584965215</th>
      <td>Main</td>
      <td>int</td>
      <td><button type="button" class="btn btn-outline-success">Edit</button>
      <button type="button" class="btn btn-outline-danger">Remove</button></td>
    </tr>
  </tbody>
</table>
</template>

<script>
export default {
    name: "TransactionsTable",
    components: {
      

    },
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
        margin-inline: 10px;
    }

    .btn-top-menu:hover {
          background-color: rgba(46, 46, 46, 0.884);
    }

    

    .table {
        background: rgb(240, 240, 240);
        color: black;
        box-shadow: 10px 10px 5px rgb(32, 32, 32);
        font-size: 0,7em;
        font-weight: bold;
    }
</style>

<template>

    <div class="jumbotron jumbotron-fluid bg-none my-4 pb-4">
  <div class="container">
      
<div v-if="jwt === undefined || jwt === ''">
    <h1 class="display-3">Welcome to our banking app.</h1>
    <p class="lead">Please sign up or log in!</p>
</div>


    <div v-else>
    <h1 class="display-3">Hello there, {{user.firstName}} {{user.lastName}}</h1>
    <p class="lead">Nice to see you visiting our Banking application!</p>
    </div>
  </div>
</div>
</template>

<script>
export default {
    name: "HomeBanner",
    components: {

    },
    props: ["user"]
}
</script>

<style scoped>
    .jumbotron {
        background: none;
    }

    .lead {
        font-size: 1.75em;
    }
</style>

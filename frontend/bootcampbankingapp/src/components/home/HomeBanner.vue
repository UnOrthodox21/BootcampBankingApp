<template>
    <div class="jumbotron jumbotron-fluid bg-none my-4 pb-4">
  <div class="container">
    <h1 class="display-3">Hello there, {{user.firstName}} {{user.lastName}}</h1>
    <p class="lead">Nice to see you visiting our Banking application!</p>
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

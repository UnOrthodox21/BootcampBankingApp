<template>
    <div class="container-fluid footer text-center py-5" id="footer-container">
        <div class="row">
            <div class="col-12">
                ©2021 <router-link to="/thank-you">Team</router-link> 4 Accenture Bootcamp LLC.
            </div>
        </div>
    </div>
</template>


<script>
export default {
    name: "Footer"
}
</script>


<style scoped>
    #footer-container {
        background-color: rgb(240, 240, 240);
        color: black;
    }

    a {
        color: black;
    }
</style>

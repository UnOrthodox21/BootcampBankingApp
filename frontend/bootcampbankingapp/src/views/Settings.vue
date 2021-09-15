<template>
  <SettingsBanner/>
</template>

<script>
import SettingsBanner from '../components/settings/SettingsBanner.vue';

export default {
  name: "Settings",
  components: {
    SettingsBanner
  },
  props: ["user"]
}
</script>

<style scoped>

</style>


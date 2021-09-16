<template>
  <SettingsBanner v-bind:user="user"/>
  <SettingsForm v-bind:user="user"/>
</template>

<script>
import SettingsBanner from '../components/settings/SettingsBanner.vue';
import SettingsForm from '../components/settings/SettingsForm.vue';
export default {
  name: "Settings",
  components: {
    SettingsBanner,
    SettingsForm
  },
  props: ["user"]
}
</script>

<style scoped>
</style>
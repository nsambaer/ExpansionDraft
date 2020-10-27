<template>
  <div id="app">
    <app-header />
    <router-view />
  </div>
</template>

<script>
import AppHeader from '@/components/AppHeader';

export default {

  components: {
    AppHeader
  }

}
</script>

<style>

.container {
  border: 1px solid #000000;
  border-radius: 5px;
  margin: 10px;
}

</style>
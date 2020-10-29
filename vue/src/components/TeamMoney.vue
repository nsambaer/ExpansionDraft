<template>
  <div id='money' class="container">

      <h3 v-show="team != ''">Current Allocation Money: {{ team.allocationMoney }}K</h3>


  </div>
</template>

<script>
export default {

    name: 'team-money',
    props: ['team']

}
</script>

<style>



</style>
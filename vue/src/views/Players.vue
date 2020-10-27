<template>
  <div class='player'>
      <player-table v-bind:players="players"/>
  </div>
</template>

<script>
import PlayerTable from '@/components/PlayerTable.vue'
import service from '@/services/WosoService.js'

export default {

    components: {
        PlayerTable
    },

    data() {
        return {
            players: []
        }
    },

    created() {
    service
      .getAllPlayers()
      .then((response) => {
        this.players = response.data;
      })
      .catch((error) => {
        const response = error.response;
        this.errors = true;
        if (response.status === 400) {
          this.errorMsg = "Bad Request: Validation Errors";
        }
      });
  },


}
</script>

<style>

</style>
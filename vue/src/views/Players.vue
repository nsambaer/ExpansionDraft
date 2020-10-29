<template>
  <div class='player'>
      <player-table v-bind:players="players"/>
  </div>
</template>

<script>
import PlayerTable from '@/components/PlayerTable'
import service from '@/services/WosoService'

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
          this.errorMsg = 'Bad Request: Validation Errors';
        }
      });
  },


}
</script>

<style>

.container {
  border: none;
  border-radius: 5px;
  margin: 10px;
  padding: 10px;
}

</style>
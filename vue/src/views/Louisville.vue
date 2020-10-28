<template>
  <div id="louisville">
    <player-table v-bind:players="louisPlayers" />
    <div id="team-selector">
      <select v-model="selectedTeam" name="team">
        <option value="">--Select a team--</option>
        <option v-for="team in teams" v-bind:key="team.id">
          {{ team.name }}
        </option>
      </select>
      <button v-on:click="changeTeam()">Go</button>
    </div>

    <player-select
      @selection="forceRerender()"
      v-bind:players="availablePlayers"
    />
  </div>
</template>

<script>
import service from '@/services/WosoService';
import PlayerSelect from '@/components/PlayerSelect';
import PlayerTable from '@/components/PlayerTable';

export default {
  name: 'Drafting',

  components: {
    PlayerSelect,
    PlayerTable,
  },

  data() {
    return {
      selectedTeam: '',
      players: [],
      louisPlayers: [],
    };
  },

  methods: {
    forceRerender() {
      this.getLouisville();
      this.changeTeam();
    },

    getLouisville() {
      let louisville = 'Racing Louisville';
      service
        .getTeam(louisville)
        .then((response) => {
          this.louisPlayers = response.data;
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = 'Bad Request: Validation Errors';
          }
        });
    },

    changeTeam() {
      service
        .getTeam(this.selectedTeam)
        .then((response) => {
          this.players = response.data;
          this.forceRerender;
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = 'Bad Request: Validation Errors';
          }
        });
    },
  },
  created() {
    this.getLouisville();
  },

  computed: {
    teams() {
      return this.$store.state.teams;
    },

    availablePlayers() {
      return this.players;
    },
  },
};
</script>

<style>
#team-selector {
  padding: 10px;
}
</style>
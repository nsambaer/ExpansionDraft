<template>
  <div id="teams">
    <div id="team-selector">
      <select v-model="selectedTeam" name="team">
        <option value="">--Select a team--</option>
        <option v-for="team in teams" v-bind:key="team.id">
          {{ team.name }}
        </option>
      </select>
      <button v-on:click="changeTeam()">Go</button>
    </div>

    <player-table v-bind:players="players" :key="key1" />
    <protection v-bind:players="players" :key="key2" />
  </div>
</template>

<script>
import service from "@/services/WosoService.js";
import PlayerTable from "@/components/PlayerTable.vue";
import Protection from "@/components/Protection.vue";

export default {
  name: "Teams",

  components: {
    PlayerTable,
    Protection,
  },

  data() {
    return {
      key1: 1,
      key2: 2,
      selectedTeam: "",
      players: [],
    };
  },

  methods: {
    forceRerender() {
      this.key1 += 1;
      this.key2 += 2;
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
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },
  },

  computed: {
    teams() {
      return this.$store.state.teams;
    },
  },
};
</script>

<style>
#team-selector {
  padding: 10px;
}
</style>
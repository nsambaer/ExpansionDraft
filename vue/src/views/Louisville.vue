<template>
  <div id="louisville">
    <div id="drafting" class="container">
      <select v-model="selectedTeam" name="team">
        <option value="">--Select a team--</option>
        <option v-for="team in teams" v-bind:key="team.id">
          {{ team }}
        </option>
      </select>
      <button v-on:click="changeTeam()">Go</button>
      <player-select
        @selection="forceRerender()"
        v-bind:players="availablePlayers"
      />
    </div>

    <div id="roster" >
    <player-table v-bind:players="louisPlayers" />
    </div>
  </div>
</template>

<script>
import service from "@/services/WosoService";
import PlayerSelect from "@/components/PlayerSelect";
import PlayerTable from "@/components/PlayerTable";

export default {
  name: "Drafting",

  components: {
    PlayerSelect,
    PlayerTable,
  },

  data() {
    return {
      selectedTeam: "",
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
      let louisville = "Racing Louisville";
      service
        .getTeamPlayers(louisville)
        .then((response) => {
          this.louisPlayers = response.data;
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },

    changeTeam() {
      service
        .getTeamPlayers(this.selectedTeam)
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
#louisville {
  display: grid;
  grid-template-columns: 1 fr 1fr;
  grid-template-areas: 
  "drafting roster";
}

#roster {
  grid-area: "roster";
}

#drafting {
  grid-area: "draft";
}



</style>
<template>
  <div id="louisville">
    <player-table id="roster" v-bind:players="louisPlayers" />
    <div id="team-selector" class="container">
      <select v-model="selectedTeam" name="team">
        <option value="">--Select a team--</option>
        <option v-for="team in teams" v-bind:key="team.id">
          {{ team }}
        </option>
      </select>
      <button v-on:click="changeTeam()">Go</button>
    </div>

    <div id="draft-list">
      <player-select
        @selection="forceRerender()"
        v-bind:players="availablePlayers"
      />
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
  grid-template-areas: 
  'select select'
  'roster draft';
}

#roster {
  grid-area: "roster";
}

#draft-list {
  grid-area: "draft";

}

#team-selector {
  grid-area: "select";
}

</style>
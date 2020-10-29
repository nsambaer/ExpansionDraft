<template>
  <div id="teams">
    <div id="team-selector" class="container">
      <select v-model="selectedTeam" name="team">
        <option value="">--Select a team--</option>
        <option v-for="team in teams" v-bind:key="team.id">
          {{ team }}
        </option>
      </select>
      <button v-on:click="changeTeam()">Go</button>
    </div>

    <team-money v-bind:team="realTeam" />
    <player-table v-bind:players="players" />
    <player-protect v-bind:players="players" />
    <international-players @buy="forceRerender()" v-bind:players="intPlayers" v-bind:team="realTeam" />
    <trade
      @trade="forceRerender()"
      v-bind:players="players"
      v-bind:teams="filterTeams"
      v-bind:selectedTeam="selectedTeam"
    />
  </div>
</template>

<script>
import service from "@/services/WosoService";
import TeamMoney from "@/components/TeamMoney";
import PlayerTable from "@/components/PlayerTable";
import PlayerProtect from "@/components/PlayerProtect";
import Trade from "@/components/TradeWindow";
import InternationalPlayers from '@/components/InternationalPlayers'

export default {
  name: "Teams",

  components: {
    PlayerTable,
    PlayerProtect,
    Trade,
    TeamMoney,
    InternationalPlayers,
  },

  data() {
    return {
      selectedTeam: "",
      players: [],
      filterTeams: [],
      realTeam: "",
      intPlayers: [],
    };
  },

  methods: {
    forceRerender() {
      this.changeTeam();
      this.getInt();
    },

    changeTeam() {
      service
        .getTeamPlayers(this.selectedTeam)
        .then((response) => {
          this.players = response.data;
          this.filterTeams = this.teams.filter((team) => {
            return team != this.selectedTeam;
          });
          service
            .getTeam(this.selectedTeam)
            .then((response) => {
              this.realTeam = response.data;
              this.forceRerender;
            })
            .catch((error) => {
              const response = error.response;
              this.errors = true;
              if (response.status === 400) {
                this.errorMsg = "Bad Request: Validation Errors";
              }
            });
        })
        .catch((error) => {
          const response = error.response;
          this.errors = true;
          if (response.status === 400) {
            this.errorMsg = "Bad Request: Validation Errors";
          }
        });
    },

    getInt() {
      service
        .getIntPlayers()
        .then((response) => {
          this.intPlayers = response.data;
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
    this.getInt();
  },

  computed: {
    teams() {
      return this.$store.state.teams;
    },
  },
};
</script>

<style>

</style>
<template>
  <div id="trade" v-show="teams.length > 0" class="container">
    <h2>Trade Window</h2>
    <p v-show="error" id="error-message">{{ errorMessage }}</p>
    <label class="trade" for="trade-team">Select Team to Trade To: </label>
    <select class="trade" name="trade-team" id="trade-team" v-model="tradeTeam">
      <option v-for="team in teams" v-bind:key="team.id">
        {{ team }}
      </option>
    </select>
    <br />
    <label class="trade" for="trade-player">Select Player to Trade: </label>
    <select
      class="trade"
      name="trade-player"
      id="trade-player"
      v-model="tradePlayer"
    >
      <option
        v-for="player in players"
        v-bind:key="player.playerId"
        v-bind:value="{
          playerId: player.playerId,
          name: player.name,
          position: player.position,
          allocated: player.allocated,
          protecc: player.protecc,
          teamName: player.teamName,
        }"
      >
        {{ player.name }}
      </option>
    </select>
    <br />
    <label class="trade" for="trade-money"
      >Enter an Amount of Allocation Money to Trade:
    </label>
    <input
      class="trade"
      type="number"
      min="0"
      v-model="money.allocationMoney"
    />
    <button v-on:click="trade()">Trade!</button>
  </div>
</template>

<script>
import service from "@/services/WosoService";

export default {
  name: "TradeWindow",
  data() {
    return {
      tradePlayer: "",
      tradeTeam: "",
      error: false,
      errorMessage: "",
      money: {
        teamName: '',
        allocationMoney: ''
      }
    };
  },

  props: ["players", "teams", "selectedTeam"],

  methods: {
    trade() {
      if (this.tradeTeam === "") {
        this.error = true;
        this.errorMessage = "You must select a team to trade to";
      } else if (this.tradePlayer == "" && this.money.allocationMoney == "") {
        this.error = true;
        this.errorMessage =
          "You must select a player or amount of money to trade";
      } else {
        this.error = false;
        if (this.tradePlayer != "") {
          service
            .changeTeam(this.tradeTeam, this.tradePlayer)
            .then((response) => {
              if (response.status == 202) {
                console.log("Successful player trade");
              }
              this.tradeTeam = '';
              this.tradePlayer = '';
              this.$emit("trade");
            })
            .catch((error) => {
              const response = error.response;
              this.errors = true;
              if (response.status === 400) {
                alert("Bad Request: Validation Errors");
              }
            });
        }
        if (this.money.allocationMoney != "") {
          this.money.teamName = this.selectedTeam;
          service
            .sendMoney(this.tradeTeam, this.money)
            .then((response) => {
              if (response.status == 202) {
                console.log("Successful money trade");
              }
              this.tradeTeam = '';
              this.money = { teamName: '', allocationMoney: ''};
              this.$emit("trade");
            })
            .catch((error) => {
              const response = error.response;
              this.errors = true;
              if (response.status === 400) {
                alert("Bad Request: Validation Errors");
              }
            });
        }
      }
    },
  },
};
</script>

<style>


#error-message {
  color: red;
  
}
</style>
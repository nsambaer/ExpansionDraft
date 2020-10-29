<template>
  <div id="protection" class="container">
    <h2>Protected List</h2>

    <p v-show="error" id="error-message">{{errorMessage}}</p>

    <h4>US Allocated Players</h4>
    <table>
      <tr>
        <th>
          <div class="table-head">
            <div>Player</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('name')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Position</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('position')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Protect</div>
          </div>
        </th>
      </tr>
      <tr v-if="players.length == 0">
        <td id="no-team" colspan="5">No team selected</td>
      </tr>
      <tr v-if="allocatedPlayers.length == 0 && players.length > 0">
        <td id="no-team" colspan="5">No allocated players on roster</td>
      </tr>
      <tr v-for="player in allocatedPlayers" v-bind:key="player.playerId">
        <td>{{ player.name }}</td>
        <td>{{ player.position }}</td>
        <td>
          <input
            id="protection-toggle"
            v-model="player.protecc"
            type="checkbox"
          />
        </td>
      </tr>
    </table>

    <h4>Non-Allocated Players</h4>
    <table>
      <tr>
        <th>
          <div class="table-head">
            <div>Player</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('name')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Position</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('position')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Protect</div>
          </div>
        </th>
      </tr>
      <tr v-if="players.length == 0">
        <td id="no-team" colspan="5">No team selected</td>
      </tr>
      <tr v-for="player in nonAllocatedPlayers" v-bind:key="player.playerId">
        <td>{{ player.name }}</td>
        <td>{{ player.position }}</td>
        <td>
          <input
            id="protection-toggle"
            v-model="player.protecc"
            type="checkbox"
          />
        </td>
      </tr>
    </table>

    <div>
      <button id="protection-submit" v-on:click="protect()">Submit</button>
    </div>
  </div>
</template>

<script>
import service from "@/services/WosoService.js";

export default {
  data() {
    return {
      error: false,
      errorMessage: "",
    };
  },
  props: ["players"],

  computed: {
    allocatedPlayers() {
      let filterList = this.players.filter((player) => {
        return player.allocated == true;
      });
      return filterList;
    },

    nonAllocatedPlayers() {
      let filterList = this.players.filter((player) => {
        return player.allocated == false;
      });
      return filterList;
    },
  },

  methods: {
    protect() {
      let playerList = this.allocatedPlayers.concat(this.nonAllocatedPlayers);

      if (this.protectionLogic(playerList)) {
        this.error = false;
        service
          .updateProtection(playerList)
          .then((response) => {
            if (response.status == 202) {
              console.log("Successful protection");
            }
          })
          .catch((error) => {
            const response = error.response;
            this.errors = true;
            if (response.status === 400) {
              alert("Bad Request: Validation Errors");
            }
          });
      } else {
        this.error = true;
        this.errorMessage =
          "Invalid protection selections. Either too many total players or allocated players are selected.";
      }
    },

    protectionLogic(playerList) {
      let total = 0;
      let allocateCt = 0;
      playerList.foreach((player) => {
        if (player.protecc == true) {
          total++;
          if (player.allocated == true) {
            allocateCt++;
          }
        }
      });

      if (total > 11 || allocateCt > 2) {
        return false;
      }

      return true;
    },
  },
};
</script>

<style>
table {
  border-collapse: collapse;
}

table,
th,
td {
  border: 1px solid black;
}

th,
td {
  padding: 5px;
}

#no-team {
  text-align: center;
}

.table-head {
  display: flex;
  justify-content: space-between;
}


.sort {
  padding: 0px 5px 0px;
  max-width: 10px;
}


#error-message {
    color:  red;

}


</style>
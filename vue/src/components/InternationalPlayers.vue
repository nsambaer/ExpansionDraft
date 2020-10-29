<template>
  <div id="int-players" class="container">
    <h2>International Player List</h2>

    <p v-show="error" id="error-message">{{ errorMessage }}</p>

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
            <div>Team</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('teamName')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Price</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('price')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Buy</div>
          </div>
        </th>
      </tr>
      <tr v-for="player in sortedPlayers" v-bind:key="player.playerId">
        <td>{{ player.name }}</td>
        <td>{{ player.position }}</td>
        <td>{{ player.teamName }}</td>
        <td>{{ player.price }}</td>
        <td>
          <input type="checkbox" v-model="player.buy" />
        </td>
      </tr>
    </table>

    <div>
      <button id="protection-submit" v-on:click="buy()">Buy!</button>
    </div>
  </div>
</template>

<script>
import service from "@/services/WosoService";

export default {
  data() {
    return {
      error: false,
      errorMessage: "",
      currentSort: "name",
      currentSortDir: "asc",
    };
  },

  props: ["players", "team"],

  methods: {
    sort: function (s) {
      //if s == current sort, reverse
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      }
      this.currentSort = s;
    },

    buy() {
      let buyList = this.players.filter((player) => {
        return player.buy;
      });

      if (this.buyLogic(buyList)) {
        this.error = false;
        this.errorMessage = "";
        service
          .buyPlayers(this.team.teamName, buyList)
          .then((response) => {
            if (response.status == 201) {
              console.log("Successful purchase");
            }
            this.$emit("buy");
          })
          .catch((error) => {
            const response = error.response;
            this.errors = true;
            if (response.status === 400) {
              alert("Bad Request: Validation Errors");
            }
          });
      }
    },

    buyLogic(buyList) {
      if (buyList.length == 0) {
        this.error = true;
        this.errorMessage = "You must select a player to buy";
        return false;
      }

      let total = 0;
      buyList.forEach((player) => {
        total += player.price;
      });

      if (total > this.team.allocationMoney) {
        this.error = true;
        this.errorMessage =
          "You cannot spend more money than you have. The league is not that corrupt... yet";
        this.players.forEach( (player) => {
            player.buy = false;
        });
        return false;
      }
      return true;
    },
  },

  computed: {
    sortedPlayers: function () {
      // eslint-disable-next-line
      return this.players.sort((a, b) => {
        let modifier = 1;
        if (this.currentSortDir === "desc") modifier = -1;
        if (a[this.currentSort] < b[this.currentSort]) return -1 * modifier;
        if (a[this.currentSort] > b[this.currentSort]) return 1 * modifier;
        return 0;
      });
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
  color: red;
}
</style>
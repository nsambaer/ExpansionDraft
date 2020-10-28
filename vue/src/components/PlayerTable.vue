<template>
  <div class="player-table">
    <h2>Player List</h2>
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
            <div>Allocated?</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('allocated')"
            />
          </div>
        </th>
        <th>
          <div class="table-head">
            <div>Protected?</div>
            <img
              class="sort"
              src="../assets/sortSmall.png"
              v-on:click="sort('protecc')"
            />
          </div>
        </th>
      </tr>
      <tr v-if="players.length == 0">
        <td id="no-team" colspan='5'>No team selected</td>
      </tr>
      <tr v-for="player in sortedPlayers" v-bind:key="player.playerId">
        <td>{{ player.name }}</td>
        <td>{{ player.position }}</td>
        <td>{{ player.teamName }}</td>
        <td v-if="player.allocated">Yes</td>
        <td v-if="!player.allocated">No</td>
        <td v-if="player.protecc">Yes</td>
        <td v-if="!player.protecc">No</td>
      </tr>
    </table>
  </div>
</template>

<script>
export default {
  data() {
    return {
      currentSort: "name",
      currentSortDir: "asc",
    };
  },

  props: ["players"],

  methods: {
    sort: function (s) {
      //if s == current sort, reverse
      if (s === this.currentSort) {
        this.currentSortDir = this.currentSortDir === "asc" ? "desc" : "asc";
      }
      this.currentSort = s;
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
  margin: 10px;
}

table,
th,
td {
  border: 1px solid black;
}

th, td {
  padding: 5px;
}

#no-team {
  text-align: center;
}

.table-head {
  display: flex;
  justify-content: space-between;
}

h2 {
  margin-left: 2px;
}

.sort {
  padding: 0px 5px 0px;
  max-width: 10px;
}

</style>
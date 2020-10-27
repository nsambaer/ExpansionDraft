import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
  });

export default {

  getAllPlayers() {
    return http.get('/players/all');
  },

  getTeam(teamName) {
    return http.get(`/players/${teamName}`);
  },

  changeTeam(teamName, player) {
    return http.put(`/players/${teamName}`, player);
  }

}

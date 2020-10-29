import axios from 'axios';

const http = axios.create({
    baseURL: "http://localhost:8080"
  });

export default {

  getAllPlayers() {
    return http.get('/players/all');
  },

  getIntPlayers() {
    return http.get('/intPlayers/all');
  },

  getTeamPlayers(teamName) {
    return http.get(`/players/${teamName}`);
  },

  getTeam(teamName) {
    return http.get(`/teams/${teamName}`);
  },

  changeTeam(teamName, player) {
    return http.put(`/teams/${teamName}`, player);
  },

  sendMoney(teamName, money) {
    return http.put(`/trade/${teamName}`, money)
  },

  updateProtection(playerList) {
    return http.put(`/players`, playerList);
  },

  updateSelection(playerList) {
    return http.put(`/draft`, playerList);
  },

  buyPlayers(teamName, playerList) {
    return http.post(`/teams/${teamName}`, playerList);
  },

}

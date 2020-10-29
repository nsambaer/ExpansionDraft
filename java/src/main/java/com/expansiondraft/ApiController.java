package com.expansiondraft;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.expansiondraft.dao.IntPlayerDAO;
import com.expansiondraft.dao.PlayerDAO;
import com.expansiondraft.dao.TeamDAO;
import com.expansiondraft.model.IntPlayer;
import com.expansiondraft.model.Player;
import com.expansiondraft.model.Team;

/**
 * ApiController
 */
@RestController
@CrossOrigin
public class ApiController {

	@Autowired
	private PlayerDAO pDAO;

	@Autowired
	private TeamDAO tDAO;

	@Autowired
	private IntPlayerDAO ipDAO;

	@RequestMapping(path = "players/all", method = RequestMethod.GET)
	public List<Player> listPlayers() {

		return pDAO.getAllPlayers();
	}

	@RequestMapping(path = "intPlayers/all", method = RequestMethod.GET)
	public List<IntPlayer> listIntPlayers() {
		return ipDAO.getAllIntPlayers();
	}

	@RequestMapping(path = "players/{teamName}", method = RequestMethod.GET)
	public List<Player> listPlayersForTeamId(@PathVariable String teamName) {
		return pDAO.getPlayersTeam(teamName);
	}

	@RequestMapping(path = "teams/{teamName}", method = RequestMethod.GET)
	public Team getTeam(@PathVariable String teamName) {
		return tDAO.getTeam(teamName);
	}

	@RequestMapping(path = "teams/{teamName}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Player changeTeam(@PathVariable String teamName, @RequestBody Player player) {
		return pDAO.changePlayerTeam(player, teamName);
	}

	@RequestMapping(path = "trade/{teamName}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean tradeMoney(@PathVariable String teamName, @RequestBody Team team) {
		String teamFrom = team.getTeamName();
		String teamTo = teamName;
		int amount = team.getAllocationMoney();
		Team from = tDAO.tradeMoney(teamFrom, -amount);
		Team to = tDAO.tradeMoney(teamTo, amount);
		return true;
	}

	@RequestMapping(path = "teams/{teamName}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.CREATED)
	public List<Player> buyPlayers(@PathVariable String teamName, @RequestBody List<IntPlayer> intPlayerList) {
		List<Player> playerList = new ArrayList<>();
		for (IntPlayer intPlayer : intPlayerList) {
			tDAO.tradeMoney(teamName, -1 * intPlayer.getPrice());
			ipDAO.deleteIntPlayer(intPlayer.getPlayerId());
			playerList.add(pDAO.buyPlayer(teamName, intPlayer));
		}

		return playerList;
	}

	@RequestMapping(path = "players", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean updateProtection(@RequestBody List<Player> players) {
		return pDAO.updateProtection(players);
	}

	@RequestMapping(path = "draft", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean updateSelection(@RequestBody List<Player> players) {
		for (Player player : players) {
			pDAO.changePlayerTeam(player, "Racing Louisville");
		}
		return true;
	}

}
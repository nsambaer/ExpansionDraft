package com.expansiondraft;

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

import com.expansiondraft.dao.PlayerDAO;
import com.expansiondraft.dao.TeamDAO;
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
	
	@RequestMapping(path = "players/all", method = RequestMethod.GET)
    public List<Player> listPlayers() {
    	
    	return pDAO.getAllPlayers();
    }
	
	@RequestMapping(path = "teams/{teamName}", method = RequestMethod.GET)
    public List<Player> listPlayersForTeamId(@PathVariable String teamName) {
    	return pDAO.getPlayersTeam(teamName);
    }
	
	@RequestMapping(path = "teams/{teamName}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Player changeTeam(@PathVariable String teamName, @RequestBody Player player) {
		return pDAO.changePlayerTeam(player, teamName);
	}
	
	@RequestMapping(path = "trade/{teamName}", method = RequestMethod.POST)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean tradeMoney(@PathVariable String teamName, @RequestBody Team team) {
		String teamFrom = team.getTeamName();
		String teamTo = teamName;
		int amount = team.getAllocationMoney();
		Team from = tDAO.tradeMoney(teamFrom, -amount);
		Team to = tDAO.tradeMoney(teamTo,  amount);
		System.out.println(from.getTeamName() + "- " + from.getAllocationMoney());
		System.out.println(to.getTeamName() + "- " + to.getAllocationMoney());
		return true;
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
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
import com.expansiondraft.model.Player;


/**
 * ApiController
 */
@RestController
@CrossOrigin
public class ApiController {

	@Autowired
    private PlayerDAO pDAO;
	
	@RequestMapping(path = "players/all", method = RequestMethod.GET)
    public List<Player> listPlayers() {
    	
    	return pDAO.getAllPlayers();
    }
	
	@RequestMapping(path = "players/{teamName}", method = RequestMethod.GET)
    public List<Player> listPlayersForTeamId(@PathVariable String teamName) {
    	return pDAO.getPlayersTeam(teamName);
    }
	
	@RequestMapping(path = "players/{teamName}", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public Player changeTeam(@PathVariable String teamName, @RequestBody Player player) {
		return pDAO.changePlayerTeam(player, teamName);
	}
	
	@RequestMapping(path = "players", method = RequestMethod.PUT)
	@ResponseStatus(HttpStatus.ACCEPTED)
	public boolean updateProtection(@RequestBody List<Player> players) {
		return pDAO.updateProtection(players);
	}
	
}
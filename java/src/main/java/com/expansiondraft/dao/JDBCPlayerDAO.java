package com.expansiondraft.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.expansiondraft.model.IntPlayer;
import com.expansiondraft.model.Player;

@Component
public class JDBCPlayerDAO implements PlayerDAO {

	
	private JdbcTemplate jdbc;
	
	public JDBCPlayerDAO(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	
	
	@Override
	public List<Player> getAllPlayers() {
		List<Player> playerList = new ArrayList<Player>();
		String sqlSelect = "SELECT * FROM players";
		
		SqlRowSet results = jdbc.queryForRowSet(sqlSelect);
		
		while (results.next()) {
			playerList.add(mapRowToPlayer(results));
		}
		
		return playerList;
	}

	@Override
	public List<Player> getPlayersTeam(String teamName) {
		List<Player> playerList = new ArrayList<Player>();
		String sqlSelect = "SELECT * FROM players WHERE team_name ILIKE ?";
		
		SqlRowSet results = jdbc.queryForRowSet(sqlSelect, teamName);
		
		while (results.next()) {
			playerList.add(mapRowToPlayer(results));
		}
		
		return playerList;
	}

	
	
	@Override
	public Player changePlayerTeam(Player player, String teamName) {
		Player newPlayer = player;
		
		String sqlUpdate = "UPDATE players SET team_name = ? WHERE player_id = ?";
		
		jdbc.update(sqlUpdate, teamName, player.getPlayerId());
		
		newPlayer.setTeamName(teamName);
		
		return newPlayer;
	}
	
	@Override
	public boolean updateProtection(List<Player> players) {
		
		String sqlUpdate = "UPDATE players SET protected = ? WHERE player_id = ?";
		int sum = 0;
		for (Player player : players) {
			int updated = jdbc.update(sqlUpdate, player.isProtecc(), player.getPlayerId());
			sum += updated;
		}
		
		if (sum == players.size()) {						
			return true;
		}
		return false;
	}
	
	@Override
	public Player buyPlayer(String teamName, IntPlayer intPlayer) {
		Player player = new Player();
		player.setName(intPlayer.getName());
		player.setPosition(intPlayer.getPosition());
		player.setTeamName(teamName);
		player.setAllocated(false);
		player.setProtecc(false);
		
		String sqlInsert = "INSERT INTO players (name, position, team_name) "
				+ "VALUES (?, ?, ?)";
		
		jdbc.update(sqlInsert, player.getName(), player.getPosition(), player.getTeamName());
				
		return player;
	}
	
	private Player mapRowToPlayer(SqlRowSet results) {
		Player player = new Player();
		player.setPlayerId(results.getInt("player_id"));
		player.setName(results.getString("name"));
		player.setPosition(results.getString("position"));
		player.setTeamName(results.getString("team_name"));
		player.setProtecc(results.getBoolean("protected"));
		player.setAllocated(results.getBoolean("allocated"));
		return player;
	}





}

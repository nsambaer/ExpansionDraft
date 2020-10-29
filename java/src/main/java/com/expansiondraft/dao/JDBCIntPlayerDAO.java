package com.expansiondraft.dao;

import java.util.ArrayList;
import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.expansiondraft.model.IntPlayer;

@Component
public class JDBCIntPlayerDAO implements IntPlayerDAO {

private JdbcTemplate jdbc;
	
	public JDBCIntPlayerDAO(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	
	@Override
	public List<IntPlayer> getAllIntPlayers() {
		List<IntPlayer> playerList = new ArrayList<IntPlayer>();
		String sqlSelect = "SELECT * FROM int_players";
				
		SqlRowSet results = jdbc.queryForRowSet(sqlSelect);
		
		while (results.next()) {
			playerList.add(mapRowToIntPlayer(results));
		}
		
		return playerList;
	}

	@Override
	public boolean deleteIntPlayer(int playerId) {
		
		String sqlDelete = "DELETE FROM int_players WHERE player_id = ?";
		
		jdbc.update(sqlDelete, playerId);
		
		return true;
	}
	
	public IntPlayer mapRowToIntPlayer(SqlRowSet results) {
		IntPlayer intPlayer = new IntPlayer();
	
		intPlayer.setPlayerId(results.getInt("player_id"));
		intPlayer.setName(results.getString("name"));
		intPlayer.setPosition(results.getString("position"));
		intPlayer.setTeamName(results.getString("team_name"));
		intPlayer.setPrice(results.getInt("price"));
		
		
		return intPlayer;
	}

}

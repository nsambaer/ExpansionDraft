package com.expansiondraft.dao;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Component;

import com.expansiondraft.model.Team;

@Component
public class JDBCTeamDAO implements TeamDAO {

private JdbcTemplate jdbc;
	
	public JDBCTeamDAO(JdbcTemplate jdbc) {
		this.jdbc = jdbc;
	}
	
	
	@Override
	public Team getTeam(String teamName) {
		Team team = new Team();
		String sqlSelect = "SELECT * FROM teams WHERE name ILIKE ?";
		
		SqlRowSet results = jdbc.queryForRowSet(sqlSelect, teamName);
		
		if (results.next()) {
			team = mapRowToTeam(results);
		}
		
		return team;
	}

	@Override
	public Team tradeMoney(String teamName, int amount) {
		Team team = getTeam(teamName);
		int newMoney = team.getAllocationMoney() + amount;
		
		String sqlUpdate = "UPDATE teams SET money = ? WHERE team_id = ?";
		
		jdbc.update(sqlUpdate, newMoney, team.getTeamId());
		
		return team;
	}
	
	private Team mapRowToTeam(SqlRowSet results) {
		Team team = new Team();
		
		team.setTeamId(results.getInt("team_id"));
		team.setTeamName(results.getString("name"));
		team.setAllocationMoney(results.getInt("money"));
		
		return team;
	}

}

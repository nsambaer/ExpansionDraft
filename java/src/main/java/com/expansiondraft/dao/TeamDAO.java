package com.expansiondraft.dao;

import com.expansiondraft.model.Team;


public interface TeamDAO {

	public Team getTeam(String teamName);
	public Team tradeMoney(String teamName, int amount);
	
}

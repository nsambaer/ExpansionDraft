package com.expansiondraft.dao;

import java.util.List;

import com.expansiondraft.model.IntPlayer;

public interface IntPlayerDAO {

	public List<IntPlayer> getAllIntPlayers();
	public boolean deleteIntPlayer(int playerId);
	
}

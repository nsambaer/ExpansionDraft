package com.expansiondraft.dao;

import java.util.List;

import com.expansiondraft.model.IntPlayer;
import com.expansiondraft.model.Player;

public interface PlayerDAO {

	public List<Player> getAllPlayers();
	public List<Player> getPlayersTeam(String teamName);
	public Player changePlayerTeam(Player player, String teamName);
	public Player buyPlayer(String teamName, IntPlayer intPlayer);
	public boolean updateProtection(List<Player> players);
}

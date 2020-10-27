package com.expansiondraft.model;

public class Player {

	private int playerId;
	private String name;
	private String teamName;
	private String position;
	private boolean protecc;
	private boolean allocated;
	
	public Player (int playerId, String name, String teamName, String position, boolean protecc, boolean allocated) {
		this.playerId = playerId;
		this.name = name;
		this.teamName = teamName;
		this.position = position;
		this.protecc = protecc;
		this.allocated = allocated;
	}
	
	public Player() {
		
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String teamName) {
		this.teamName = teamName;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public boolean isProtecc() {
		return protecc;
	}

	public void setProtecc(boolean protecc) {
		this.protecc = protecc;
	}

	public boolean isAllocated() {
		return allocated;
	}

	public void setAllocated(boolean allocated) {
		this.allocated = allocated;
	}
	
	
	
	
	
}

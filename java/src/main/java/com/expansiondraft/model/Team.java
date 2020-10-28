package com.expansiondraft.model;

public class Team {

	private int teamId;
	private String teamName;
	private int allocationMoney;
	
	public Team () {
		
	}
	
	public Team(int teamId, String name, int allocationMoney) {
		this.teamId = teamId;
		this.teamName = name;
	}

	public String getTeamName() {
		return teamName;
	}

	public void setTeamName(String name) {
		this.teamName = name;
	}

	public int getTeamId() {
		return teamId;
	}

	public void setTeamId(int teamId) {
		this.teamId = teamId;
	}

	public int getAllocationMoney() {
		return allocationMoney;
	}

	public void setAllocationMoney(int allocationMoney) {
		this.allocationMoney = allocationMoney;
	}
	
	
	
	
}

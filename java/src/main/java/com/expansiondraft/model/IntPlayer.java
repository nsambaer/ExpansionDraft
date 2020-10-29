package com.expansiondraft.model;

public class IntPlayer extends Player {
	
	private int price;

	public IntPlayer(int playerId, String name, String teamName, String position, int price) {
		super(playerId, name, teamName, position);
		this.price = price;
	}
	
	public IntPlayer() {
		
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}
	
	
	
	
	
	
}

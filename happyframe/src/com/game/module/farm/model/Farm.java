package com.game.module.farm.model;

public class Farm {
	
	
	private int farmId;
	private int type;
	private int playerId;
	private int plantId;
	private long startTime;
	private int state;//状态,0为没有东西，1为幼苗期，2为生长期，3为成熟期，4为收获期，5为死亡
	
	public int getPlayerId() {
		return playerId;
	}
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}
	public int getFarmId() {
		return farmId;
	}
	public void setFarmId(int farmId) {
		this.farmId = farmId;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getPlantId() {
		return plantId;
	}
	public void setPlantId(int plantId) {
		this.plantId = plantId;
	}
	public long getStartTime() {
		return startTime;
	}
	public void setStartTime(long startTime) {
		this.startTime = startTime;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String toMsg() {
		return farmId + "|" + type + "|" + plantId + "|" + startTime; 
	}
	

}

package com.game.module.farm.model;

public class CropInfo {
	private int corpId;
	private String name;
	private int type;  //1为水稻，2为非水稻
	private int childTime; //幼年期时间
	private String childIcon;//幼年期Icon
	private String childDisaster;//幼年期可发生的天灾

	private int growTime;//成长期时间
	private String growIcon;//成长期Icon
	private String growDisaster;//成长期可发生的天灾

	private int ripeTime;//成熟期时间
	private String ripeIcon;//成熟期Icon
	private String ripeDisaster;//成熟期可发生的天灾

	private int gainTime;//收获期时间
	private String gainIcon;//收获期Icon
	private String gainDisaster;//收获期可发生的天灾
	
	private int dead;//死亡
	private String deadIcon;//死亡期Icon
	private int gainId;//果实Id
	public int getCorpId() {
		return corpId;
	}
	public void setCorpId(int corpId) {
		this.corpId = corpId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getType() {
		return type;
	}
	public void setType(int type) {
		this.type = type;
	}
	public int getChildTime() {
		return childTime;
	}
	public void setChildTime(int childTime) {
		this.childTime = childTime;
	}
	public String getChildIcon() {
		return childIcon;
	}
	public void setChildIcon(String childIcon) {
		this.childIcon = childIcon;
	}
	public String getChildDisaster() {
		return childDisaster;
	}
	public void setChildDisaster(String childDisaster) {
		this.childDisaster = childDisaster;
	}
	public int getGrowTime() {
		return growTime;
	}
	public void setGrowTime(int growTime) {
		this.growTime = growTime;
	}
	public String getGrowIcon() {
		return growIcon;
	}
	public void setGrowIcon(String growIcon) {
		this.growIcon = growIcon;
	}
	public String getGrowDisaster() {
		return growDisaster;
	}
	public void setGrowDisaster(String growDisaster) {
		this.growDisaster = growDisaster;
	}
	public int getRipeTime() {
		return ripeTime;
	}
	public void setRipeTime(int ripeTime) {
		this.ripeTime = ripeTime;
	}
	public String getRipeIcon() {
		return ripeIcon;
	}
	public void setRipeIcon(String ripeIcon) {
		this.ripeIcon = ripeIcon;
	}
	public String getRipeDisaster() {
		return ripeDisaster;
	}
	public void setRipeDisaster(String ripeDisaster) {
		this.ripeDisaster = ripeDisaster;
	}
	public int getGainTime() {
		return gainTime;
	}
	public void setGainTime(int gainTime) {
		this.gainTime = gainTime;
	}
	public String getGainIcon() {
		return gainIcon;
	}
	public void setGainIcon(String gainIcon) {
		this.gainIcon = gainIcon;
	}
	public String getGainDisaster() {
		return gainDisaster;
	}
	public void setGainDisaster(String gainDisaster) {
		this.gainDisaster = gainDisaster;
	}
	public int getDead() {
		return dead;
	}
	public void setDead(int dead) {
		this.dead = dead;
	}
	public String getDeadIcon() {
		return deadIcon;
	}
	public void setDeadIcon(String deadIcon) {
		this.deadIcon = deadIcon;
	}
	public int getGainId() {
		return gainId;
	}
	public void setGainId(int gainId) {
		this.gainId = gainId;
	}
	
	
}

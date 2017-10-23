package com.game.module;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 上午9:52:56
 */
public class PacketId {

	/** 心跳包 **/
	public static final short BEAT = 1;
	/** 移除内存 **/
	public static final short REMOVE_CACHE = 2;

	/** 添加金币协议（测试） **/
	public static final short ADD_GOLD = 11;
	/** 添加金钱协议（测试） **/
	public static final short ADD_COPPER = 12;
	
	

	/** 登陆协议 **/
	public static final short LOGIN = 1001;
	/** 创建角色 **/
	public static final short CREATE_PLAYER = 2002;
	
	
	/** update bag capacity **/
	public static final short BAG_LIST = 4001;
	public static final short UPDATE_BAG = 4002;
	public static final short SELL_BAG_ITEM = 4003;


	public static final short PLANT = 5001;
	public static final short CLEAN = 5002;
	public static final short REWARD = 5003;
}
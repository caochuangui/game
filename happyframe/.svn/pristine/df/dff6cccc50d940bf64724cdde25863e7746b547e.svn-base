package com.game.module.player.model;

import com.frame.async.AsynOperation;
import com.frame.bean.BeanManager;
import com.game.commom.InstanceMsg;
import com.game.module.player.dao.PlayerDao;
import com.game.module.player.service.PlayerService;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 下午5:14:37
 */
public class Player extends AsynOperation implements InstanceMsg{
	private int playerId; // 玩家Id，唯一标识
	private String name = ""; // 玩家姓名
	private int level = 1;
	private short viplevel = 0; // vip等级
	private int copper = 0; // 钱币
	private int gold = 0; // 金币
	private int exp = 0; // 当前经验
	private int bagMaxCount = 20;


	public Player(int playerId) {
		this.playerId = playerId;
	}

	public Player() {

	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	/**
	 * @param playerId
	 *            the playerId to set
	 */
	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	/**
	 * @return the viplevel
	 */
	public short getViplevel() {
		return viplevel;
	}

	/**
	 * @param viplevel
	 *            the viplevel to set
	 */
	public void setViplevel(short viplevel) {
		this.viplevel = viplevel;
	}

	/**
	 * @return the gold
	 */
	public int getGold() {
		return gold;
	}

	/**
	 * @param gold
	 *            the gold to set
	 */
	public void setGold(int gold) {
		this.gold = gold;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}



	/**
	 * @return the exp
	 */
	public int getExp() {
		return exp;
	}

	/**
	 * @param exp
	 *            the exp to set
	 */
	public void setExp(int exp) {
		this.exp = exp;
	}

	

	public int getCopper() {
		return copper;
	}

	public void setCopper(int copper) {
		this.copper = copper;
	}

	/**
	 * @return the level
	 */
	public int getLevel() {
		return level;
	}

	/**
	 * @param level
	 *            the level to set
	 */
	public void setLevel(int level) {
		this.level = level;
	}
	
	
	public int getBagMaxCount() {
		return bagMaxCount;
	}
	
	public void setBagMaxCount(int count) {
		bagMaxCount = count;
	}
	

	/**
	 * 
	 * @see frame.async.AsynObject#save2DB()
	 */
	@Override
	public void save2DB() {
		PlayerDao bean = BeanManager.getInstance().getBean(PlayerDao.class);
		bean.updatePlayer(this);
	}

	/**
	 * 
	 * @see com.gzwabao.frame.async.AsynOperation#getId()
	 */
	@Override
	public Integer getId() {
		return playerId;
	}

	@Override
	public String toMsg() {
		String msg = level + " " + viplevel + " " +  copper + " "
				+ gold + " " + bagMaxCount + " " + exp;
		return msg;
	}
}

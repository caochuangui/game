package com.game.module.bag.model;

import com.frame.async.AsynOperation;
import com.frame.bean.BeanManager;
import com.game.commom.InstanceMsg;
import com.game.module.bag.dao.BagDao;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 下午5:14:37
 */
public class BagItem extends AsynOperation implements InstanceMsg{
	
	private int uid;
	private int playerId; // 玩家Id，唯一标识
	private int itemId;
	private int count;


	public BagItem(int uid) {
		this.uid = uid;
	}

	public BagItem() {

	}
	
	public int getUid() {
		return uid;
	}
	
	public void setUid(int uid) {
		this.uid = uid;
	}
	
	public int getItemId() {
		return itemId;
	}
	
	public void setItemId(int itemId) {
		this.itemId = itemId;
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
	
	public int getCount() {
		return count;
	}
	
	public void setCount(int count) {
		this.count = count;
	}

	/**
	 * 
	 * @see frame.async.AsynObject#save2DB()
	 */
	@Override
	public void save2DB() {
		BagDao bean = BeanManager.getInstance().getBean(BagDao.class);
		bean.updateBagItem(this);
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
		String msg = uid + "|" + itemId + "|" + count;
		return msg;
	}
}

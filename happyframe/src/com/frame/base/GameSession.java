package com.frame.base;

import org.jboss.netty.channel.Channel;


import com.game.module.player.model.Player;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 下午5:13:21
 */
public class GameSession {

	private Channel channel;
	private Player player;
	private int uid;

	/**
	 * @return the channel
	 */
	public Channel getChannel() {
		return channel;
	}

	/**
	 * @param channel
	 *            the channel to set
	 */
	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	/**
	 * @return the player
	 */
	public Player getPlayer() {
		return player;
	}

	/**
	 * @param player
	 *            the player to set
	 */
	public void setPlayer(Player player) {
		this.player = player;
	}

	/**
	 * @return the uid
	 */
	public int getUid() {
		return uid;
	}

	/**
	 * @param uid
	 *            the uid to set
	 */
	public void setUid(int uid) {
		this.uid = uid;
	}

}

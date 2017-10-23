package com.frame.packet;

import java.util.HashMap;
import java.util.Map;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 上午10:40:08
 */
public class ReqPacket {
	private int playerId;
	private short packetId;
	private String mess;
	/**
	 * @return the packetId
	 */
	public short getPacketId() {
		return packetId;
	}

	/**
	 * @param packetId
	 *            the packetId to set
	 */
	public void setPacketId(short packetId) {
		this.packetId = packetId;
	}

	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}

}

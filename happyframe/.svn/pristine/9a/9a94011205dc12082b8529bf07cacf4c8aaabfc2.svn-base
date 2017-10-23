package com.frame.extention;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

import org.jboss.netty.channel.Channel;

import com.frame.packet.ReqPacket;

/**
 * @author liuzhengyi
 * @date 2014年11月18日 下午1:52:57
 */
public abstract class AbstractEventJob {
	private ReqPacket reqPacket;
	private int playerId;
	private Channel channel;
	private AtomicBoolean atomicBoolean;
	private String msgId;

	private String mess;
	
	/**
	 * @return the reqPacket
	 */
	public ReqPacket getReqPacket() {
		return reqPacket;
	}

	/**
	 * @param reqPacket
	 *            the reqPacket to set
	 */
	public void setReqPacket(ReqPacket reqPacket) {
		mess = reqPacket.getMess();
		this.reqPacket = reqPacket;
	}

	/**
	 * @return the playerId
	 */
	public int getPlayerId() {
		return playerId;
	}

	public void setPlayerId(int playerId) {
		this.playerId = playerId;
	}

	public Channel getChannel() {
		return channel;
	}

	public void setChannel(Channel channel) {
		this.channel = channel;
	}

	public AtomicBoolean getAtomicBoolean() {
		return atomicBoolean;
	}

	public void setAtomicBoolean(AtomicBoolean atomicBoolean) {
		this.atomicBoolean = atomicBoolean;
	}

	public String getMess() {
		return mess;
	}

	public void setMess(String mess) {
		this.mess = mess;
	}
	
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}
	
	public String getMsgId() {
		return msgId;
	}

	public abstract Object excete();

}

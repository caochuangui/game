package com.client.net.sendPack.player;

import java.util.HashMap;
import java.util.Map;

import org.jboss.netty.channel.Channel;

import com.frame.packet.ReqPacket;
import com.game.module.PacketId;

/**
 * @author liuzhengyi
 * @date 2014年11月20日 上午11:51:06
 */
public class PlayerEventJob {

	public static void addGold(Channel channel) {
		ReqPacket reqPacket = new ReqPacket();
		reqPacket.setPacketId(PacketId.ADD_GOLD);
		StringBuffer sb = new StringBuffer();
		sb.append(100);
		reqPacket.setMess(sb.toString());;
		channel.write(reqPacket);
	}

	public static void createPlayer(Channel channel) {
		ReqPacket reqPacket = new ReqPacket();
		reqPacket.setPacketId(PacketId.CREATE_PLAYER);
		StringBuffer sb = new StringBuffer();
		sb.append("正义");
		reqPacket.setMess(sb.toString());;
		channel.write(reqPacket);
	}

}

package com.client.net;

import java.util.HashMap;
import java.util.Map;

import com.frame.packet.ReqPacket;
import com.game.module.PacketId;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 下午3:34:11
 */
public class Client {
	public static void main(String[] args) {
		ClientSocket clientSocket = new ClientSocket();
		clientSocket.connect("127.0.0.1", 8001);
		ReqPacket reqPacket = new ReqPacket();
		reqPacket.setPacketId(PacketId.LOGIN);
		StringBuffer sb = new StringBuffer();
		sb.append("zhengyi|123456");
		reqPacket.setMess(sb.toString());;
		ClientSocket.channel.write(reqPacket);

	}
}

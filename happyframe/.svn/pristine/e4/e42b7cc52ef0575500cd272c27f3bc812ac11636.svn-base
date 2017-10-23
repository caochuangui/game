package com.frame.net;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.List;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.OnlinePlayerManager;
import com.frame.packet.RespPacket;
import com.game.module.MsgId;
import com.game.module.PacketId;


/**
 * @author liuzhengyi
 * @date 2014年11月24日 下午2:53:47
 */
public class SendUtil {

	private static final Logger log = LoggerFactory.getLogger(SendUtil.class);

	private static RespPacket beat = new RespPacket();
	static {
		beat.setPacketId(PacketId.BEAT);
	}

	public static ChannelFuture write2Client(ChannelHandlerContext ctx,
			RespPacket resp) {
		if (resp != null) {
			try {
				byte[] bytes = resp.getMess().getBytes();
				ChannelBuffer buffer  = ChannelBuffers.dynamicBuffer(bytes.length + 2);
				buffer.writeShort(resp.getPacketId());
				buffer.writeBytes(bytes);
				Channel channel = ctx.getChannel();
				if (channel.isOpen() && channel.isWritable()) {
					return ctx.getChannel().write(buffer);
				}
				return null;
			} catch (Exception e) {
				log.error("", e);
			}
		}
		return null;
	}

	public static void sendAllOnlinePlayer(RespPacket resp) {
		List<Integer> allOnlinePlayerId = OnlinePlayerManager.getIntance()
				.getAllOnlinePlayerId();
		sendRespByPlayerId(resp, allOnlinePlayerId);
	}

	public static void sendRespByPlayerId(RespPacket resp, List<Integer> list) {
		for (int playerId : list) {
			sendRespByPlayerId(resp, playerId);
		}

	}

	public static void sendRespByPlayerId(RespPacket resp, int playerId) {
		try {
			Channel channel = OnlinePlayerManager.getIntance()
					.getChannelByPlayerId(playerId);
			byte[] bytes = resp.getMess().getBytes();
			ChannelBuffer buffer  = ChannelBuffers.dynamicBuffer(bytes.length + 2);
			buffer.writeShort(resp.getPacketId());
			buffer.writeBytes(bytes);
			if (channel.isOpen() && channel.isWritable()) {
				channel.write(buffer);
			}
		} catch (Exception e) {
			log.error("", e);
		}
	}

	public static void hearbeat(Channel channel) throws Exception {
		channel.write(MsgId.BEAT);
	}
}

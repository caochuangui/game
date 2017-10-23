package com.frame.net.netty;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.OnlinePlayerManager;
import com.frame.packet.ReqPacket;

public class GameDecoder extends FrameDecoder {

	private static Logger log = LoggerFactory.getLogger(GameDecoder.class);

	private static final int PACKET_HEAD = 4;

	@Override
	protected Object decode(ChannelHandlerContext cxt, Channel channel,
			ChannelBuffer buffer) throws Exception {
		if (buffer.readableBytes() < PACKET_HEAD) {
			return null;
		}

		int bodySize = buffer.readInt();

		if (buffer.readableBytes() < bodySize) {
			return null;
		}

		try {
			ReqPacket reqPacket = new ReqPacket();
			short packetId = buffer.readShort();
			byte[] body = new byte[bodySize - 2];
			buffer.readBytes(body);
			String mess = new String(body);
			reqPacket.setPacketId(packetId);
			reqPacket.setMess(mess);
			System.out.println(channel.getRemoteAddress().toString());
			return reqPacket;
		} catch (Exception e) {
			log.error("the bodySize is :" + bodySize + ",channel :"
					+ channel.getRemoteAddress().toString());
			log.error("", e);
			throw new Exception("GameDecoder : creat body is error");
		}

	}

	/**
	 * 
	 * @see org.jboss.netty.handler.codec.frame.FrameDecoder#channelClosed(org.jboss.netty.channel.ChannelHandlerContext,
	 *      org.jboss.netty.channel.ChannelStateEvent)
	 */
	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelClosed(ctx, e);
		try {
			// OnlinePlayerManager.getIntance().remove(ctx.getChannel());
			OnlinePlayerManager.getIntance().addRemoveEventJob(ctx);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}

	/**
	 * 
	 * @see org.jboss.netty.channel.SimpleChannelUpstreamHandler#channelConnected(org.jboss.netty.channel.ChannelHandlerContext,
	 *      org.jboss.netty.channel.ChannelStateEvent)
	 */
	@Override
	public void channelConnected(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelConnected(ctx, e);
		try {
			OnlinePlayerManager.getIntance().addGameSession(ctx.getChannel());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
	}
}

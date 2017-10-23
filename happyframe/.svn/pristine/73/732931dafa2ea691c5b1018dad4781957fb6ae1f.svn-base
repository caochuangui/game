package com.client.net;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.handler.codec.frame.FrameDecoder;

import com.frame.packet.RespPacket;

public class ClientGameDecoder extends FrameDecoder {

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
		short packetId = buffer.readShort();
		byte[] body = new byte[bodySize - 2];
		buffer.readBytes(body);
		String mess = new String(body);
		RespPacket respPacket = new RespPacket();
		respPacket.setMess(mess);
		respPacket.setPacketId(packetId);
		return respPacket;
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
	}
}

package com.client.net;

import java.io.ByteArrayOutputStream;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.frame.packet.ReqPacket;


public class ClientGameEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object obj) throws Exception {
		if (obj instanceof ReqPacket) {
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			ReqPacket reqPacket = (ReqPacket)obj;
			byte[] byteArray = reqPacket.getMess().getBytes();

			int count = byteArray.length + 2;
			buffer.writeInt(count);
			buffer.writeShort(reqPacket.getPacketId());
			buffer.writeBytes(byteArray);
			return buffer;
		}
		return null;
	}

}

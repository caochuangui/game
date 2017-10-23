package com.frame.net.netty;

import java.nio.charset.Charset;

import org.jboss.netty.buffer.ChannelBuffer;
import org.jboss.netty.buffer.ChannelBuffers;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.codec.oneone.OneToOneEncoder;

import com.frame.packet.RespPacket;


public class GameEncoder extends OneToOneEncoder {

	@Override
	protected Object encode(ChannelHandlerContext ctx, Channel channel,
			Object obj) throws Exception {
		if (obj instanceof RespPacket) {
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			RespPacket respPacket = (RespPacket) obj;

			byte[] byteArray = respPacket.getMess().getBytes();

			int count = byteArray.length + 2;
			buffer.writeInt(count);
			buffer.writeShort(respPacket.getPacketId());
			buffer.writeBytes(byteArray);
			return buffer;
		}
		else if ((obj instanceof String) || (obj instanceof StringBuffer)) {
			String outInfo = obj.toString();
			if (!outInfo.endsWith("\n")) {
				outInfo = outInfo + "\n";
			}
			ChannelBuffer buffer = ChannelBuffers.dynamicBuffer();
			buffer.writeBytes(outInfo.getBytes(Charset.forName("UTF-8")));
//			buffer.writeBytes(outInfo.getBytes(Charset.forName("GBK")));
			System.out.println("CMD outbuffer: " + obj);
			return buffer;
		}
		System.err.println("CMD output error : " + obj + " " + obj.getClass());
		return null;
	}

}

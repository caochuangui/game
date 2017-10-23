package com.client.net;

import java.io.ByteArrayInputStream;
import java.util.Map.Entry;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ChannelStateEvent;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.client.net.sendPack.player.PlayerEventJob;
import com.frame.packet.ReqPacket;
import com.frame.packet.RespPacket;
import com.game.module.PacketId;
import com.game.module.player.model.Player;


public class ClientGameHandler extends SimpleChannelUpstreamHandler {

	@Override
	public void channelClosed(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		// TODO
		super.channelClosed(ctx, e);

	}

	@Override
	public void channelOpen(ChannelHandlerContext ctx, ChannelStateEvent e)
			throws Exception {
		super.channelOpen(ctx, e);
	}

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		RespPacket respPacket = (RespPacket) e.getMessage();
		if (respPacket.getMess() != null
				&& respPacket.getPacketId() == PacketId.LOGIN) {
			System.out.println(respPacket.getMess());
			String[] split = respPacket.getMess().split(";");
			ReqPacket reqPacket = new ReqPacket();
			if(Integer.valueOf(split[0]) == RespType.CREATE_PLAYER){
				reqPacket.setPacketId(PacketId.CREATE_PLAYER);
				reqPacket.setMess("正义");
			}else{
				reqPacket.setPacketId(PacketId.ADD_GOLD);
				reqPacket.setMess("100");
			}
			ctx.getChannel().write(reqPacket);
			
		} else if(respPacket.getMess() != null
				&& respPacket.getPacketId() == PacketId.CREATE_PLAYER){
			System.out.println(respPacket.getMess());
			ReqPacket reqPacket = new ReqPacket();
			reqPacket.setPacketId(PacketId.ADD_GOLD);
			reqPacket.setMess("100");
			ctx.getChannel().write(reqPacket);
			
		}else if(respPacket.getMess() != null
				&& respPacket.getPacketId() == PacketId.ADD_GOLD){
			System.out.println("ADD_GOLD:" + respPacket.getMess());
		}else{
			System.out.println("packId is :" + respPacket.getPacketId() + ";msg is " + respPacket.getMess());
		}
	}

}

package com.frame.net.netty;

import java.io.ByteArrayInputStream;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.MessageEvent;
import org.jboss.netty.channel.SimpleChannelUpstreamHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.frame.OnlinePlayerManager;
import com.frame.base.GameSession;
import com.frame.extention.AbstractEventJob;
import com.frame.extention.EventJobFactory;
import com.frame.extention.EventJobManager;
import com.frame.packet.ReqPacket;
import com.game.module.PacketId;
import com.game.module.login.service.LoginService;


public class GameHandler extends SimpleChannelUpstreamHandler {
	private static final Logger log = LoggerFactory
			.getLogger(GameHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		ReqPacket reqPacket = (ReqPacket) e.getMessage();
		Channel channel = ctx.getChannel();
		AbstractEventJob eventJob = EventJobFactory.createExtention(reqPacket,
				channel);

		// 移除退出倒计时
		OnlinePlayerManager.getIntance().clearBeatCount(channel);
		if (reqPacket.getPacketId() == PacketId.LOGIN) {
			LoginService.getIntance().addExtention(eventJob);
		} else if (reqPacket.getPacketId() == PacketId.BEAT) {
		} else {
			GameSession gameSsionByChannel = OnlinePlayerManager.getIntance()
					.getGameSsionByChannel(channel);
			if (gameSsionByChannel == null
					|| gameSsionByChannel.getPlayer() == null
					|| ((gameSsionByChannel.getPlayer().getName() == null || ""
							.equals(gameSsionByChannel.getPlayer().getName())
							&& reqPacket.getPacketId() != PacketId.CREATE_PLAYER))) {
				channel.close();
				return;
			}
			log.info("Uid :" + gameSsionByChannel.getUid() + ";packetId:"
					+ eventJob.getReqPacket().getPacketId());
			eventJob.setPlayerId(gameSsionByChannel.getUid());
			EventJobManager.getInstance().addEventJob(eventJob);
		}
	}

}

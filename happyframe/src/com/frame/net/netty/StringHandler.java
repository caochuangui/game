package com.frame.net.netty;

import java.io.ByteArrayInputStream;

import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.channel.ExceptionEvent;
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
import com.game.module.MsgId;
import com.game.module.PacketId;
import com.game.module.login.service.LoginService;


public class StringHandler extends SimpleChannelUpstreamHandler {
	private static final Logger log = LoggerFactory
			.getLogger(StringHandler.class);

	@Override
	public void messageReceived(ChannelHandlerContext ctx, MessageEvent e)
			throws Exception {
		String msg = (String) e.getMessage();
		System.out.println("messageReceived: " + msg);
		if (msg == null || "".equals(msg)) {
			return;
		}
		msg = msg.trim();
        int spIdx = msg.indexOf(' ');
        String msgId = (spIdx < 0) ? msg : msg.substring(0, spIdx);

		Channel channel = ctx.getChannel();
		AbstractEventJob eventJob = EventJobFactory.createExtention(msg,
				channel);
		if (eventJob == null && !MsgId.BEAT.equals(msg)
				&& !MsgId.LOGOUT.equals(msg)) {
			System.err.println("Unkown cmd: " + msg);
			channel.write(msgId + " -5 Unkown Command\n");
			return;
		}

		System.out.println("msg: " + msg + " channel state:" + channel.isConnected());
		// 移除退出倒计时
		OnlinePlayerManager.getIntance().clearBeatCount(channel);
		if (MsgId.LOGIN.equals(msgId) || MsgId.REGISTER.equals(msgId)) {
			LoginService.getIntance().addExtention(eventJob);
		} else if (MsgId.BEAT.equals(msgId)) {
		} else if (MsgId.LOGOUT.equals(msgId)) {
			channel.write(MsgId.LOGOUT + " 0");
			channel.close();
			return;
		} else {
			GameSession gameSsionByChannel = OnlinePlayerManager.getIntance()
					.getGameSsionByChannel(channel);
			if (gameSsionByChannel == null
					|| gameSsionByChannel.getPlayer() == null
					|| ((gameSsionByChannel.getPlayer().getName() == null || ""
							.equals(gameSsionByChannel.getPlayer().getName())
							&& !MsgId.CREATE_PLAYER.equals(msgId)))) {
				System.err.println("MessageReceive: channel closed");
				channel.close();
				return;
			}
			log.info("Uid: " + gameSsionByChannel.getUid() + " msg: " + msg);
//			eventJob.setPlayerId(gameSsionByChannel.getUid());
			EventJobManager.getInstance().addEventJob(eventJob);
		}
	}

	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, ExceptionEvent e)
			throws Exception {
		// TODO Auto-generated method stub
		super.exceptionCaught(ctx, e);
	}
	
	

}

package com.frame.net.netty;

import org.jboss.netty.channel.ChannelHandlerContext;
import org.jboss.netty.handler.timeout.IdleState;
import org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler;
import org.jboss.netty.handler.timeout.IdleStateEvent;

import com.frame.OnlinePlayerManager;
import com.frame.net.SendUtil;

/**
 * @author liuzhengyi
 * @date 2014年11月24日 上午10:18:44
 */
public class Heartbeat extends IdleStateAwareChannelHandler {

	/**
	 * 
	 * @see org.jboss.netty.handler.timeout.IdleStateAwareChannelHandler#channelIdle(org.jboss.netty.channel.ChannelHandlerContext,
	 *      org.jboss.netty.handler.timeout.IdleStateEvent)
	 */
	@Override
	public void channelIdle(ChannelHandlerContext ctx, IdleStateEvent e)
			throws Exception {
		// 一个心跳周期内没接收到任何的信息就发送一次心跳包
		if (e.getState() == IdleState.READER_IDLE) {
			CheckBeatType type = OnlinePlayerManager.getIntance()
					.increaseBeatCount(ctx.getChannel());
			switch (type) {

			case UN_LOGIN:

				break;
			case INCREAT_COUNT:
				System.out.println("send beat pack");
				SendUtil.hearbeat(ctx.getChannel());
				break;
			case CAN_ADD_REMOVEJOB:
				OnlinePlayerManager.getIntance().addRemoveEventJob(ctx);
				break;
			case WAIT_REMOVEJOB:

				break;
			default:
				break;
			}

		}
	}
}

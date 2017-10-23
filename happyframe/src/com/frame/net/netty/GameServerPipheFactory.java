package com.frame.net.netty;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;
import org.jboss.netty.handler.timeout.IdleStateHandler;
import org.jboss.netty.util.HashedWheelTimer;

public class GameServerPipheFactory implements ChannelPipelineFactory {

	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
//		pipeline.addLast("decoder", new GameDecoder());
//		pipeline.addLast("encoder", new GameEncoder());
//		pipeline.addLast("handler", new GameHandler());

		pipeline.addLast("decoder", new StringDecoder());
		pipeline.addLast("encoder", new GameEncoder());
		pipeline.addLast("handler", new StringHandler());

		// 心跳
		pipeline.addLast("timeout", new IdleStateHandler(
				new HashedWheelTimer(), 10, 10, 0));
		pipeline.addLast("hearbeat", new Heartbeat());
		return pipeline;
	}

}

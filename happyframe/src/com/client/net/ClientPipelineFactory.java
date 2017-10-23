package com.client.net;

import static org.jboss.netty.channel.Channels.pipeline;

import org.jboss.netty.channel.ChannelPipeline;
import org.jboss.netty.channel.ChannelPipelineFactory;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 下午2:43:16
 */
public class ClientPipelineFactory implements ChannelPipelineFactory {

	/**
	 * 
	 * @see org.jboss.netty.channel.ChannelPipelineFactory#getPipeline()
	 */
	@Override
	public ChannelPipeline getPipeline() throws Exception {
		ChannelPipeline pipeline = pipeline();
		pipeline.addLast("decoder", new ClientGameDecoder());
		pipeline.addLast("encoder", new ClientGameEncoder());
		pipeline.addLast("handler", new ClientGameHandler());
		return pipeline;
	}

}

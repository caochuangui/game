package com.client.net;

import java.net.InetSocketAddress;
import java.util.concurrent.Executors;

import org.jboss.netty.bootstrap.ClientBootstrap;
import org.jboss.netty.channel.Channel;
import org.jboss.netty.channel.ChannelFuture;
import org.jboss.netty.channel.socket.nio.NioClientSocketChannelFactory;

/**
 * @author liuzhengyi
 * @date 2014年11月19日 下午2:40:05
 */
public class ClientSocket {
	public static Channel channel;
	public static ChannelFuture future;

	static ClientBootstrap bootstrap = new ClientBootstrap(
			new NioClientSocketChannelFactory(Executors.newCachedThreadPool(),
					Executors.newCachedThreadPool()));

	public void connect(String host, int port) {
		// 用户自定义的pipeline工厂
		bootstrap.setPipelineFactory(new ClientPipelineFactory());

		// 异步创建连接

		future = bootstrap.connect(new InetSocketAddress(host, port));
		channel = future.awaitUninterruptibly().getChannel();
	}

}

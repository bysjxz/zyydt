package com.cdutcm.tcms.socket;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class EacClient {

	public void connect(String host, int port) throws Exception {
		EventLoopGroup workerGroup = new NioEventLoopGroup();

		try {
			Bootstrap b = new Bootstrap();
			b.group(workerGroup);
			b.channel(NioSocketChannel.class);
			b.option(ChannelOption.SO_KEEPALIVE, true);
			b.handler(new ChannelInitializer<SocketChannel>() {
				@Override
				public void initChannel(SocketChannel ch) throws Exception {
					ch.pipeline().addLast(new EacClientHandler());
				}
			});
			// Start the client.
			ChannelFuture f = b.connect(host, port).sync();
			// Wait until the connection is closed.
			f.channel().closeFuture().sync();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			workerGroup.shutdownGracefully();
		}

	}

	public static void main(String[] args) throws Exception {
		//1010101010101010101010101010101010101010123421
		EacClient client = new EacClient();
		client.connect("192.168.43.198", 8000);
//		client.connect("192.168.112.1", 8000);
	}
}

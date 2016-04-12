package com.gmsz.om.scoket.udp;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.Unpooled;
import io.netty.channel.Channel;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.DatagramPacket;
import io.netty.channel.socket.nio.NioDatagramChannel;

import java.net.InetSocketAddress;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.gmsz.om.common.beans.UdpMsgBean;
import com.gmsz.om.common.constant.StateDefine;

@Component
public class UdpClient {
	
	private static final Logger sysLogger = Logger.getLogger(StateDefine.SYS_LOG);
	
	 public  void writeAndFlush(final UdpMsgBean udpMsg) throws Exception {
	    	new Thread() {
				public void run() {
			    	EventLoopGroup group = new NioEventLoopGroup();
			    	try{
			    		Bootstrap b = new Bootstrap();
			    		b.group(group)
			    		.channel(NioDatagramChannel.class)
			    		.option(ChannelOption.SO_BROADCAST, true)
			    		.handler(new UdpClientHandler());
			    		Channel ch;
						try {
							ch = b.bind(0).sync().channel();
							ch.writeAndFlush(
									new DatagramPacket(
										Unpooled.copiedBuffer(udpMsg.getUdpMsgBytes()),
										new InetSocketAddress(udpMsg.getServerIp(), udpMsg.getServerPort())
									)
								).sync();
							//  If the channel is not closed within 50 seconds,print an error message and quit.
							if (!ch.closeFuture().await(1200000)) {
				    			sysLogger.error("QOTM request timed out.");
				    		}
						} catch (InterruptedException e) {
							sysLogger.error(e);
						}
			    	}finally{
			    		group.shutdownGracefully();
			    	}
				}
			}.start();
	    }
	 
	 
	 public static void main(String[] args) throws Exception {
		UdpClient client = new UdpClient();
		UdpMsgBean udpMsg = new UdpMsgBean();
		udpMsg.setServerIp("192.168.18.141");
		udpMsg.setServerPort(1858);
		String alarmStr = "P1 15/10/21 14:23 BURGLARY       020";
		byte[] send = alarmStr.getBytes();
		udpMsg.setUdpMsgBytes(send);
		client.writeAndFlush(udpMsg);
	}
	    
	
}

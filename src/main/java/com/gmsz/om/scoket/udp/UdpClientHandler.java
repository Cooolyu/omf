package com.gmsz.om.scoket.udp;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.socket.DatagramPacket;

public class UdpClientHandler extends SimpleChannelInboundHandler<DatagramPacket>{

	@Override
	protected void channelRead0(ChannelHandlerContext ctx, DatagramPacket msg)
			throws Exception {
        ctx.close();
		
	}
	
	public boolean invoke(DatagramPacket msg) {
    	return true;
    }

}

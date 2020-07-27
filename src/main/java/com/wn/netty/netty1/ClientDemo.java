package com.wn.netty.netty1;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientDemo {
    public static void main(String[] args) throws InterruptedException {
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(new NioEventLoopGroup());
        bootstrap.channel(NioSocketChannel.class).handler(new ChannelInboundHandlerAdapter() {
            @Override
            public void channelActive(ChannelHandlerContext ctx) throws Exception {
                ByteBuf buffer = PooledByteBufAllocator.DEFAULT.buffer(8);//代码一
                buffer.writeInt(1).writeInt(2).writeInt(3).writeInt(4);
                ctx.writeAndFlush(buffer);
            }

            @Override
            public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
                System.out.println("数据发送");
            }

            @Override
            public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                ByteBuf buf = (ByteBuf) msg;
                System.out.println(buf.readInt());
                System.out.println(buf.readInt());
                System.out.println(buf.readInt());
                System.out.println(buf.readInt());
                ctx.close();
                System.out.println("通道关闭");
            }
        });

        ChannelFuture sync = bootstrap.connect("127.0.0.1", 2323).sync();
    }
}

package com.wn.netty.netty1;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

/**
 * 客户端消息定长为 16 字节
 * TCP 不发生拆包粘包
 * 客户端一次只发送一条消息
 */
public class MainDemo {
    public static void main(String[] args) {
        EventLoopGroup boss = new NioEventLoopGroup();
        EventLoopGroup worker = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boss, worker)//代码一：设置整个服务端执行所使用的线程池
                    .channel(NioServerSocketChannel.class)//代码二：设置服务端使用的通道类型，作为服务端程序，必然选择ServerSocketChannel下面的各种实现类。如果是基于JDK的NIO的话，则是选择NioServerSocketChannel
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        //代码三：该方法在客户端链接成功建立时被触发，方法入参就是新建立的客户端通道对象

                        @Override
                        protected void initChannel(SocketChannel ch) throws Exception {
                            //代码四：获取socket通道的管道对象，并且向其中添加处理器
                            ch.pipeline().addLast(new ChannelInboundHandlerAdapter() {
                                @Override
                                public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
                                    ByteBuf buf = (ByteBuf) msg;
                                    if (buf.readerIndex() != 0) {
                                        throw new IllegalStateException();
                                    }
                                    if (buf.writerIndex() != 16) {
                                        throw new IllegalStateException();
                                    }
                                    ctx.writeAndFlush(buf);
                                }

                                @Override
                                public void channelInactive(ChannelHandlerContext ctx) throws Exception {
                                    //客户端关闭通道后，收到消息
                                    System.out.println("客户端关闭通道");
                                }
                            });
                        }
                    });

            ChannelFuture sync = serverBootstrap.bind(2323).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boss.shutdownGracefully();
            worker.shutdownGracefully();
        }
    }
}

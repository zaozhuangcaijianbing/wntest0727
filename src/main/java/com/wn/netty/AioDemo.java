package com.wn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

//aio没有被广泛使用，netty还是使用的nio
public class AioDemo {
    public static void main(String[] args) throws IOException {
        new AioDemo().startServer();
    }

    public void startServer() throws IOException {
        final AsynchronousServerSocketChannel server = AsynchronousServerSocketChannel.open();
        server.bind(new InetSocketAddress(8080));
        server.accept(server, new AcceptHandler());
    }

    class AcceptHandler implements CompletionHandler<AsynchronousSocketChannel, AsynchronousServerSocketChannel> {

        /**
         * 新创建的链接对象作为入参result被传入
         *
         * @param result
         * @param server
         */
        @Override
        public void completed(AsynchronousSocketChannel result, AsynchronousServerSocketChannel server) {
            //先忽略对新连接的处理相关代码
            //继续等待下一个接入的链接
            ByteBuffer buffer = ByteBuffer.allocate(16);
            result.read(buffer, buffer, new ClientReadHandler(result));
            server.accept(server, this);
        }

        @Override
        public void failed(Throwable exc, AsynchronousServerSocketChannel server) {
        }
    }

    class ClientReadHandler implements CompletionHandler<Integer, ByteBuffer> {
        AsynchronousSocketChannel socketChannel;

        public ClientReadHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer buffer) {
            if (result == 16) {
                //数据读取完毕，准备写出
                socketChannel.write(buffer, buffer, new WriteHandler(socketChannel));
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
        }
    }

    class WriteHandler implements CompletionHandler<Integer, ByteBuffer> {
        AsynchronousSocketChannel socketChannel;

        public WriteHandler(AsynchronousSocketChannel socketChannel) {
            this.socketChannel = socketChannel;
        }

        @Override
        public void completed(Integer result, ByteBuffer attachment) {
            //如果一次没有全部写完，继续写
            if (attachment.hasRemaining()) {
                socketChannel.write(attachment, attachment, this);
            } else {
                try {
                    socketChannel.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        @Override
        public void failed(Throwable exc, ByteBuffer attachment) {
        }
    }
}

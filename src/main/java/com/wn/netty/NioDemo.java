package com.wn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public class NioDemo {
    public static void main(String[] args) throws IOException {
        NioDemo nioDemo = new NioDemo();
        nioDemo.start();
    }


    public void start() throws IOException {
        Selector selector = Selector.open();
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.socket().bind(new InetSocketAddress(8080), 150);
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        AtomicInteger id = new AtomicInteger();
        Selector[] selectors = initWorkerSelectors();
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                SocketChannel socketChannel = serverSocketChannel.accept();
                System.out.println("accept");
                //通过轮训，选择一个选择器进行注册，后续所有的操作也都在对应的线程上执行
                socketChannel.configureBlocking(false);
                socketChannel.register(selectors[id.getAndIncrement() % selectors.length], SelectionKey.OP_READ);
                iterator.remove();
            }
        }
    }

    public Selector[] initWorkerSelectors() throws IOException {
        final Selector[] selectors = new Selector[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < selectors.length; i++) {
            final Selector selector = Selector.open();
            selectors[i] = selector;
            new Thread(new Runnable() {
                @Override
                public void run() {
                    while (true) {
                        try {
                            processWithSelector(selector);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }).start();
        }
        return selectors;
    }

    private void processWithSelector(Selector selector) throws IOException {
        selector.select();
        Set<SelectionKey> selectionKeys = selector.selectedKeys();
        Iterator<SelectionKey> iterator = selectionKeys.iterator();
        while (iterator.hasNext()) {
            SelectionKey key = iterator.next();
            if (key.isValid() == false) {
                continue;
            }
            if (key.isReadable()) {
                ByteBuffer buffer = ByteBuffer.wrap(new byte[16]);
                SocketChannel clientChannel = (SocketChannel) key.channel();
                int read = clientChannel.read(buffer);
                if (read == -1)//关闭分支
                {
                    //通道连接关闭，可以取消这个注册键，后续不在触发。
                    key.cancel();
                    clientChannel.close();
                } else//读写分支
                {
                    System.out.println("read");
                    buffer.flip();
                    clientChannel.write(buffer);
                }
            }
            iterator.remove();
        }
    }
}

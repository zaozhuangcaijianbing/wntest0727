package com.wn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class MainDemo2 {
    static class ClientProcessor implements Runnable
    {
        private Selector selector;

        public ClientProcessor(Selector selector)
        {
            this.selector = selector;
        }

        @Override
        public void run()
        {
            while (true)
            {
                try
                {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (SelectionKey each : selectionKeys)
                    {
                        if (each.isValid() == false)
                        {
                            continue;
                        }
                        if (each.isReadable())
                        {
                            SocketChannel clientChannel = (SocketChannel) each.channel();
                            ByteBuffer    readBuffer    = (ByteBuffer) each.attachment();
                            int           read          = clientChannel.read(readBuffer);
                            if (read == -1)
                            {
                                //通道连接关闭，可以取消这个注册键，后续不在触发。
                                each.cancel();
                                clientChannel.close();
                            }
                            else
                            {
                                //翻转buffer，从写入状态切换到读取状态
                                readBuffer.flip();
                                int              position = readBuffer.position();
                                int              limit    = readBuffer.limit();
                                List<ByteBuffer> buffers  = new ArrayList<>();
                                //新增二：按照协议从流中分割出消息
                                /**从readBuffer确认每一个字节，发现分割符则切分出一个消息**/
                                for (int i = position; i < limit; i++)
                                {
                                    //读取到消息结束符
                                    if (readBuffer.get(i) == '\r')
                                    {
                                        ByteBuffer message = ByteBuffer.allocate(i - readBuffer.position()+1);
                                        readBuffer.limit(i+1);
                                        message.put(readBuffer);
                                        readBuffer.limit(limit);
                                        message.flip();
                                        buffers.add(message);
                                        readBuffer.limit(limit);
                                    }
                                }
                                /**从readBuffer确认每一个字节，发现分割符则切分出一个消息**/
                                /**将所有得到的消息发送出去**/
                                for (ByteBuffer buffer : buffers)
                                {
                                    //新增三
                                    while (buffer.hasRemaining())
                                    {
                                        clientChannel.write(buffer);
                                    }
                                }
                                /**将所有得到的消息发送出去**/
                                //新增四：压缩readBuffer，压缩完毕后进入写入状态。并且由于长度是256，压缩之后必然有足够的空间可以写入一条消息
                                readBuffer.compact();
                            }
                        }
                    }
                    selectionKeys.clear();
                }
                catch (IOException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException
    {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        final Selector[] selectors = new Selector[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < selectors.length; i++)
        {
            final Selector selector = Selector.open();
            selectors[i] = selector;
            new Thread(new ClientProcessor(selector)).start();
        }
        AtomicInteger id       = new AtomicInteger();
        Selector      selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true)
        {
            selector.select();
            Set<SelectionKey>      selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator      = selectionKeys.iterator();
            while (iterator.hasNext())
            {
                iterator.next();
                SocketChannel socketChannel  = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                Selector      selectForChild = selectors[id.getAndIncrement() % selectors.length];
                //新增一：每一个选择键或者说通道持有一个独占的buffer，用于数据的累计读取
                socketChannel.register(selectForChild, SelectionKey.OP_READ, ByteBuffer.allocate(128 * 2));
                selectForChild.wakeup();
                iterator.remove();
            }
        }
    }
}

package com.wn.netty;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

public class MainDemo3 {
    static class ChannelBuffer {
        ByteBuffer readBuffer;
        ByteBuffer[] writeBuffers;
        List<ByteBuffer> list = new LinkedList<>();
    }

    static class ClientProcessor implements Runnable {
        private Selector selector;

        public ClientProcessor(Selector selector) {
            this.selector = selector;
        }

        @Override
        public void run() {
            while (true) {
                try {
                    selector.select();
                    Set<SelectionKey> selectionKeys = selector.selectedKeys();
                    for (SelectionKey each : selectionKeys) {
                        if (each.isValid() == false) {
                            continue;
                        }
                        if (each.isReadable()) {
                            SocketChannel clientChannel = (SocketChannel) each.channel();
                            ChannelBuffer channelBuffer = (ChannelBuffer) each.attachment();
                            ByteBuffer readBuffer = channelBuffer.readBuffer;
                            int read = clientChannel.read(readBuffer);
                            if (read == -1) {
                                each.cancel();
                                clientChannel.close();
                                continue;
                            } else {
                                readBuffer.flip();
                                int position = readBuffer.position();
                                int limit = readBuffer.limit();
                                List<ByteBuffer> buffers = new ArrayList<>();
                                for (int i = position; i < limit; i++) {
                                    //读取到消息结束符
                                    if (readBuffer.get(i) == '\r') {
                                        ByteBuffer message = ByteBuffer.allocate(i - readBuffer.position() + 1);
                                        readBuffer.limit(i + 1);
                                        message.put(readBuffer);
                                        readBuffer.limit(limit);
                                        message.flip();
                                        buffers.add(message);
                                        readBuffer.limit(limit);
                                    }
                                }
                                //修改一：区分对象是否还有剩余数据未发送完成，以及在数据不可写入的时候关注通道可写事件等待下一次机会
                                if (channelBuffer.writeBuffers == null) {
                                    //没有还未发送完全的数据，那么本次需要发送的数据可以直接进行发送
                                    ByteBuffer[] byteBuffers = buffers.toArray(new ByteBuffer[buffers.size()]);
                                    clientChannel.write(byteBuffers);
                                    boolean hasRemaining = hasRemaining(byteBuffers);
                                    if (hasRemaining) {
                                        channelBuffer.writeBuffers = byteBuffers;
                                        each.interestOps(each.interestOps() | SelectionKey.OP_WRITE);
                                    }
                                } else {
                                    //还有尚未发送完全的数据，新产生的数据需要放入队列
                                    channelBuffer.list.addAll(buffers);
                                }
                                readBuffer.compact();
                            }
                        }
                        if (each.isWritable()) {
                            //新增二：新增在通道可写事件触发后，将之前剩余的数据写出
                            SocketChannel clientChannel = (SocketChannel) each.channel();
                            ChannelBuffer channelBuffer = (ChannelBuffer) each.attachment();
                            ByteBuffer[] writeBuffers = channelBuffer.writeBuffers;
                            clientChannel.write(writeBuffers);
                            boolean hasRemaining = hasRemaining(writeBuffers);
                            if (hasRemaining == false) {
                                channelBuffer.writeBuffers = null;
                                List<ByteBuffer> list = channelBuffer.list;
                                if (list.isEmpty() == false) {
                                    writeBuffers = list.toArray(new ByteBuffer[list.size()]);
                                    list.clear();
                                    clientChannel.write(writeBuffers);
                                    if (hasRemaining(writeBuffers)) {
                                        //仍然有数据没有完全写出，保留对可写事件的关注
                                    } else {
                                        //没有数据要写出了，取消对可写事件的关注
                                        each.interestOps(each.interestOps() & ~SelectionKey.OP_WRITE);
                                    }
                                } else {
                                    //没有数据要写出了，取消对可写事件的关注
                                    each.interestOps(each.interestOps() | ~SelectionKey.OP_WRITE);
                                }
                            } else {
                                //数据还没有写完，继续保持对可写事件的关注
                            }
                        }
                    }
                    selectionKeys.clear();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        private boolean hasRemaining(ByteBuffer[] byteBuffers) {
            boolean hasRemaining = false;
            for (ByteBuffer byteBuffer : byteBuffers) {
                if (byteBuffer.hasRemaining()) {
                    hasRemaining = true;
                    break;
                }
            }
            return hasRemaining;
        }
    }

    public static void main(String[] args) throws IOException, ExecutionException, InterruptedException {
        ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
        serverSocketChannel.configureBlocking(false);
        serverSocketChannel.bind(new InetSocketAddress(8899));
        final Selector[] selectors = new Selector[Runtime.getRuntime().availableProcessors()];
        for (int i = 0; i < selectors.length; i++) {
            final Selector selector = Selector.open();
            selectors[i] = selector;
            new Thread(new ClientProcessor(selector)).start();
        }
        AtomicInteger id = new AtomicInteger();
        Selector selector = Selector.open();
        serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
        while (true) {
            selector.select();
            Set<SelectionKey> selectionKeys = selector.selectedKeys();
            Iterator<SelectionKey> iterator = selectionKeys.iterator();
            while (iterator.hasNext()) {
                iterator.next();
                SocketChannel socketChannel = serverSocketChannel.accept();
                socketChannel.configureBlocking(false);
                Selector selectForChild = selectors[id.getAndIncrement() % selectors.length];
                //新增一：使用ChannelBuffer作为选择键的附件
                ChannelBuffer channelBuffer = new ChannelBuffer();
                channelBuffer.readBuffer = ByteBuffer.allocate(128 * 2);
                socketChannel.register(selectForChild, SelectionKey.OP_READ, channelBuffer);
                selectForChild.wakeup();
                iterator.remove();
            }
        }
    }
}

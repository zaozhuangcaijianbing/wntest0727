package com.wn.netty;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class BioDemo {
    public static void main(String[] args) throws IOException {
        BioDemo bioDemo = new BioDemo();
        bioDemo.processConnection();
    }

    ExecutorService executorService = Executors.newFixedThreadPool(10);

    public void processConnection() throws IOException {
        ServerSocket serverSocket = new ServerSocket(8080);
        Socket socket;
        while ((socket = serverSocket.accept()) != null) {
            System.out.println("client ip " + socket.getPort());
            final Socket finalSocket = socket;
            executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream inputStream = finalSocket.getInputStream();
                        byte[] data = new byte[16];
                        inputStream.read(data);
                        OutputStream outputStream = finalSocket.getOutputStream();
                        outputStream.write(data);
                        finalSocket.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            });
        }
    }



}

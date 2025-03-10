package org.example.JavaNetworkProgramming.server;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.List;

public class SimpleServerChannel {

    public static void main(String[] args) {

        try (ServerSocketChannel serverSocketChannel = ServerSocketChannel.open()) {

            serverSocketChannel.socket().bind(new InetSocketAddress(5000));
            serverSocketChannel.configureBlocking(false);
            System.out.println("Server is listening on port " +
                    serverSocketChannel.socket().getLocalPort());

            List<SocketChannel> clientChannels = new ArrayList<>();


            while (true) {
//                System.out.print("Waiting to connect to another client");
                SocketChannel clientChannel = serverSocketChannel.accept();

                if (clientChannel != null) {
                    clientChannel.configureBlocking(false);
                    clientChannels.add(clientChannel);
//                System.out.printf("%nClient is listening on port " +
//                        serverSocketChannel.socket().getLocalPort() + "%n");
                    System.out.printf("%nClient %s connected%n ",
                            clientChannel.socket().getRemoteSocketAddress());
                }

                ByteBuffer buffer = ByteBuffer.allocate(1024);
                for (int i = 0; i < clientChannels.size(); i++) {
                    SocketChannel channel = clientChannels.get(i);
//                    System.out.println("Waiting on client request data");
                    int readBytes = channel.read(buffer);

                    if (readBytes > 0) {
                        buffer.flip();
                        channel.write(ByteBuffer.wrap("Echo from server: ".getBytes()));
                        while (buffer.hasRemaining()) {
                            channel.write(buffer);
                        }
                        buffer.clear();
                    } else if (readBytes == -1) {
                        System.out.printf("Connection to %s lost%n",
                                channel.socket().getRemoteSocketAddress());
                        channel.close();
                        clientChannels.remove(i);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println("Server exception " + e.getMessage());
        }
    }
}

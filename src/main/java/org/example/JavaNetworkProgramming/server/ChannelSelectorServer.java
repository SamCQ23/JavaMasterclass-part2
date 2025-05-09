package org.example.JavaNetworkProgramming.server;

import org.glassfish.jaxb.core.util.Which;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class ChannelSelectorServer {

    public static void main(String[] args) {

        try (ServerSocketChannel serverSocketChannel =
                     ServerSocketChannel.open()) {

            serverSocketChannel.bind(new InetSocketAddress(5000));
            serverSocketChannel.configureBlocking(false);
            //for event driven channels want no blocking
            Selector selector = Selector.open();
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);

            while (true) {

                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();

                while (iterator.hasNext()) {
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if (key.isAcceptable()) {
                        SocketChannel clientChannel = serverSocketChannel.accept();
                        System.out.println("Client connected: " + clientChannel.getRemoteAddress());
                        clientChannel.configureBlocking(false);
                        clientChannel.register(selector, SelectionKey.OP_READ);

                    } else if (key.isReadable()) {
                        echoData(key);
                    }
                }
            }
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private static void echoData(SelectionKey key) throws IOException {

        SocketChannel clientChannel = (SocketChannel) key.channel();
        ByteBuffer buffer = ByteBuffer.allocate(1024);

        int bytesRead = clientChannel.read(buffer);
        if (bytesRead > 0) {
            buffer.flip();
            byte[] data = new byte[buffer.remaining()];
            buffer.get(data);
            String message = "Echo: " + new String(data);
            clientChannel.write(ByteBuffer.wrap(message.getBytes()));
        } else if (bytesRead == -1) {
            System.out.println("Client disconnected: " +
                    clientChannel.getRemoteAddress());
            key.cancel();
            clientChannel.close();
        }
    }
}

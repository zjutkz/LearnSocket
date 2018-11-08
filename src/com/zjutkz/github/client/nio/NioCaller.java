package com.zjutkz.github.client.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SocketChannel;

/**
 * Created by kangzhe on 18/11/7.
 */
public class NioCaller {

    private InetSocketAddress inetSocketAddress;

    public NioCaller() {
        inetSocketAddress = new InetSocketAddress("localhost", 8081);
    }

    /**
     * 发送请求数据
     */
    public void send() {
        try {
            SocketChannel socketChannel = SocketChannel.open(inetSocketAddress);
            socketChannel.configureBlocking(false);
            ByteBuffer byteBuffer = ByteBuffer.allocate(512);
            Thread.sleep(10000);
            socketChannel.write(ByteBuffer.wrap(String.format("nio client %s", toString()).getBytes()));
            while (true) {
                byteBuffer.clear();
                int readBytes = socketChannel.read(byteBuffer);
                if (readBytes > 0) {
                    byteBuffer.flip();

                    System.out.println(new String(byteBuffer.array(), 0, readBytes));

                    socketChannel.close();
                    break;
                }
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

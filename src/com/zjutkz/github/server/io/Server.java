package com.zjutkz.github.server.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.Method;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kangzhe on 18/11/6.
 */
public class Server {

    private static int count = 0;

    public static void main(String args[]) {
        ObjectInputStream ois = null;
        ObjectOutputStream oos = null;
        Socket clientSocket = null;
        ServerSocket ss = null;
        try {
            ss = new ServerSocket(8081);
        } catch (IOException e) {
            e.printStackTrace();
        }
        while (true) {
            try {
                clientSocket = ss.accept();
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());
                String msg = (String) ois.readObject();

                System.out.println("req from client msg: " + msg);
                oos.writeObject("server process " + ++count + " times");
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                if (oos != null) {
                    try {
                        oos.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (ois != null) {
                    try {
                        ois.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }

                if (clientSocket != null) {
                    try {
                        clientSocket.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}

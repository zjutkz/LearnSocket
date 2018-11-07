package com.zjutkz.github.server.threads;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Created by kangzhe on 18/11/6.
 */
public class MultiThreadServer {

    static int count = 0;

    public static void main(String args[]) {

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
            }
            catch (IOException e) {
                continue;
            }

            new ServerThread(clientSocket).start();
        }
    }

    private static class ServerThread extends Thread {

        private Socket clientSocket;

        public ServerThread(Socket s) {
            this.clientSocket = s;
        }

        public void run() {
            ObjectInputStream ois = null;
            ObjectOutputStream oos = null;
            try {
                ois = new ObjectInputStream(clientSocket.getInputStream());
                oos = new ObjectOutputStream(clientSocket.getOutputStream());

                String msg = (String) ois.readObject();

                System.out.println("receive client msg: " + msg);
                oos.writeObject("multi process server process " + ++MultiThreadServer.count + " times");
            } catch(Exception e) {
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

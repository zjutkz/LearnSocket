package com.zjutkz.github.client.io;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.lang.reflect.Proxy;
import java.net.InetSocketAddress;
import java.net.Socket;


/**
 * Created by kangzhe on 18/11/6.
 */
public class Caller implements Serializable{

    public Object sendReq() throws IOException {
        Socket s = null;
        ObjectOutputStream oos = null;
        ObjectInputStream ois = null;

        try {
            s = new Socket();
            s.connect(new InetSocketAddress("localhost", 8081));
            oos = new ObjectOutputStream(s.getOutputStream());
            ois = new ObjectInputStream(s.getInputStream());

            Thread.sleep(10000);

            oos.writeObject(String.format("client %s", toString()));

            return ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (s != null)
                s.close();

            if (ois != null)
                ois.close();

            if (oos != null)
                oos.close();
        }
        return null;
    }
}

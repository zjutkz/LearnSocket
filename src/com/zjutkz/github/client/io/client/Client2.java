package com.zjutkz.github.client.io.client;

import com.zjutkz.github.client.io.Caller;

/**
 * Created by kangzhe on 18/11/7.
 */
public class Client2 {

    public static void main(String args[]) {
        try {
            for(int i = 0; i < 3; i++) {
                System.out.println(new Caller().sendReq());
            }
        }catch (Exception e) {
            e.printStackTrace();
        }
    }
}

package com.zjutkz.github.client.nio.client;

import com.zjutkz.github.client.nio.NioCaller;

/**
 * Created by kangzhe on 18/11/7.
 */
public class NioClient2 {

    public static void main(String[] args) {
        for(int i = 0; i < 3 ; i++) {
            new NioCaller().send();
        }
    }
}

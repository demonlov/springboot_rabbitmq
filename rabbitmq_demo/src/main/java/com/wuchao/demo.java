package com.wuchao;

public class demo {

    public static void main(String args[] ) {
        int i = 0;
        System.out.println(i++);
        new Thread(() -> {

            System.out.println("aaa");
        }).start();
    }
}

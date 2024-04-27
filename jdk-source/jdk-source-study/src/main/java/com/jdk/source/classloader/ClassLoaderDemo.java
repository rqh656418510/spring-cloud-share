package com.jdk.source.classloader;

public class ClassLoaderDemo {

    public static int num = 1;

    static {
        num = 2;
        System.out.println(num);
    }

    public static void main(String[] args) {
        num = 3;
        System.out.println(num);
    }

}

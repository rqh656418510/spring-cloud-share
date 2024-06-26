package com.jdk.source.map;

public class MyHashMapTest {

    public static void main(String[] args) {
        test01();
        // test02();
    }

    public static void test01() {
        MyMap<String, Integer> map = new MyHashMap<>();
        map.put("C++", 1);
        map.put("Java", 2);
        map.put("Python", 3);
        map.put("Python", 4);

        System.out.println(map.get("C++"));
        System.out.println(map.get("Java"));
        System.out.println(map.get("Python"));
        System.out.println("size = " + map.size());
    }

    private static void test02() {
        MyMap<String, Integer> map = new MyHashMap<>();
        long star = System.currentTimeMillis();
        for (int i = 0; i < 1000; i++) {
            map.put("Java" + i, i);
        }
        for (int i = 0; i < 1000; i++) {
            map.get("Java");
        }
        long end = System.currentTimeMillis();
        System.out.println((end - star) + "ms");
    }

}

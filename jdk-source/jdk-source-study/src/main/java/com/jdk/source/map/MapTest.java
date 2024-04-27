package com.jdk.source.map;

public class MapTest {

    public static void main(String[] args) {
        test01();
        // test02();
    }

    public static void test01() {
        Map<String, Integer> map = new HashMap<>();
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
        Map<String, Integer> map = new HashMap<>();
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

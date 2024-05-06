package com.jdk.source.list.array;

public class MyArrayListTest {

    public static void main(String[] args) {
        MyArrayList<Integer> list = new MyArrayList<Integer>(10);
        list.add(3);
        list.add(2);
        list.add(1);
        list.add(4);
        list.add(7);
        list.add(5);
        list.add(8);
        System.out.println(list);

        list.set(1, 9);
        System.out.println(list);

        list.add(14);
        list.add(17);
        list.add(15);
        list.add(18);   // 触发扩容
        list.add(18);
        System.out.println(list);

        list.remove(1);
        list.remove(1);
        list.remove(1);
        System.out.println(list);
        System.out.println("size = " + list.size());

        list.clear();
        System.out.println(list);
        System.out.println("size = " + list.size());
    }

}

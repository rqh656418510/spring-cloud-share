package com.jdk.source.list.linked;

public class MyLinkedListTest {

    public static void main(String[] args) {
        MyLinkedList<Integer> list = new MyLinkedList<>();
        list.add(13);
        list.add(12);
        list.add(11);
        list.add(14);
        list.add(17);
        list.add(15);
        list.add(18);
        System.out.println(list);

        Integer value = list.get(5);
        System.out.println(value);

        Integer oldValue = list.set(1, 23);
        System.out.println(oldValue);
        System.out.println(list);
    }

}

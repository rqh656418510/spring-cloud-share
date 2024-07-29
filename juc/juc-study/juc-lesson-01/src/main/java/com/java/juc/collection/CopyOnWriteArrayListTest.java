package com.java.juc.collection;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList 的使用（写时复制）
 *
 * <p> CopyOnWriteArrayList 默认支持并发迭代修改操作
 * <p> Collections.synchronizedList() 不支持并发迭代修改操作，即使使用 ListIterator 进行迭代也解决不了
 */
public class CopyOnWriteArrayListTest {

    public static void main(String[] args) {

        // Runnable task = new Task1();  // 抛出异常

        Runnable task = new Task2();     // 正常运行

        for (int i = 0; i < 10; i++) {
            new Thread(task).start();
        }
    }

}

class Task1 implements Runnable {

    // 在单线程环境下，不支持在迭代过程中对容器进行结构性修改，因为在同步列表内部，迭代器并不知道列表在迭代过程中是否发生了结构性修改，可以使用 ListIterator 来解决
    // 在多线程环境下，支持并发访问，但不支持在迭代过程中对容器进行结构性修改，即使使用 ListIterator 进行迭代也解决不了
    private static List<String> list = Collections.synchronizedList(new ArrayList<>());

    static {
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
    }

    @Override
    public void run() {
        ListIterator<String> iterator = list.listIterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            iterator.add("DDD");
        }
    }

}

class Task2 implements Runnable {

    // 在多线程环境下，支持并发访问，且支持在迭代过程中对容器进行结构性修改
    // 因为容器每次修改时会创建一个新的容器副本，也就是说修改的容器和迭代的容器不是同一个，所以使用迭代器遍历容器不受修改的影响
    private static List<String> list = new CopyOnWriteArrayList<>();

    static {
        list.add("AAA");
        list.add("BBB");
        list.add("CCC");
    }

    @Override
    public void run() {
        Iterator<String> iterator = list.iterator();
        while (iterator.hasNext()) {
            System.out.println(iterator.next());
            list.add("DDD");
        }
    }

}
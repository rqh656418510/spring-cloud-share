package com.jdk.source.map;

/**
 * 手写 JDK 1.7 的 HashMap 实现
 *
 * <p> 数组 + 单向链表
 */
public class MyHashMap<K, V> implements MyMap<K, V> {

    private static int defaultLength = 16;  // 默认容量
    private static float defaultLoader = 0.7f;  // 加载因子
    private Entry<K, V>[] table = null;  // 数组
    private int size = 0;  // 元素数量

    public MyHashMap() {
        this(defaultLength, defaultLoader);
    }

    public MyHashMap(int length, float loader) {
        defaultLength = length;
        defaultLoader = loader;
        // 初始化数组
        table = new Entry[defaultLength];
    }

    /**
     * 哈希算法
     * <p> 哈希算法决定了运行效率（时间复杂度）
     */
    private int hash(K k) {
        int l = defaultLength;
        int i = k.hashCode() % l;
        return i > 0 ? i : -i;
    }

    /**
     * 创建节点
     */
    private Entry<K, V> newEntry(K k, V v, Entry<K, V> next) {
        return new Entry<>(k, v, next);
    }

    /**
     * 查找节点（递归）
     */
    private V find(K k, Entry<K, V> entry) {
        if (k == entry.getKey() || k.equals(entry.getKey())) {
            return entry.getValue();
        } else {
            if (entry.next != null) {
                return find(k, entry.next);
            }
        }
        return null;
    }

    @Override
    public V put(K k, V v) {
        int index = hash(k);
        Entry<K, V> entry = table[index];
        if (entry == null) {
            size++;
            table[index] = newEntry(k, v, null);
        } else {
            // 使用单向链表来解决哈希冲突问题
            table[index] = newEntry(k, v, entry);
        }
        return table[index].getValue();
    }

    @Override
    public V get(K k) {
        int index = hash(k);
        if (index >= defaultLength) {
            return null;
        }
        Entry<K, V> entry = table[index];
        return null == entry ? null : find(k, entry);
    }

    @Override
    public int size() {
        return size;
    }

    /**
     * 定义节点
     * <p> 实现了单向链表的数据结构
     */
    static class Entry<K, V> implements MyMap.Entry<K, V> {

        K k;    // 键
        V v;    // 值
        Entry<K, V> next;   // 下一个节点

        public Entry(K k, V v) {
            this.k = k;
            this.v = v;
            this.next = null;
        }

        public Entry(K k, V v, Entry<K, V> next) {
            this.k = k;
            this.v = v;
            this.next = next;
        }

        @Override
        public K getKey() {
            return k;
        }

        @Override
        public V getValue() {
            return v;
        }

    }

}

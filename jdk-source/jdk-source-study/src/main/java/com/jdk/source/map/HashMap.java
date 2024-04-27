package com.jdk.source.map;

/**
 * 手写 JDK 1.7 的 HashMap 实现
 *
 * <p> 数组 + 单向链表
 */
public class HashMap<K, V> implements Map<K, V> {

    private static int defaultLength = 16;
    private static float defaultLoader = 0.7f;
    private Entry<K, V>[] table = null;
    private int size = 0;

    public HashMap() {
        this(defaultLength, defaultLoader);
    }

    public HashMap(int length, float loader) {
        defaultLength = length;
        defaultLoader = loader;
        table = new Entry[defaultLength];
    }

    private int hash(K k) {
        int l = defaultLength;
        int i = k.hashCode() % l;
        return i > 0 ? i : -i;
    }

    private Entry<K, V> newEntry(K k, V v, Entry<K, V> next) {
        return new Entry<>(k, v, next);
    }

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

    static class Entry<K, V> implements Map.Entry<K, V> {

        K k;
        V v;
        Entry<K, V> next;

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

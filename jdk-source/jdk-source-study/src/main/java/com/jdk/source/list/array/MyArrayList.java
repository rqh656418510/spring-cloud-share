package com.jdk.source.list.array;

import java.util.Arrays;

/**
 * 手写 ArrayList 的实现
 *
 * <p> 底层使用数组实现
 */
public class MyArrayList<E> implements MyList<E> {

    private int size = 0;   // 数组的元素数量

    private Object[] elementData; // 存储数据的数组

    private static final int DEFAULT_CAPACITY = 10;    // 数组的默认大小

    private static final int MAX_ARRAY_SIZE = Integer.MAX_VALUE - 8;    // 数组的最大大小

    public MyArrayList() {
        elementData = new Object[DEFAULT_CAPACITY];
    }

    public MyArrayList(int initialCapacity) {
        if (initialCapacity < 0) {
            throw new IllegalArgumentException("Illegal Capacity: " + initialCapacity);
        }
        elementData = new Object[initialCapacity];
    }

    /**
     * 检查数组的容量是否足够
     */
    private void ensureCapacityInternal(int minCapacity) {
        minCapacity = Math.max(DEFAULT_CAPACITY, minCapacity);
        if (minCapacity - elementData.length > 0) {
            grow(minCapacity);
        }
    }

    /**
     * 数组动态扩容
     */
    private void grow(int minCapacity) {
        int oldCapacity = elementData.length;
        // 按照当前数组容量的 1.5 倍进行扩容
        int newCapacity = oldCapacity + (oldCapacity >> 1);
        // 检查数组扩容后的容量
        if (newCapacity - minCapacity < 0) {
            newCapacity = minCapacity;
        }
        // 检查数组的最大容量
        if (newCapacity - MAX_ARRAY_SIZE > 0) {
            newCapacity = hugeCapacity(minCapacity);
        }
        // 拷贝数组元素
        elementData = Arrays.copyOf(elementData, newCapacity);
    }

    /**
     * 计算数组的最大容量
     */
    private static int hugeCapacity(int minCapacity) {
        if (minCapacity < 0) {
            throw new OutOfMemoryError();
        }
        return (minCapacity > MAX_ARRAY_SIZE) ? Integer.MAX_VALUE : MAX_ARRAY_SIZE;
    }

    /**
     * 检查数组下标的范围
     *
     * @param index
     */
    private void rangeCheck(int index) {
        if (index < 0 || index >= size) {
            throw new IndexOutOfBoundsException();
        }
    }

    private E elementData(int index) {
        return (E) elementData[index];
    }

    @Override
    public void add(E element) {
        ensureCapacityInternal(size + 1);
        elementData[size++] = element;
    }

    @Override
    public E get(int index) {
        rangeCheck(index);
        return elementData(index);
    }

    @Override
    public E set(int index, E element) {
        rangeCheck(index);
        E oldValue = elementData(index);
        elementData[index] = element;
        return oldValue;
    }

    @Override
    public E remove(int index) {
        rangeCheck(index);
        E oldValue = elementData(index);

        // 计算需要移动元素的数量
        int numMoved = size - (index + 1);
        if (numMoved > 0) {
            System.arraycopy(elementData, index + 1, elementData, index, numMoved);
        }

        // GC
        elementData[--size] = null;
        return oldValue;
    }

    @Override
    public void clear() {
        for (int i = 0; i < size; i++) {
            // GC
            elementData[i] = null;
        }
        size = 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < size; i++) {
            stringBuilder.append(elementData[i]);
            if (i < size - 1) {
                stringBuilder.append(", ");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

}

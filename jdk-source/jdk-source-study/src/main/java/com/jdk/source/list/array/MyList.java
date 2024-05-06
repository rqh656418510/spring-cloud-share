package com.jdk.source.list.array;

public interface MyList<E> {

    /**
     * 添加元素
     */
    void add(E element);

    /**
     * 获取元素
     */
    E get(int index);

    /**
     * 设置元素
     */
    E set(int index, E element);

    /**
     * 删除元素
     */
    E remove(int index);

    /**
     * 清空列表
     */
    void clear();

    /**
     * 获取元素个数
     */
    int size();

}

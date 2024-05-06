package com.jdk.source.list.linked;

public interface List<E> {

    /**
     * 添加元素
     */
    void add(E element);

    /**
     * 设置元素
     */
    E set(int index, E element);

    /**
     * 获取元素
     */
    E get(int index);

    /**
     * 删除元素
     */
    E remove(int index);

    /**
     * 清空链表
     */
    void clear();

}

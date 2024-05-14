package com.jdk.source.list.linked;

/**
 * List 接口
 */
public interface MyList<E> {

    /**
     * 添加节点
     */
    void add(E element);

    /**
     * 设置节点
     */
    E set(int index, E element);

    /**
     * 获取节点
     */
    E get(int index);

    /**
     * 删除节点
     */
    E remove(int index);

    /**
     * 清空链表节点
     */
    void clear();

    /**
     * 获取节点数量
     */
    int size();

}

package com.jdk.source.list.linked;

/**
 * 手写 LinkedList 的实现
 */
public class MyLinkedList<E> implements List<E> {

    private int size = 0;   // 链表元素的数量

    private Node<E> first;  // 头节点

    private Node<E> last;   // 尾节点

    public MyLinkedList() {

    }

    /**
     * 链接到链表的尾部
     */
    private void linkLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 检查索引范围
     */
    private void checkElementIndex(int index) {
        if (!isElementIndex(index)) {
            throw new IndexOutOfBoundsException();
        }
    }

    /**
     * 检查索引范围
     */
    private boolean isElementIndex(int index) {
        return index >= 0 && index < size;
    }

    /**
     * 获取指定位置的元素
     */
    private Node<E> node(int index) {
        checkElementIndex(index);
        Node<E> cur = first;
        for (int i = 0; i < index; i++) {
            cur = cur.next;
        }
        return cur;
    }

    @Override
    public void add(E element) {
        linkLast(element);
    }

    @Override
    public E set(int index, E element) {
        Node<E> node = node(index);
        E oldData = node.data;
        node.data = element;
        return oldData;
    }

    @Override
    public E get(int index) {
        Node<E> node = node(index);
        return node.data;
    }

    @Override
    public E remove(int index) {
        return null;
    }

    @Override
    public void clear() {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        Node<E> cur = first;
        while (cur != null) {
            stringBuilder.append(cur.data);
            if (cur != last) {
                stringBuilder.append(", ");
            }
            cur = cur.next;
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    /**
     * 节点
     */
    static class Node<E> {

        /**
         * 数据
         */
        E data;

        /**
         * 上一个节点
         */
        Node<E> prev;

        /**
         * 下一个节点
         */
        Node<E> next;

        Node(Node<E> prev, E element, Node<E> next) {
            this.prev = prev;
            this.next = next;
            this.data = element;
        }

    }

}

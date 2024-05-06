package com.jdk.source.list.linked;

/**
 * 手写 LinkedList 的实现
 *
 * <p> 底层使用双向链表
 */
public class MyLinkedList<E> implements MyList<E> {

    private int size = 0;   // 链表节点的数量

    private Node<E> first;  // 头节点

    private Node<E> last;   // 尾节点

    public MyLinkedList() {

    }

    /**
     * 添加节点到链表的尾部
     */
    private void linkLast(E element) {
        Node<E> l = last;
        Node<E> newNode = new Node<>(l, element, null);
        last = newNode;
        // 判断是否首次添加节点
        if (l == null) {
            first = newNode;
        } else {
            l.next = newNode;
        }
        size++;
    }

    /**
     * 移除节点
     */
    private E unlink(Node<E> node) {
        E data = node.data;
        Node<E> prev = node.prev;
        Node<E> next = node.next;

        // 判断是否移除头节点
        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        // 判断是否移除尾节点
        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.data = null;
        size--;
        return data;
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
     * 获取指定位置的节点
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
        Node<E> node = node(index);
        return unlink(node);
    }

    @Override
    public void clear() {
        Node<E> cur = first;
        while (cur != null) {
            Node<E> next = cur.next;
            // GC
            cur.prev = null;
            cur.next = null;
            cur.data = null;
            cur = next;
        }
        first = null;
        last = null;
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

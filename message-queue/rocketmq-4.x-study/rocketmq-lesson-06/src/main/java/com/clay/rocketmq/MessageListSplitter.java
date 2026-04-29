package com.clay.rocketmq;

import org.apache.rocketmq.common.message.Message;

import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

/**
 * 消息列表分割器（非线程安全），其只会处理每条消息的大小不超过 sizeLimit 的情况
 * 若存在某条消息，其本身大小大于 sizeLimit，这个分割器无法处理，其直接将这条消息构成一个子列表返回，并没有再进行分割
 */
public class MessageListSplitter implements Iterator<List<Message>> {

    // 指定每条消息的极限大小
    private final int sizeLimit;

    // 存放所有要发送的消息
    private final List<Message> messages;

    // 要进行批量发送的消息起始索引
    private int currIndex;

    public MessageListSplitter(List<Message> messages, int sizeLimit) {
        if (sizeLimit <= 0) {
            throw new IllegalArgumentException("sizeLimit must be > 0");
        }

        this.messages = messages;
        this.sizeLimit = sizeLimit;
    }

    @Override
    public boolean hasNext() {
        // 判断当前开始遍历的消息索引要小于消息总数
        return currIndex < messages.size();
    }

    @Override
    public List<Message> next() {
        if (!hasNext()) {
            throw new NoSuchElementException();
        }

        // 当前遍历的消息的索引
        int nextIndex = currIndex;

        // 记录当前要发送的这一小批次消息列表的大小
        int totalSize = 0;

        for (; nextIndex < messages.size(); nextIndex++) {
            // 获取当前遍历的消息
            Message message = messages.get(nextIndex);

            // 统计当前遍历的消息的大小，消息结构：Topic | Boby | Log | Properties，其中 Log 固定 20 个字节
            int msgSize = message.getTopic().getBytes(StandardCharsets.UTF_8).length;

            byte[] body = message.getBody();
            int bodySize = body == null ? 0 : body.length;
            msgSize += bodySize;

            Map<String, String> properties = message.getProperties();
            if (properties != null) {
                for (Map.Entry<String, String> entry : properties.entrySet()) {
                    String key = entry.getKey();
                    String value = entry.getValue();
                    if (key != null) {
                        msgSize += key.getBytes(StandardCharsets.UTF_8).length;
                    }
                    if (value != null) {
                        msgSize += value.getBytes(StandardCharsets.UTF_8).length;
                    }
                }
            }
            msgSize += 20;

            // 判断当前消息本身是否大于 4MB
            if (msgSize > sizeLimit) {
                // 打印日志信息
                System.out.printf("Single message size exceeded limit, topic=%s, size=%d%n", message.getTopic(), msgSize);
                // 如果当前批次还没有任何消息，强行塞一条消息
                if (nextIndex == currIndex) {
                    nextIndex++;
                }
                break;
            }

            if (msgSize + totalSize > sizeLimit) {
                break;
            } else {
                totalSize += msgSize;
            }
        }

        // 获取当前消息列表的子集合[currIndex, nextIndex)
        List<Message> subList = new ArrayList<>(messages.subList(currIndex, nextIndex));

        // 下次遍历的起始索引
        currIndex = nextIndex;

        return subList;
    }

}
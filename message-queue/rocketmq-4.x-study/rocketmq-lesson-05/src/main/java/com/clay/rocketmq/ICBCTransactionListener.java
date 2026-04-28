package com.clay.rocketmq;

import org.apache.commons.lang3.StringUtils;
import org.apache.rocketmq.client.producer.LocalTransactionState;
import org.apache.rocketmq.client.producer.TransactionListener;
import org.apache.rocketmq.common.message.Message;
import org.apache.rocketmq.common.message.MessageExt;

/**
 * 事务监听器（以工商银行转账为例）
 */
public class ICBCTransactionListener implements TransactionListener {

    /**
     * 回调操作方法 <br>
     * 消息预提交成功后，就会触发该方法的执行，用于完成本地事务（分支事务）的执行
     */
    @Override
    public LocalTransactionState executeLocalTransaction(Message msg, Object arg) {
        System.out.println("消息预提交成功：" + msg);

        // 假设接收到 TAGA 的消息就表示扣款操作成功，TAGB 的消息表示扣款失败，TAGC 的消息表示扣款结果不清楚，需要执行状态回查
        if (StringUtils.equals("TAGA", msg.getTags())) {
            return LocalTransactionState.COMMIT_MESSAGE;
        } else if (StringUtils.equals("TAGB", msg.getTags())) {
            return LocalTransactionState.ROLLBACK_MESSAGE;
        } else if (StringUtils.equals("TAGC", msg.getTags())) {
            return LocalTransactionState.UNKNOW;
        }

        return LocalTransactionState.UNKNOW;
    }

    /**
     * 状态回查方法 <br>
     * 触发状态回查的最常见原因有两个：
     * (1) 回调操作返回 UNKNWON
     * (2) TC 没有接收到 TM 的最终全局事务确认指令
     */
    @Override
    public LocalTransactionState checkLocalTransaction(MessageExt msg) {
        System.out.println("执行状态回查" + msg.getTags());
        return LocalTransactionState.COMMIT_MESSAGE;
    }

}
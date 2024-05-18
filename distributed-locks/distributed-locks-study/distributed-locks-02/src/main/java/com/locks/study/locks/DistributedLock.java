package com.locks.study.locks;

import redis.clients.jedis.Jedis;
import redis.clients.jedis.Transaction;

import java.util.UUID;

/**
 * 分布式锁
 * <p> 基于 Redis 的事务，实现分布式锁
 */
public class DistributedLock {

    private final Jedis jedis;
    private final String lockKey;
    private final int lockTimeout;
    private String lockValue; // 用于存储锁的值，防止其他客户端误解锁

    /**
     * 初始化分布式锁
     *
     * @param jedis       Jedis 客户端
     * @param lockKey     锁的 Key
     * @param lockTimeout 加锁的超时时间，单位秒
     */
    public DistributedLock(Jedis jedis, String lockKey, int lockTimeout) {
        this.jedis = jedis;
        this.lockKey = lockKey;
        this.lockTimeout = lockTimeout;
    }

    /**
     * 加锁
     */
    public boolean lock() {
        try {
            long start = System.currentTimeMillis();
            while (System.currentTimeMillis() - start < lockTimeout) {
                // 生成一个唯一的锁值，用于识别锁的持有者
                lockValue = UUID.randomUUID().toString() + ":" + System.currentTimeMillis();

                // 监视锁，确保 MULTI/EXEC 执行期间锁未被修改
                jedis.watch(lockKey);

                // 判断锁是否已被其他客户端持有
                if (jedis.get(lockKey) == null) {
                    // 开启事务
                    Transaction tx = jedis.multi();
                    // 加锁，并设置锁的过期时间
                    tx.setex(lockKey, lockTimeout, lockValue);
                    // 执行事务
                    if (tx.exec() != null) {
                        // 加锁成功
                        return true;
                    }
                }

                // 解除监视，重新尝试加锁
                jedis.unwatch();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 加锁失败
        return false;
    }

    /**
     * 解锁
     */
    public boolean unlock() {
        try {
            while (true) {
                // 监视锁，确保 MULTI/EXEC 执行期间锁未被修改
                jedis.watch(lockKey);

                String currentLockValue = jedis.get(lockKey);
                if (currentLockValue != null && currentLockValue.equals(lockValue)) {
                    // 开启事务
                    Transaction tx = jedis.multi();
                    // 释放锁
                    tx.del(lockKey);
                    // 执行事务
                    if (tx.exec() != null) {
                        // 解锁成功
                        return true;
                    }
                }
                // 解除监视
                jedis.unwatch();
                // 结束循环
                break;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        // 解锁失败
        return false;
    }

}

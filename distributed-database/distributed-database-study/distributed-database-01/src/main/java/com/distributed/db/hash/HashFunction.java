package com.distributed.db.hash;

/**
 * 根据某种自定义的Hash算法来进行散列,并根据散列的值进行路由
 * 常见的水平切分规则有:
 * 基于范围的切分, 比如 memberId > 10000 and memberId < 20000
 * 基于模数的切分, 比如 memberId%128==1 或者 memberId%128==2 或者...
 * 基于哈希(hashing)的切分, 比如hashing(memberId)==someValue等
 */
public class HashFunction {

    /**
     * 对三个主数据库进行散列分布
     * <p> 返回其他值，没有在配置文件中配置的，如负数等，则在默认数据库中查找
     */
    public int applyEmployee(Long employeeId) {
        int result = Math.abs(employeeId.hashCode() % 1024);
        System.out.println("hash : " + result);
        if (0 <= result && result < 341) {
            result = 1;
            System.out.println("在第1个数据库中");
        } else if (341 <= result && result < 682) {
            result = 2;
            System.out.println("在第2个数据库中");
        } else if (682 <= result && result < 1024) {
            result = 3;
            System.out.println("在第3个数据库中");
        }
        return result;
    }

}

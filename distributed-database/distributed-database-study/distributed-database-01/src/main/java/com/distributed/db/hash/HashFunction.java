package com.distributed.db.hash;

public class HashFunction {

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

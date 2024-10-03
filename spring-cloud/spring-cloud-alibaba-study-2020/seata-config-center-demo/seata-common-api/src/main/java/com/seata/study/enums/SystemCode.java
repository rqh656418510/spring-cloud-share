package com.seata.study.enums;

/**
 * @author clay
 * @version 1.0
 */
public enum SystemCode {

    BUSINESS_OK(200, "处理成功"),

    ERROR_PARAMETER(300, "非法参数"),

    STORAGE_NOT_ENOUGH(400, "库存不足"),

    ACCOUNT_NOT_ENOUGH(500, "余额不足");

    private int code;

    private String description;

    private SystemCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SystemCode{" +
                "code=" + code +
                ", description='" + description + '\'' +
                '}';
    }
}

package com.clay.zuul.gateway.domain;

import java.io.Serializable;

/**
 * 网关API路由规则
 */
public class GatewayApiRoute implements Serializable {

    private String id;
    private String path;
    private String serviceId;
    private String url;
    private int stripPrefix = 1;
    private int retryable;
    private int enabled;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getServiceId() {
        return serviceId;
    }

    public void setServiceId(String serviceId) {
        this.serviceId = serviceId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public int getStripPrefix() {
        return stripPrefix;
    }

    public void setStripPrefix(int stripPrefix) {
        this.stripPrefix = stripPrefix;
    }

    public int getRetryable() {
        return retryable;
    }

    public void setRetryable(int retryable) {
        this.retryable = retryable;
    }

    public int getEnabled() {
        return enabled;
    }

    public void setEnabled(int enabled) {
        this.enabled = enabled;
    }
}
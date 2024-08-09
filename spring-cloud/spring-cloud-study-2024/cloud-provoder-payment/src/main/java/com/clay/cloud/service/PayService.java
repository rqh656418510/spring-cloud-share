package com.clay.cloud.service;

import com.clay.cloud.entities.Pay;

import java.util.List;

/**
 * @author clay
 * @version 1.0
 */
public interface PayService {

    public int add(Pay pay);

    public int delete(Integer id);

    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();

}

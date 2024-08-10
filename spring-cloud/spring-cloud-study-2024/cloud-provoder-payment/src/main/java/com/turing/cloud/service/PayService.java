package com.turing.cloud.service;

import com.turing.cloud.entities.Pay;

import java.util.List;

/**
 * @author turing
 * @version 1.0
 */
public interface PayService {

    public int add(Pay pay);

    public int delete(Integer id);

    public int update(Pay pay);

    public Pay getById(Integer id);

    public List<Pay> getAll();

}

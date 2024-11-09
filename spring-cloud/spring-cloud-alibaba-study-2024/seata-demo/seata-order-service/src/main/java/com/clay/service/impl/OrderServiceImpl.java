package com.clay.service.impl;

import com.clay.client.AccountFeignApi;
import com.clay.client.StorageFeignApi;
import com.clay.entities.Order;
import com.clay.mapper.OrderMapper;
import com.clay.resp.ResultData;
import com.clay.service.OrderService;
import jakarta.annotation.Resource;
import lombok.extern.slf4j.Slf4j;
import org.apache.seata.core.context.RootContext;
import org.apache.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

/**
 * @author turing
 * @version 1.0
 */
@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private StorageFeignApi storageFeignApi;

    @Resource
    private AccountFeignApi accountFeignApi;

    @Override
    @GlobalTransactional(name = "seata-create-order", rollbackFor = Exception.class)
    public ResultData create(Order order) {
        // 查看 Seata 的 XID
        String xid = RootContext.getXID();
        log.info("==================>开始创建订单" + "\t" + "xid: " + xid);

        // 1. 插入订单
        order.setStatus(0);
        int result = orderMapper.insert(order);

        Order orderFromDB = null;
        if (result > 0) {
            // 插入订单成功后，获取插入MySQL的订单实体对象
            orderFromDB = orderMapper.selectById(order.getId());
            log.info("-------> 创建订单成功，orderFromDB info: " + orderFromDB);
            System.out.println();

            // 2. 扣减库存
            log.info("-------> 订单微服务开始调用Storage库存服务，开始扣减库存");
            ResultData storageResult = storageFeignApi.decrease(orderFromDB.getProductId(), orderFromDB.getCount());
            Assert.isTrue(storageResult.isSuccess(), storageResult.getMessage());
            log.info("-------> 订单微服务结束调用Storage库存服务，扣减库存完成");
            System.out.println();

            // 3. 扣减账户余额
            log.info("-------> 订单微服务开始调用Account账户服务，开始扣减账户余额");
            ResultData accountResult = accountFeignApi.decrease(orderFromDB.getUserId(), orderFromDB.getMoney());
            Assert.isTrue(accountResult.isSuccess(), accountResult.getMessage());
            log.info("-------> 订单微服务结束调用Account账户服务，扣减账户余额完成");
            System.out.println();

            // 4. 修改订单状态
            log.info("-------> 开始修改订单状态");
            int updateResult = orderMapper.updateStatus(orderFromDB.getId(), 1);
            log.info("-------> 修改订单状态完成" + "\t" + "修改结果： " + updateResult);
        } else {
            log.info("-------> 订单创建失败");
        }

        System.out.println();
        log.info("==================>结束创建订单" + "\t" + "xid: " + xid);

        return ResultData.success(orderFromDB);
    }

}

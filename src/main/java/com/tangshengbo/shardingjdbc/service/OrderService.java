package com.tangshengbo.shardingjdbc.service;

import com.tangshengbo.shardingjdbc.dao.OrderItemMapper;
import com.tangshengbo.shardingjdbc.dao.OrderMapper;
import com.tangshengbo.shardingjdbc.model.Order;
import com.tangshengbo.shardingjdbc.model.OrderItem;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2020/1/7
 */
@Service
public class OrderService {

    @Resource
    private OrderMapper orderMapper;

    @Resource
    private OrderItemMapper orderItemMapper;

    public String addOrder(Order order) {
        orderMapper.insertSelective(order);
        Long orderId = order.getOrderId();
        OrderItem item = new OrderItem();
        item.setOrderId(orderId);
        item.setUserId(order.getUserId());
        item.setStatus("INSERT_TEST");
        orderItemMapper.insertSelective(item);
        return orderId + "|" + item.getOrderItemId();
    }

    public Order findByOrderId(Long orderId) {
        return orderMapper.selectByPrimaryKey(orderId);
    }
}

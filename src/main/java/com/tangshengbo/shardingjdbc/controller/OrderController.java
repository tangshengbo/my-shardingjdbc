package com.tangshengbo.shardingjdbc.controller;

import com.tangshengbo.shardingjdbc.model.Order;
import com.tangshengbo.shardingjdbc.service.OrderService;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Tangshengbo
 *
 * @author Tangshengbo
 * @date 2020/1/7
 */
@RestController
@RequestMapping("/order")
public class OrderController {

    @Resource
    private OrderService orderService;

    @PostMapping()
    public String addOrder(@RequestBody Order order) {
        return orderService.addOrder(order);
    }

    @GetMapping("/{orderId}")
    public Order findByOrderId(@PathVariable("orderId") Long orderId) {
        return orderService.findByOrderId(orderId);
    }

    @GetMapping("/user/{userId}")
    public List<Order> findListByUserId(@PathVariable("userId") Integer userId) {
        return orderService.findListByUserId(userId);
    }

}

package com.tangshengbo.shardingjdbc.controller;

import com.tangshengbo.shardingjdbc.model.Order;
import com.tangshengbo.shardingjdbc.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by admin on 2020/1/10
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Resource
    private OrderService orderService;

    @GetMapping("/{userId}")
    public List<Order> findListByUserId(@PathVariable("userId") Integer userId) {
        return orderService.findListByUserId(userId);
    }
}

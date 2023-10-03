package com.example.springkafka.controller;

import com.example.springkafka.model.Order;
import com.example.springkafka.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/order")
public class OrderController {


    @Autowired
    private OrderService orderService;

    @PostMapping(value = "/create")
    public Order saveOrder(@RequestBody Order order) {

        order=orderService.saveOrder(order);
        return order;
    }

    @GetMapping("/all")
    public List<Order> getOrders() {

        return orderService.getOrder();
    }
}
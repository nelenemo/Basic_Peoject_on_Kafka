package com.example.springkafka.service;

import com.example.springkafka.model.Order;
import com.example.springkafka.repo.OrderRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.weaver.ast.Or;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepo orderRepo;
    @Autowired
    KafkaTemplate<String, String> kafkaTemplate;
    @Value("${order.topic.name}")
    private String topicName;

    ObjectMapper om = new ObjectMapper();

    public Order saveOrder(Order order) {
        order = orderRepo.save(order);

        order.setStatus("CREATED");
        String orderStr=null;
        try {
            orderStr = om.writeValueAsString(order);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        kafkaTemplate.send(topicName, orderStr);

        return order;
    }

    public List<Order> getOrder(){
        List<Order> orders=orderRepo.findAll();
        return orders;

    }


}

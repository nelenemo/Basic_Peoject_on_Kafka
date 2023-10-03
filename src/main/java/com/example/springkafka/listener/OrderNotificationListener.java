package com.example.springkafka.listener;

import com.example.springkafka.model.Order;
import com.example.springkafka.model.User;
import com.example.springkafka.repo.OrderRepo;
import com.example.springkafka.repo.UserRepo;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
public class OrderNotificationListener {
@Autowired
    OrderRepo orderRepo;
@Autowired
    UserRepo userRepo;

    ObjectMapper om=new ObjectMapper();

    @KafkaListener(topics = "order-topic", groupId="groupId")
    public void processOrder(String orderString){
        System.out.println("Receiver Message:" + orderString);
        try {
            Order order = om.readValue(orderString, Order.class);
            User user=userRepo.findById(order.getUserId()).get();
            if(user.getBalance()>order.getOrderAmount()){
                order.setStatus("SUCCESS");
                user.setBalance(user.getBalance()-order.getOrderAmount());

            }
            orderRepo.save(order);

        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }


    }


}

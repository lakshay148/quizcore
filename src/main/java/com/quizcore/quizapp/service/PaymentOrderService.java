package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.PaymentOrder;
import com.quizcore.quizapp.model.repository.PaymentOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class PaymentOrderService {

    @Autowired
    PaymentOrderRepository orderRepository;

    public UUID createOrder(PaymentOrder order){
        PaymentOrder savedOrder = orderRepository.save(order);
        return savedOrder.id;
    }
}

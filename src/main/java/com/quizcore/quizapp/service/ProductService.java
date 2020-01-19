package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.*;
import com.quizcore.quizapp.model.network.request.product.ProductMetrics;
import com.quizcore.quizapp.model.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class ProductService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    QuizRepository quizRepository;

    @Autowired
    PaymentOrderRepository paymentOrderRepository;

    @Autowired
    ResultRepository resultRepository;

    @Autowired
    JobRepository jobRepository;

    public ProductMetrics getMetrics(UUID productId){
        ProductMetrics productMetrics = new ProductMetrics();
        productMetrics.setJobs(((List<Job>)jobRepository.findAll()).size());
        productMetrics.setUsers(((List<User>)userRepository.findAllByProductId(productId)).size());
        productMetrics.setPayments(((List<PaymentOrder>)paymentOrderRepository.findAll()).size());
        productMetrics.setQuizresponses(((List<Result>)resultRepository.findAll()).size());
        productMetrics.setQuizzes(((List<Quiz>)quizRepository.findAll()).size());
        return productMetrics;
    }
}

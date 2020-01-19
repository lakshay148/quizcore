package com.quizcore.quizapp.model.network.request.product;

import lombok.Data;

@Data
public class ProductMetrics {
    int users;
    int quizzes;
    int payments;
    int quizresponses;
    int jobs;
}

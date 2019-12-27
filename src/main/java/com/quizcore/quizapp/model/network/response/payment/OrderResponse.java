package com.quizcore.quizapp.model.network.response.payment;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class OrderResponse {
    String redirectionUrl;
}

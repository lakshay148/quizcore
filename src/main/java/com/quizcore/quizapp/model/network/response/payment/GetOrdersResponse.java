package com.quizcore.quizapp.model.network.response.payment;

import com.quizcore.quizapp.model.entity.PaymentOrder;
import lombok.Data;

import java.util.List;

@Data
public class GetOrdersResponse {
    List<PaymentOrder> orders;
}

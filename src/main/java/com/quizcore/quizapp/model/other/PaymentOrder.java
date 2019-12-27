package com.quizcore.quizapp.model.other;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.UUID;

@ToString
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PaymentOrder {
    double amount;
    UUID userId;
    UUID productId;
    UUID partnerId;
}

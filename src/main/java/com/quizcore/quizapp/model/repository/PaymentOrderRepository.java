package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.PaymentOrder;
import org.springframework.data.repository.CrudRepository;

import java.util.UUID;

public interface PaymentOrderRepository extends CrudRepository<PaymentOrder, UUID> {

}

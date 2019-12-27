package com.quizcore.quizapp.model.entity;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Date;
import java.util.UUID;

@Entity
public class PaymentOrder {

    public PaymentOrder(UUID userId, UUID productId, UUID partnerId, double amount) {
        this.userId = userId;
        this.productId = productId;
        this.partnerId = partnerId;
        this.amount = amount;
    }

    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    @Type(type="uuid-char")
    public UUID id;

    @Type(type="uuid-char")
    UUID userId;

    @Type(type="uuid-char")
    UUID productId;

    @Type(type="uuid-char")
    UUID partnerId;

    double amount;

    @CreationTimestamp
    Date createdAt;

    @UpdateTimestamp
    Date updatedAt;

}

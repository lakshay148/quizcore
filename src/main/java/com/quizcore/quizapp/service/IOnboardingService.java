package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.other.ProductPartner;

import java.util.UUID;

public interface IOnboardingService {

    Product onboardProduct(Product product);
    ProductPartner onboardPartner(ProductPartner partner);
    UUID addProduct(Product product);
    User getProductByEmailOrPhone(Product product);
}

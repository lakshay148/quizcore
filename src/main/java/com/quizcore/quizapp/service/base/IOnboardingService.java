package com.quizcore.quizapp.service.base;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.other.ProductPartner;

public interface IOnboardingService {
    Product onboardProduct(Product product);
    ProductPartner onboardPartner(ProductPartner partner);
}

package com.quizcore.quizapp.service.base;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.other.ProductPartner;

import java.util.UUID;

public interface IOnboardingService {
    Product onboardProduct(Product product);
    Partner onboardPartner(Partner partner);
}

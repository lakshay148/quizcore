package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.other.ProductPartner;
import com.quizcore.quizapp.model.repository.ProductRepository;
import com.quizcore.quizapp.service.base.IOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

public class OnboardingService implements IOnboardingService {

    @Autowired
    ProductRepository productRepository;

    @Override
    public ProductPartner onboardPartner(ProductPartner partner) {
        return null;
    }


    @Override
    public Product onboardProduct(Product product) {
        Product productSaved = productRepository.save(product);
        return productSaved;
    }

    public Product getProductByEmailOrPhone(Product product) {
        Product productSaved = productRepository.findByEmailOrPhone(product.getEmail(), product.getMobile());
        return productSaved;
    }
}

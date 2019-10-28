package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.User;
import com.quizcore.quizapp.model.other.ProductPartner;
import com.quizcore.quizapp.model.repository.ProductRepository;
import com.quizcore.quizapp.service.base.IOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
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
        Product productSaved = productRepository.findByEmailOrMobile(product.getEmail(), product.getMobile());
        return productSaved;
    }

    public Product getProductByKey(Product product) {
        Optional<Product> productSaved = productRepository.findById(product.getId());
        Product productByKey = null;
        if(productSaved != null){
            productByKey = productSaved.get();
        }

        return productByKey;
    }
}

package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.other.ProductPartner;
import com.quizcore.quizapp.model.repository.PartnerRepository;
import com.quizcore.quizapp.model.repository.ProductRepository;
import com.quizcore.quizapp.service.base.IOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OnboardingService implements IOnboardingService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Override
    public Partner onboardPartner(Partner partner) {
        Partner partnerSaved = partnerRepository.save(partner);
        return partnerSaved;
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
        if (productSaved.isPresent()) {
            productByKey = productSaved.get();
        }
        return productByKey;
    }

    public Partner getPartnerByEmailOrMobile(Partner partner) {
        Partner partnerSaved = partnerRepository.findByEmailOrMobile(partner.getEmail(), partner.getMobile());
        return partnerSaved;
    }

    public List<Partner> getPartners(UUID productId) {
        List<Partner> partners = partnerRepository.findAllByProductId(productId);
        return partners;
    }


}


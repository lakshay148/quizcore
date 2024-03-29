package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.repository.PartnerRepository;
import com.quizcore.quizapp.model.repository.ProductRepository;
import com.quizcore.quizapp.model.repository.QuizRepository;
import com.quizcore.quizapp.service.base.IOnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class OnboardingService implements IOnboardingService {

    @Autowired
    ProductRepository productRepository;

    @Autowired
    PartnerRepository partnerRepository;

    @Autowired
    QuizRepository quizRepository;

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

    public List<Quiz> getQuizes(UUID partnerId) {
        List<Quiz> quizes = quizRepository.findAllByPartnerId(partnerId);
        return quizes;
    }


}


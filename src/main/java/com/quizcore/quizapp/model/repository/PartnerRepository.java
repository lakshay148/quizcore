package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Quiz;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, UUID> {

    Partner findByEmailOrMobile(String email, String mobile);
    List<Partner> findAllByProductId(UUID productId);
}

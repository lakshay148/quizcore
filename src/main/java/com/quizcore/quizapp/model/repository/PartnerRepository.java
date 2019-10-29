package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface PartnerRepository extends CrudRepository<Partner, UUID> {

    Partner findByEmailOrMobile(String email, String mobile);

}

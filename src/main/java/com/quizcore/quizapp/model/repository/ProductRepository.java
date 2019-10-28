package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ProductRepository extends CrudRepository<Product, UUID> {

    Product findByEmailOrMobile(String email, String mobile);

}

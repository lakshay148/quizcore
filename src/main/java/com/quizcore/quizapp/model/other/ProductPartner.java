package com.quizcore.quizapp.model.other;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Quiz;

import java.util.List;
import java.util.UUID;

public class ProductPartner {
    UUID id;
    String title;
    String description;
    String email;
    String mobile;
    Product product;
    List<Quiz> quizList;

    public ProductPartner(Product product) {
        this.product = product;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }

    public List<Quiz> getQuizList() {
        return quizList;
    }

    public void setQuizList(List<Quiz> quizList) {
        this.quizList = quizList;
    }
}

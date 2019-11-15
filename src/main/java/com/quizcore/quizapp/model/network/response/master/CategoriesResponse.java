package com.quizcore.quizapp.model.network.response.master;

import com.quizcore.quizapp.model.entity.Category;

import java.util.List;

public class CategoriesResponse {

    List<Category> categories;

    public List<Category> getCategories() {
        return categories;
    }

    public void setCategories(List<Category> categories) {
        this.categories = categories;
    }
}

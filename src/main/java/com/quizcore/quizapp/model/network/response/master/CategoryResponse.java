package com.quizcore.quizapp.model.network.response.master;

import java.util.UUID;

public class CategoryResponse {

    private UUID productkey;
    private String categoryname;
    private UUID categoryId;

    public UUID getProductkey() {
        return productkey;
    }

    public void setProductkey(UUID productkey) {
        this.productkey = productkey;
    }

    public String getCategoryname() {
        return categoryname;
    }

    public void setCategoryname(String categoryname) {
        this.categoryname = categoryname;
    }

    public UUID getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(UUID categoryId) {
        this.categoryId = categoryId;
    }
}

package com.quizcore.quizapp.model.network.request.master;

public class AddCategoryRequest {

    private String productId;

    private String categoryName;

    public AddCategoryRequest(String productId, String categoryName) {
        this.productId = productId;
        this.categoryName = categoryName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Override
    public String toString() {
        return "AddCategoryRequest{" +
                "productId='" + productId + '\'' +
                ", categoryName='" + categoryName + '\'' +
                '}';
    }
}

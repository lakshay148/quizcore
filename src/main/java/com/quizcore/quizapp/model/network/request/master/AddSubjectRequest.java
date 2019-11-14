package com.quizcore.quizapp.model.network.request.master;

public class AddSubjectRequest {

    private String productId;

    private String subjectName;

    public AddSubjectRequest(String productId, String subjectName) {
        this.productId = productId;
        this.subjectName = subjectName;
    }

    public String getProductId() {
        return productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    @Override
    public String toString() {
        return "AddSubjectRequest{" +
                "productId='" + productId + '\'' +
                ", subjectName='" + subjectName + '\'' +
                '}';
    }


}

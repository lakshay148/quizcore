package com.quizcore.quizapp.model.network.response.product;

import java.util.UUID;

public class ProductResponse {

    private UUID productkey;
    private String message;
    private String mobile;
    private String email;
    private String type;
    private String title;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public UUID getProductkey() {
        return productkey;
    }

    public void setProductkey(UUID productkey) {
        this.productkey = productkey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}

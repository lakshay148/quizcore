package com.quizcore.quizapp.model.network.response.product;

import java.util.UUID;

public class ProductResponse {

    private UUID productkey;
    private String message;

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

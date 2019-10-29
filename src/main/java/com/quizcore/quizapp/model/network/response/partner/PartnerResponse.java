package com.quizcore.quizapp.model.network.response.partner;

import java.util.UUID;

public class PartnerResponse {

    private UUID partnerkey;
    private String message;
    private String mobile;
    private String email;
    private String description;
    private String title;

    public UUID getPartnerkey() {
        return partnerkey;
    }

    public void setPartnerkey(UUID partnerkey) {
        this.partnerkey = partnerkey;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
}

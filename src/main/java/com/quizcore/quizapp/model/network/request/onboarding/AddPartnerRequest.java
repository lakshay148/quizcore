package com.quizcore.quizapp.model.network.request.onboarding;

public class AddPartnerRequest extends OnboardRequest {

    @Override
    public String toString() {
        return "AddPartnerRequest{" +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                '}';
    }
}

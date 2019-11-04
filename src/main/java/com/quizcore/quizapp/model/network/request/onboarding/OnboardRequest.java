package com.quizcore.quizapp.model.network.request.onboarding;

import com.quizcore.quizapp.model.network.request.BaseRequest;
import com.quizcore.quizapp.model.other.Validity;

public class OnboardRequest extends BaseRequest<OnboardRequest> {
    String name;
    String email;
    String mobile;
    String description;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return "OnboardRequest{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", mobile='" + mobile + '\'' +
                ", description='" + description + '\'' +
                '}';
    }

    @Override
    public Validity validateRequest(OnboardRequest request) {
        Validity nonNullValidity = checkNonNull(request);
        if(!nonNullValidity.isValid())
            return nonNullValidity;
        return new Validity(true, "valid");
    }

    public Validity checkNonNull(OnboardRequest request){
        if(request.email == null){
            return new Validity(false, "invalid email");
        }
        if(request.mobile == null){
            return new Validity(false, "invalid mobile");
        }
        return new Validity(true, "valid");
    }
}

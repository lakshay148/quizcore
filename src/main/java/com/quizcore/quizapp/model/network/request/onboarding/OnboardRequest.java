package com.quizcore.quizapp.model.network.request.onboarding;

import com.quizcore.quizapp.model.network.request.BaseRequest;
import com.quizcore.quizapp.model.other.Validity;

import java.util.Map;

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


    public Validity validate(OnboardRequest request) {
        Validity validity = validatePhone(request.getMobile());
        if(!validity.isValid()){
            return validity;
        }
        Validity validity1 = checkNonNull(request.getEmail());
        if(!validity1.isValid()){
            return validity1;
        }
        return new Validity(true, "valid");
    }

    public Validity checkNonNull(String email){
        if(email == null || email.length() == 0){
            return new Validity(false, "invalid email");
        }
        if(mobile == null || mobile.length() == 0){
            return new Validity(false, "invalid mobile");
        }
        return new Validity(true, "valid");
    }

    public Validity validatePhone(String mobile) {
        if(mobile == null || mobile.length() < 10 || mobile.length() > 10){
            return new Validity(false, "Please enter 10 digits");
        }
        return new Validity(true, "valid");
    }

    @Override
    public Validity validateRequest(OnboardRequest request) {
        return null;
    }

    @Override
    public Validity validateHeaders(Map<String, String> headers) {
        return null;
    }
}

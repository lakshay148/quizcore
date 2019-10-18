package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("quizcore/api/v1/onboard")
public class OnboardingController {



    @GetMapping("/healthcheck")
    public BaseResponse<Object> checkHealth() {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }

    @PostMapping("/product")
    public BaseResponse<Object> addProduct(@RequestBody AddProductRequest request) {
        System.out.println("request " + request.toString());
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }

    @GetMapping("/product")
    public BaseResponse<Object> getProduct() {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }

    @PostMapping("/product/{productId}/partner")
    public BaseResponse<Object> addPartner(@RequestBody AddPartnerRequest request) {
        System.out.println("request " + request.toString());
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }
}

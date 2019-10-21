package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

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

    @GetMapping("/product/{productKey}")
    public BaseResponse<Object> getProduct(@PathParam("productKey") String productKey) {
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

    @GetMapping("/product/{productId}/partner")
    public BaseResponse<Object> getPartners(@PathParam("productId") String productId) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }

    @GetMapping("/product/{productId}/partner/{partnerId}")
    public BaseResponse<Object> getPartnerDetails(@PathParam("productId") String productId, @PathParam("partnerId") String partnerId) {
        BaseResponse<Object> response = new BaseResponse<>();
        response.message = "It works awesone";
        response.status = 200;
        return response;
    }
}

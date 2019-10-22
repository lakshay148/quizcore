package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@RequestMapping("quizcore/api/v1/onboard")
public class OnboardingController {

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @PostMapping("/product")
    public SuccessResponse<Object> addProduct(@RequestBody AddProductRequest request) {
        System.out.println("request " + request.toString());
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/product/{productKey}")
    public SuccessResponse<Object> getProduct(@PathParam("productKey") String productKey) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @PostMapping("/product/{productId}/partner")
    public SuccessResponse<Object> addPartner(@RequestBody AddPartnerRequest request) {
        System.out.println("request " + request.toString());
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/product/{productId}/partner")
    public SuccessResponse<Object> getPartners(@PathParam("productId") String productId) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/product/{productId}/partner/{partnerId}")
    public SuccessResponse<Object> getPartnerDetails(@PathParam("productId") String productId, @PathParam("partnerId") String partnerId) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }
}

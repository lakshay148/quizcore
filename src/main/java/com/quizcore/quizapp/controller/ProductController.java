package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.partner.GetPartnerQuizResponse;
import com.quizcore.quizapp.model.network.response.partner.GetProductPartnersResponse;
import com.quizcore.quizapp.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

@RestController
//@RequestMapping("api/v1/product")
@RequestMapping("qizcore/api/v1/product")
public class ProductController {

    @Autowired
    OnboardingService onboardingService;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/{productKey}")
    public SuccessResponse<Object> getProduct(@PathParam("productKey") String productKey) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/{productId}/partner")
    public SuccessResponse<GetProductPartnersResponse> getPartners(@PathParam("productId") String productId) {
        SuccessResponse<GetProductPartnersResponse> response = new SuccessResponse<>("Product Partners");
        ArrayList<Partner> partners = (ArrayList<Partner>) onboardingService.getPartners(UUID.fromString(productId));
        GetProductPartnersResponse partnersResponse = new GetProductPartnersResponse();
        partnersResponse.setPartners(partners);
        response.data = partnersResponse;
        return response;
    }

    @GetMapping("/{productId}/partner/{partnerId}")
    public SuccessResponse<GetPartnerQuizResponse> getPartnerDetails(@PathParam("productId") String productId, @PathParam("partnerId") String partnerId) {
        SuccessResponse<GetPartnerQuizResponse> response = new SuccessResponse<>("Partner Quizes");
        ArrayList<Quiz> quizes = (ArrayList<Quiz>) onboardingService.getQuizes(UUID.fromString(partnerId));
        GetPartnerQuizResponse quizesResponse = new GetPartnerQuizResponse();
        quizesResponse.setQuizes(quizes);
        response.data = quizesResponse;
        return response;
    }
}

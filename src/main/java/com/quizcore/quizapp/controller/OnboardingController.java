package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Question;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.partner.GetPartnerQuizResponse;
import com.quizcore.quizapp.model.network.response.partner.GetProductPartnersResponse;
import com.quizcore.quizapp.model.network.response.partner.PartnerResponse;
import com.quizcore.quizapp.model.network.response.product.ProductResponse;
import com.quizcore.quizapp.model.network.response.quiz.GetQuizQuestionsResponse;
import com.quizcore.quizapp.model.network.response.user.RegistrationResponse;
import com.quizcore.quizapp.model.other.Validity;
import com.quizcore.quizapp.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

//TODO remove unused imports

@RestController
//@RequestMapping("api/v1/onboard")
@RequestMapping("quizcore/api/v1/onboard")
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
        return response;
    }

    @PostMapping("/product")
    public BaseResponse<ProductResponse> addProduct(@RequestBody AddProductRequest request)
    {

        Validity requestValidity = request.validate(request);
        if (!requestValidity.isValid()) {
            ErrorResponse<ProductResponse> response = new ErrorResponse<>(requestValidity.getMessage(), null);
            return response;
        }

            Product product  = new Product(request.getDescription(), request.getEmail(), request.getMobile(), request.getType(), request.getName());
            Product productKey = onboardingService.getProductByEmailOrPhone(product);

            if (productKey != null)
            {
                ErrorResponse<ProductResponse> response = new ErrorResponse<>("Product already exists", null);
                return response;
            }

            Product addedProduct = onboardingService.onboardProduct(product);
            SuccessResponse<ProductResponse> response = new SuccessResponse<>("Product registered successfully");
            ProductResponse productResponse = new ProductResponse();
            productResponse.setProductkey(addedProduct.getId());
            productResponse.setEmail(addedProduct.getEmail());
            productResponse.setMobile(addedProduct.getMobile());
            productResponse.setType(addedProduct.getType());
            productResponse.setTitle(addedProduct.getTitle());
            response.data = productResponse;
            return response;
    }

    @GetMapping("/product/{productKey}")
    public BaseResponse<ProductResponse> getProduct(@PathVariable("productKey") String productKey, @RequestHeader("token") String userToken)
    {
        if(productKey == null) {
            ErrorResponse<ProductResponse> response = new ErrorResponse<>("Please provide Productkey", null);
            return response;
        }
            Product product = new Product(UUID.fromString(productKey));
            Product addedProduct = onboardingService.getProductByKey(product);
            if(addedProduct != null)
            {
                SuccessResponse<ProductResponse> response = new SuccessResponse<>("Product found !!");
                ProductResponse productDetails = new ProductResponse();
                productDetails.setProductkey(addedProduct.getId());
                productDetails.setEmail(addedProduct.getEmail());
                productDetails.setMobile(addedProduct.getMobile());
                productDetails.setTitle(addedProduct.getTitle());
                productDetails.setType(addedProduct.getType());
                response.data = productDetails;
                return response;
            }
            else
            {
                ErrorResponse<ProductResponse> response = new ErrorResponse<>("No Product found !!", null);
                return response;
            }
    }

    @PostMapping("/product/{productId}/partner")
    public BaseResponse<PartnerResponse> addPartner(@PathVariable("productId") String productId,@RequestBody AddPartnerRequest request) {
        Validity requestValidity = request.validate(request);
        if (!requestValidity.isValid()) {
            ErrorResponse<PartnerResponse> response = new ErrorResponse<>(requestValidity.getMessage(), null);
            return response;
        }

        Partner partner = new Partner(UUID.fromString(productId),request.getEmail(), request.getDescription(), request.getMobile(), request.getName());
        Partner partnerKey = onboardingService.getPartnerByEmailOrMobile(partner);

        if(partnerKey != null)
        {
            ErrorResponse<PartnerResponse> response = new ErrorResponse<>("Partner already exists", null);
            return response;
        }

        Partner addedPartner = onboardingService.onboardPartner(partner);
        SuccessResponse<PartnerResponse> response = new SuccessResponse<>("Partner registered successfully");
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setPartnerkey(addedPartner.getId());
        partnerResponse.setEmail(addedPartner.getEmail());
        partnerResponse.setMobile(addedPartner.getMobile());
        partnerResponse.setTitle(addedPartner.getTitle());
        response.data = partnerResponse;
        return response;
    }

    //TODO remove this method
    @GetMapping("/product/{productId}/partner")
    public SuccessResponse<GetProductPartnersResponse> getProductPartners(@PathVariable("productId") String productId, @RequestHeader("token") String userToken){
        SuccessResponse<GetProductPartnersResponse> response = new SuccessResponse<>("Product Partners");
        ArrayList<Partner> partners = (ArrayList<Partner>) onboardingService.getPartners(UUID.fromString(productId));
        GetProductPartnersResponse partnersResponse = new GetProductPartnersResponse();
        partnersResponse.setPartners(partners);
        response.data = partnersResponse;
        return response;
    }

    //TODO remove this method
    @GetMapping("/product/{productId}/partner/{partnerId}")
    public SuccessResponse<GetPartnerQuizResponse> getPartnerQuiz(@PathVariable("productId") String productId, @PathVariable("partnerId") String partnerId,@RequestHeader("token") String userToken) {
        SuccessResponse<GetPartnerQuizResponse> response = new SuccessResponse<>("Partner Quizes");
        ArrayList<Quiz> quizes = (ArrayList<Quiz>) onboardingService.getQuizes(UUID.fromString(partnerId));
        GetPartnerQuizResponse quizesResponse = new GetPartnerQuizResponse();
        quizesResponse.setQuizes(quizes);
        response.data = quizesResponse;
        return response;
    }
}

package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.product.ProductMetrics;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.partner.GetPartnerQuizResponse;
import com.quizcore.quizapp.model.network.response.partner.GetProductPartnersResponse;
import com.quizcore.quizapp.model.network.response.product.ProductResponse;
import com.quizcore.quizapp.service.MediaStorageService;
import com.quizcore.quizapp.service.OnboardingService;
import com.quizcore.quizapp.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${base.endpoint}/api/v1/product")
public class ProductController {

    @Autowired
    OnboardingService onboardingService;

    @Autowired
    MediaStorageService mediaStorageService;

    @Autowired
    ProductService productService;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/{productKey}")
    public BaseResponse<ProductResponse> getProduct(@PathParam("productKey") String productKey) {
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

    @GetMapping("/{productId}/metrics")
    public BaseResponse<ProductMetrics> getProductMetrics(@PathVariable("productId") String productId){
        ProductMetrics productMetrics = productService.getMetrics(UUID.fromString(productId));
        SuccessResponse<ProductMetrics> response = new SuccessResponse<ProductMetrics>("Product Metrics");
        response.data = productMetrics;
        return response;
    }
}

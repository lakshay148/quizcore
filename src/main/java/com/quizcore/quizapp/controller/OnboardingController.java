package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.product.ProductResponse;
import com.quizcore.quizapp.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.UUID;

@RestController
@RequestMapping("quizcore/api/v1/onboard")
public class OnboardingController {

    @Autowired
    OnboardingService onboardingService;

    @GetMapping("/healthcheck")
    public SuccessResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @PostMapping("/product")
    public BaseResponse<ProductResponse> addProduct(@RequestBody AddProductRequest request)
    {
            Product product  = new Product(request.getDescription(), request.getEmail(), request.getMobile(), request.getType(), request.getName());
            Product productkey = onboardingService.getProductByEmailOrPhone(product);

            if (productkey != null)
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
    public BaseResponse<ProductResponse> getProduct(@PathVariable("productKey") String productKey)
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

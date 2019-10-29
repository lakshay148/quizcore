package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.partner.PartnerResponse;
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
    public BaseResponse<PartnerResponse> addPartner(@PathVariable("productId") String productId,@RequestBody AddPartnerRequest request)
    {
        Partner partner = new Partner(UUID.fromString(productId),request.getEmail(), request.getDescription(), request.getMobile(), request.getName());
        Partner partnerKey = onboardingService.getPartnerByEmailOrMobile(partner);

        if(partnerKey != null)
        {
            ErrorResponse<PartnerResponse> response = new ErrorResponse<>("Partner already exists", null);
            return response;
        }

        Partner addedPartner = onboardingService.onboardPartner(partner);
        SuccessResponse<PartnerResponse> response = new SuccessResponse<>("Product registered successfully");
        PartnerResponse partnerResponse = new PartnerResponse();
        partnerResponse.setPartnerkey(addedPartner.getId());
        partnerResponse.setEmail(addedPartner.getEmail());
        partnerResponse.setMobile(addedPartner.getMobile());
        partnerResponse.setTitle(addedPartner.getTitle());
        response.data = partnerResponse;
        return response;
    }

    @GetMapping("/product/{productId}/partner")
    public BaseResponse<PartnerResponse> getPartners(@PathVariable("productId") String productId) {
        if(productId == null) {
            ErrorResponse<PartnerResponse> response = new ErrorResponse<>("Please provide ProductId", null);
            return response;
        }
//           Product product = new Product(UUID.fromString(productId));
//           Partner List<String> addedPartners = onboardingService.getPartnersByProduct(product);
//           if(addedPartners[0]    != null) {
//               SuccessResponse<ProductResponse> response = new SuccessResponse<>("List Of Partners found !!");
//               PartnerResponse partnersDetails = new PartnerResponse();
//               partnersDetails.setEmail(addedPartners.getEmail());
//               partnersDetails.setMobile(addedPartners.getMobile());
//               partnersDetails.setTitle(addedPartners.getTitle());
//               partnersDetails.setDescription(addedPartners.getDescription());
//               response.data = partnersDetails;
//               return response;
//           }
//           else
//           {
//               ErrorResponse<ProductResponse> response = new ErrorResponse<>("No Product found !!", null);
//               return response;
//           }
        return null;
    }

    @GetMapping("/product/{productId}/partner/{partnerId}")
    public SuccessResponse<Object> getPartnerDetails(@PathParam("productId") String productId, @PathParam("partnerId") String partnerId) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }
}

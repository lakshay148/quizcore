package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.MediaContent;
import com.quizcore.quizapp.model.entity.Partner;
import com.quizcore.quizapp.model.entity.Product;
import com.quizcore.quizapp.model.entity.Quiz;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.partner.GetPartnerQuizResponse;
import com.quizcore.quizapp.model.network.response.partner.GetProductPartnersResponse;
import com.quizcore.quizapp.model.network.response.product.ProductResponse;
import com.quizcore.quizapp.service.OnboardingService;
import org.springframework.beans.factory.annotation.Autowired;
import com.quizcore.quizapp.model.network.response.product.UploadMediaResponse;
import com.quizcore.quizapp.service.VideoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;
import java.util.ArrayList;
import java.util.UUID;

@RestController
//@RequestMapping("api/v1/product")
@RequestMapping("qizcore/api/v1/product")
public class ProductController {

    @Autowired
    OnboardingService onboardingService;

    @Autowired
    VideoStorageService videoStorageService;

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


    @PostMapping("/uploadVideo")
    public BaseResponse<UploadMediaResponse> uploadVideo(@RequestParam("file") MultipartFile file, @RequestParam("partnerId") String partnerId, @RequestParam("quizId") String quizId,@RequestHeader("token") String userToken) throws IOException {

        File saveFile = new File("C:/Users/MeghaMiglani/Videos/" + file.getOriginalFilename());

        if (saveFile == null)
        {
            ErrorResponse<UploadMediaResponse> response = new ErrorResponse<>("Please upload video", null);
            return response;
        }
        try {
            FileOutputStream fout = new FileOutputStream(saveFile);
            fout.write(file.getBytes());
        } catch (Exception e) {
            System.out.println(e.getLocalizedMessage());
        }

        MediaContent mediaContent = new MediaContent();
        SuccessResponse<UploadMediaResponse> response = new SuccessResponse<>("Video uploaded");
        mediaContent.setQuizId(quizId);
        mediaContent.setPartnerId(partnerId);
        mediaContent.setUserId(UUID.fromString(userToken));
        mediaContent.setPath(saveFile.getPath());

        UUID videosaved = videoStorageService.videoUpload(mediaContent);

        UploadMediaResponse mediaResponse = new UploadMediaResponse();
        mediaResponse.setId(videosaved.toString());
        mediaResponse.setPath(saveFile.getPath());
        response.data = mediaResponse;
        return response;
    }
}

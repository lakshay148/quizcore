package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.MediaContent;
import com.quizcore.quizapp.model.network.request.onboarding.AddPartnerRequest;
import com.quizcore.quizapp.model.network.request.onboarding.AddProductRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.product.UploadMediaResponse;
import com.quizcore.quizapp.service.QuizService;
import com.quizcore.quizapp.service.base.VideoStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.websocket.server.PathParam;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/quizcore/api/v1/product")
public class ProductController {

    @Autowired
    VideoStorageService videoStorageService;

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

    @PostMapping
    public SuccessResponse<Object> addProduct(@RequestBody AddProductRequest request) {
        System.out.println("request " + request.toString());
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @PostMapping("/{productId}/partner")
    public SuccessResponse<Object> addPartner(@RequestBody AddPartnerRequest request) {
        System.out.println("request " + request.toString());
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/{productId}/partner")
    public SuccessResponse<Object> getPartners(@PathParam("productId") String productId) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
        return response;
    }

    @GetMapping("/{productId}/partner/{partnerId}")
    public SuccessResponse<Object> getPartnerDetails(@PathParam("productId") String productId, @PathParam("partnerId") String partnerId) {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesone");
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

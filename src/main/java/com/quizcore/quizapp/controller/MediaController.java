package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.MediaContent;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.product.UploadMediaResponse;
import com.quizcore.quizapp.service.MediaStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("${base.endpoint}/api/v1/media")
public class MediaController {

    @Autowired
    MediaStorageService mediaStorageService;

    @Value("${videoResourcesPath}")
    String videoResourcePath;

    @PostMapping("/uploadVideo")
    public BaseResponse<UploadMediaResponse> uploadVideo(@RequestParam("file") MultipartFile file, @RequestHeader("token") String userToken) throws IOException {

        File saveFile = new File(videoResourcePath + file.getOriginalFilename().replace(" ","_"));

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
        mediaContent.setUserId(UUID.fromString(userToken));
        mediaContent.setPath(saveFile.getPath());
        mediaContent.setMediaType("VIDEO");

        UUID videosaved = mediaStorageService.videoUpload(mediaContent);

        UploadMediaResponse mediaResponse = new UploadMediaResponse();
        mediaResponse.setId(videosaved.toString());
        mediaResponse.setPath(saveFile.getPath());
        response.data = mediaResponse;
        return response;
    }
}

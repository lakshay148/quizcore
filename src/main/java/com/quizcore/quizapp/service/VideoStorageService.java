package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.MediaContent;
import com.quizcore.quizapp.model.repository.MediaContentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoStorageService {

    @Autowired
    MediaContentRepository mediaContentRepository;

    public UUID videoUpload(MediaContent video) {
        MediaContent videoSaved = mediaContentRepository.save(video);
        return videoSaved.id;
    }

}

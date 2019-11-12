package com.quizcore.quizapp.service.base;

import com.quizcore.quizapp.model.entity.MediaContent;
import com.quizcore.quizapp.model.repository.DatabaseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class VideoStorageService {

        @Autowired
        DatabaseRepository databaseRepository;

        public UUID videoUpload(MediaContent video) {
            MediaContent videoSaved = databaseRepository.save(video);
            return videoSaved.id;
        }

    }

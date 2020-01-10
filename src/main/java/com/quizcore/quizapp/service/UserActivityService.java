package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.UserActivityLog;
import com.quizcore.quizapp.model.repository.UserActivityRepository;
import com.quizcore.quizapp.util.UserAction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class UserActivityService {

    @Autowired
    UserActivityRepository userActivityRepository;

    public void saveUserAction(UUID userId, UserAction action){
        UserActivityLog userActivityLog = new UserActivityLog(userId, action.name());
        userActivityRepository.save(userActivityLog);
    }

    public void saveUserQuizAction(UUID userId, UUID quizId, UserAction userAction){
        UserActivityLog userActivityLog = new UserActivityLog(userId, quizId,userAction.name());
        userActivityRepository.save(userActivityLog);
    }

    public List<UserActivityLog> getUserActions(UUID userId){
        List<UserActivityLog> userLogs = userActivityRepository.findAllByUserId(userId);
        return userLogs;
    }

    public List<UserActivityLog> getUserActions(UUID userId, UUID quizId){
        return userActivityRepository.findAllByUserIdAndQuizId(userId, quizId);
    }

}

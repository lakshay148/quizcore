package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Result;
import com.quizcore.quizapp.model.other.UserQuizResult;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ResultRepository extends CrudRepository<Result, UUID> {
    Result findByQuizIdAndUserId(UUID quizId, UUID userId);

    @Query("Select new com.quizcore.quizapp.model.other.UserQuizResult(b.result,b.score,b.quizId,a.description,a.title) from Quiz a, Result b where a.id=b.quizId and b.userId =?1")
    public List<UserQuizResult> getUserQuizResults(UUID userId);
}
//Select new com.quizcore.quizapp.model.other.UserQuizResult() from Quiz a, Result b where a.id=b.quiz_id and b.user_id =?1
package com.quizcore.quizapp.model.repository;

import com.quizcore.quizapp.model.entity.Subject;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface SubjectRepository  extends CrudRepository<Subject, UUID> {

     Subject findBySubjectName(String subjectName);
     List<Subject> findByProductId(UUID productId);

}

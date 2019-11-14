package com.quizcore.quizapp.service;

import com.quizcore.quizapp.model.entity.Category;
import com.quizcore.quizapp.model.entity.Subject;
import com.quizcore.quizapp.model.repository.CategoryRepository;
import com.quizcore.quizapp.model.repository.SubjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public class MasterDataService {

    @Autowired
    SubjectRepository subjectRepository;

    @Autowired
    CategoryRepository categoryRepository;

    public Subject getSubjectIdBySubjectName(Subject subject)
    {
        Subject subjectId = subjectRepository.findBySubjectName(subject.getSubjectName());
        return subjectId;
    }

    public Subject addSubject(Subject subject)
    {
        Subject addedsubject = subjectRepository.save(subject);
        return addedsubject;
    }

    public List<Subject> getSubjectByProductId(UUID productId)
    {
        List<Subject> subjects = (List<Subject>) subjectRepository.findByProductId(productId);
        return subjects;
    }

    public Category getCategoryIdByCategoryName(Category category)
    {
        Category categoryId = categoryRepository.findByCategoryName(category.getCategoryName());
        return categoryId;
    }

    public Category addCategory(Category category)
    {
        Category addedcategory = categoryRepository.save(category);
        return addedcategory;
    }

    public List<Category> getCategoryByProductId(UUID productId)
    {
        List<Category> categories = (List<Category>) categoryRepository.findByProductId(productId);
        return categories;
    }

}

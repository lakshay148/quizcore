package com.quizcore.quizapp.controller;

import com.quizcore.quizapp.model.entity.Category;
import com.quizcore.quizapp.model.entity.Subject;
import com.quizcore.quizapp.model.network.request.master.AddCategoryRequest;
import com.quizcore.quizapp.model.network.request.master.AddSubjectRequest;
import com.quizcore.quizapp.model.network.response.BaseResponse;
import com.quizcore.quizapp.model.network.response.ErrorResponse;
import com.quizcore.quizapp.model.network.response.SuccessResponse;
import com.quizcore.quizapp.model.network.response.master.CategoriesResponse;
import com.quizcore.quizapp.model.network.response.master.CategoryResponse;
import com.quizcore.quizapp.model.network.response.master.SubjectResponse;
import com.quizcore.quizapp.model.network.response.master.SubjectsResponse;
import com.quizcore.quizapp.service.MasterDataService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.UUID;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("${base.endpoint}/api/v1/masterdata")
public class MasterDataController {


    @Autowired
    MasterDataService masterDataService;

    @GetMapping("/healthcheck")
    public BaseResponse<Object> checkHealth() {
        SuccessResponse<Object> response = new SuccessResponse<>("It works awesome");
        return response;
    }

    @PostMapping("/addSubject")
    public BaseResponse<SubjectResponse> addSubject(@RequestBody AddSubjectRequest request)
    {
        Subject subject = new Subject(UUID.fromString(request.getProductId()), request.getSubjectName());
        Subject subjectId = masterDataService.getSubjectIdBySubjectName(subject);

        if (subjectId != null)
        {
            ErrorResponse<SubjectResponse> response = new ErrorResponse<>("Subject already exists", null) ;
            return response;
        }

        Subject addedSubject = masterDataService.addSubject(subject);
        SuccessResponse<SubjectResponse> response = new SuccessResponse<>("Subject added successfully");
        SubjectResponse subjectresponse = new SubjectResponse();
        subjectresponse.setSubjectId(addedSubject.getId());
        subjectresponse.setSubjectname(addedSubject.getSubjectName());
        subjectresponse.setProductkey(addedSubject.getProductId());
        response.data = subjectresponse;
        return response;
    }

    @GetMapping("/subject/{productId}")
    public BaseResponse<SubjectsResponse> getSubject(@PathVariable("productId") String productId, @RequestHeader("token") String userToken)
    {
        if (productId == null)
        {
            ErrorResponse<SubjectsResponse> response = new ErrorResponse<>("Please provide productId", null);
            return response;
        }
        ArrayList<Subject> subjects = (ArrayList<Subject>) masterDataService.getSubjectByProductId(UUID.fromString(productId));
        if(subjects != null)
        {
            SuccessResponse<SubjectsResponse> response = new SuccessResponse<>("Subjects found !!");
            SubjectsResponse subjectDetails = new SubjectsResponse();
            subjectDetails.setSubjects(subjects);
            response.data = subjectDetails;
            return response;
        }
        else
        {
            ErrorResponse<SubjectsResponse> response = new ErrorResponse<>("No Subject Found", null);
            return response;
        }

    }


    @PostMapping("/addCategory")
    public BaseResponse<CategoryResponse> addCategory(@RequestBody AddCategoryRequest request)
    {
        Category category = new Category(UUID.fromString(request.getProductId()), request.getCategoryName());
        Category categoryId = masterDataService.getCategoryIdByCategoryName(category);

        if (categoryId != null)
        {
            ErrorResponse<CategoryResponse> response = new ErrorResponse<>("Category already exists", null) ;
            return response;
        }

        Category addedCategory = masterDataService.addCategory(category);
        SuccessResponse<CategoryResponse> response = new SuccessResponse<>("Category added successfully");
        CategoryResponse categoryresponse = new CategoryResponse();
        categoryresponse.setCategoryId(addedCategory.getId());
        categoryresponse.setCategoryname(addedCategory.getCategoryName());
        categoryresponse.setProductkey(addedCategory.getProductId());
        response.data = categoryresponse;
        return response;
    }


    @GetMapping("/category/{productId}")
    public BaseResponse<CategoriesResponse> getCategory(@PathVariable("productId") String productId, @RequestHeader("token") String userToken)
    {
        if (productId == null)
        {
            ErrorResponse<CategoriesResponse> response = new ErrorResponse<>("Please provide productId", null);
            return response;
        }
        ArrayList<Category> categories = (ArrayList<Category>) masterDataService.getCategoryByProductId(UUID.fromString(productId));
        if(categories != null)
        {
            SuccessResponse<CategoriesResponse> response = new SuccessResponse<>("categories found !!");
            CategoriesResponse categoriesDetails = new CategoriesResponse();
            categoriesDetails.setCategories(categories);
            response.data = categoriesDetails;
            return response;
        }
        else
        {
            ErrorResponse<CategoriesResponse> response = new ErrorResponse<>("No Category Found", null);
            return response;
        }

    }




    //TODO add subject,level,category,type of questions and quiz (MCQ,Audio)  in master data
}

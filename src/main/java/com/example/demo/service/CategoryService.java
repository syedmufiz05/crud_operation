package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface CategoryService {
    CategoryDto addCategory(String ctgName,String authToken) throws JsonProcessingException;

    ResponseEntity getCategory(Integer categoryId);

    List<CategoryDto> getAllCategory();

    ResponseEntity editCategory(Integer categoryId, String categoryName) throws JsonProcessingException;

    String deleteCategory(Integer categoryId);
}

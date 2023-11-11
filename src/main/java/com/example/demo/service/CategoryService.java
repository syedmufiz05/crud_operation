package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto, String authToken) throws JsonProcessingException;

    ResponseEntity getCategory(Integer categoryId);

    ResponseEntity editCategory(Integer categoryId, String categoryName) throws JsonProcessingException;

    String deleteCategory(Integer categoryId);
}

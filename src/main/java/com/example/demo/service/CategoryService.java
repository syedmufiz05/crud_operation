package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.http.ResponseEntity;

public interface CategoryService {
    CategoryDto addCategory(CategoryDto categoryDto, String authToken) throws JsonProcessingException;

    ResponseEntity editCategory(Integer categoryId, CategoryDto categoryDto) throws JsonProcessingException;

    String deleteCategory(Integer categoryId);
}

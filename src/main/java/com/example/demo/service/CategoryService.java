package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.fasterxml.jackson.core.JsonProcessingException;

public interface CategoryService {
    String addCategory(CategoryDto categoryDto, String authToken) throws JsonProcessingException;
    String editCategory(Integer categoryId,CategoryDto categoryDto);
    String deleteCategory(Integer categoryId);
}

package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Category;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Override
    public String addCategory(CategoryDto categoryDto, String authToken) throws JsonProcessingException {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setReqPayload(convertEntityToJson(categoryDto));
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        Category category = new Category();
        category.setName(convertEntityToJson(categoryDto));
        category.setAccessLogs(accessLogs);
        categoryRepository.save(category);
        accessLogsRepository.save(accessLogs);
        return "Category added successfully...";
    }

    @Override
    public String editCategory(Integer categoryId, CategoryDto categoryDto) {
        return null;
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        return null;
    }

    private String convertEntityToJson(CategoryDto categoryDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(categoryDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

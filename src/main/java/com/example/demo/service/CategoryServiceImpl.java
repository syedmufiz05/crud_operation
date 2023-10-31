package com.example.demo.service;

import com.example.demo.dto.CategoryDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Category;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.CategoryRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Override
    public CategoryDto addCategory(CategoryDto categoryDto, String authToken) throws JsonProcessingException {
        Optional<Category> category = categoryRepository.findById(categoryDto.getCategoryId());
        if (category.isPresent()) {
            Category categoryDb = category.get();
            categoryDto.setCategoryId(categoryDb.getId());
            categoryDto.setName(categoryDb.getName().replaceAll("\\\\", ""));
            Optional<AccessLogs> accessLogs = accessLogsRepository.findByIdAccessLogsId(categoryDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogs.isPresent()) {
                AccessLogs accessLogsDb = accessLogs.get();
                accessLogsDb.setUserId(1212);
                accessLogsDb.setReqPayload(convertEntityToJson(categoryDto));
                accessLogsDb.setResponsePayload("");
                accessLogsDb.setAuthToken(authToken);
                accessLogsRepository.save(accessLogsDb);
                categoryDb.setAccessLogs(accessLogsDb);
            }
            categoryRepository.save(categoryDb);
            return new CategoryDto(categoryDto.getCategoryId(), categoryDto.getName());
        }
        Category categoryNew = new Category();
        categoryNew.setName(convertEntityToJson(categoryDto));
        categoryRepository.save(categoryNew);
        categoryDto.setCategoryId(categoryNew.getId());
        categoryDto.setName(categoryNew.getName());
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setReqPayload(convertEntityToJson(categoryDto));
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        categoryNew.setAccessLogs(accessLogs);
        return new CategoryDto(categoryDto.getCategoryId(), categoryDto.getName());
    }

    @Override
    public ResponseEntity editCategory(Integer categoryId, CategoryDto categoryDto) throws JsonProcessingException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category categoryDb = category.get();
            categoryDb.setName(convertEntityToJson(categoryDto));
            categoryRepository.save(categoryDb);
            Optional<AccessLogs> accessLogs = accessLogsRepository.findByIdAccessLogsId(categoryDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogs.isPresent()) {
                AccessLogs accessLogsDb = accessLogs.get();
                accessLogsDb.setAccessDateTime(new Date());
                accessLogsDb.setReqPayload(convertEntityToJson(categoryDto));
                accessLogsRepository.save(accessLogsDb);
            }
            CategoryDto categoryDtoNew = new CategoryDto(categoryDto.getCategoryId(), categoryDto.getName());
            return new ResponseEntity<>(categoryDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Category Id does n't exists"));
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        categoryRepository.findById(categoryId);
        return "Deleted successfully...";
    }

    private String convertEntityToJson(CategoryDto categoryDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(categoryDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

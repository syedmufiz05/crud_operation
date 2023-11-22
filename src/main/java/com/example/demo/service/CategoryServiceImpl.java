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
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Override
    public CategoryDto addCategory(String ctgName, String authToken) throws JsonProcessingException {
        Category categoryDb = new Category();
        categoryDb.setName(ctgName);
        categoryRepository.save(categoryDb);
        CategoryDto categoryDto = new CategoryDto();
        categoryDto.setCategoryId(categoryDb.getId());
        categoryDto.setName(ctgName);
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setReqPayload(convertEntityToJson(categoryDto));
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        categoryDb.setAccessLogs(accessLogs);
        categoryRepository.save(categoryDb);
        return new CategoryDto(categoryDto.getCategoryId(), categoryDto.getName());
    }

    @Override
    public ResponseEntity getCategory(Integer categoryId) {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category categoryDb = category.get();
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setCategoryId(categoryDb.getId());
            categoryDto.setName(categoryDb.getName().replace("\"", ""));
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Category Id does n't exist"));
    }

    @Override
    public List<CategoryDto> getAllCategory() {
        return categoryRepository.fetchAllCategory();
    }

    @Override
    public ResponseEntity editCategory(Integer categoryId, String categoryName) throws JsonProcessingException {
        Optional<Category> category = categoryRepository.findById(categoryId);
        if (category.isPresent()) {
            Category categoryDb = category.get();
            categoryDb.setName(categoryName);
            CategoryDto categoryDto = new CategoryDto(categoryId, categoryName);
            Optional<AccessLogs> accessLogs = accessLogsRepository.findById(categoryDb.getAccessLogs() != null ? categoryDb.getAccessLogs().getId() : 0);
            if (accessLogs.isPresent()) {
                AccessLogs accessLogsDb = accessLogs.get();
                accessLogsDb.setAccessDateTime(new Date());
                accessLogsDb.setReqPayload(convertEntityToJson(categoryDto));
                accessLogsRepository.save(accessLogsDb);
                return new ResponseEntity<>(categoryDto, HttpStatus.OK);
            }
            AccessLogs accessLogsNew = new AccessLogs();
            accessLogsNew.setAccessDateTime(new Date());
            accessLogsNew.setReqPayload(convertEntityToJson(categoryDto));
            accessLogsRepository.save(accessLogsNew);
            categoryDb.setAccessLogs(accessLogsNew);
            categoryRepository.save(categoryDb);
            return new ResponseEntity<>(categoryDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Category Id does n't exist"));
    }

    @Override
    public String deleteCategory(Integer categoryId) {
        categoryRepository.deleteById(categoryId);
        return "Deleted successfully...";
    }

    private String convertEntityToJson(CategoryDto categoryDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(categoryDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }

    private String fetchValueFromJsonData(String name) {
        String value = name.replaceAll("\\\\", "");
        String catName = value.substring(1, value.length() - 1).replaceAll("\\s", "");
        JSONObject jsonObject = new JSONObject(catName);
        return jsonObject.get("name").toString();
    }
}

package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/detail/add", method = RequestMethod.POST)
    public CategoryDto addCategory(@RequestBody CategoryDto categoryDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return categoryService.addCategory(categoryDto, authToken);
    }

    @RequestMapping(value = "/detail/edit/{category_id}", method = RequestMethod.PUT)
    public ResponseEntity editCategory(@PathVariable("category_id") Integer categoryId, @RequestBody CategoryDto categoryDto) throws JsonProcessingException {
        return categoryService.editCategory(categoryId, categoryDto);
    }

    @RequestMapping(value = "/detail/delete/{category_id}", method = RequestMethod.DELETE)
    public String deleteCategory(@PathVariable("category_id") Integer categoryId) {
        return categoryService.deleteCategory(categoryId);
    }
}

package com.example.demo.controller;

import com.example.demo.dto.CategoryDto;
import com.example.demo.service.CategoryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @RequestMapping(value = "/detail/add", method = RequestMethod.POST)
    public String addCategory(@RequestBody CategoryDto categoryDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return categoryService.addCategory(categoryDto, authToken);
    }

}

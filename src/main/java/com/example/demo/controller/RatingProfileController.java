package com.example.demo.controller;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.service.RatingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rating/profile")
public class RatingProfileController {
    @Autowired
    private RatingProfileService ratingProfileService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RatingProfileDto createRatingProfile(@RequestBody RatingProfileDto ratingProfileDto, HttpServletRequest httpServletRequest) {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return ratingProfileService.createRatingProfile(ratingProfileDto, authToken);
    }
}

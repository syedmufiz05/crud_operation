package com.example.demo.controller;

import com.example.demo.dto.RatingPlanDto;
import com.example.demo.service.RatingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rating/plan")
public class RatingPlanController {
    @Autowired
    private RatingPlanService ratingPlanService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RatingPlanDto createRatingPlan(@RequestBody RatingPlanDto ratingPlanDto, HttpServletRequest httpServletRequest) {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return ratingPlanService.createRatingPlan(ratingPlanDto, authToken);
    }
}

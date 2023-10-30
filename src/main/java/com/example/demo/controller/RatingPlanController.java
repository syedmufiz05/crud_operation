package com.example.demo.controller;

import com.example.demo.dto.RatingPlanDto;
import com.example.demo.model.RatingPlan;
import com.example.demo.service.RatingPlanService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

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

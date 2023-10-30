package com.example.demo.service;

import com.example.demo.dto.RatingPlanDto;

public interface RatingPlanService {
    RatingPlanDto createRatingPlan(RatingPlanDto ratingPlanDto, String authToken);
    }


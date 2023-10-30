package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.model.RatingProfile;

public interface RatingProfileService {
    RatingProfileDto createRatingProfile(RatingProfileDto ratingProfileDto,String authToken);
    String editRatingProfile();
    String deleteRatingProfile();
}

package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import org.springframework.http.ResponseEntity;

public interface RatingProfileService {
    RatingProfileDto createRatingProfile(RatingProfileDto ratingProfileDto, String authToken);

    ResponseEntity editRatingProfile(Integer ratingProfileId, String callingParty);

    String deleteRatingProfile(Integer ratingProfileId);
}

package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.dto.RatingProfileVoucherDto;
import com.example.demo.model.RatingProfile;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface RatingProfileService {
    RatingProfileDto createRatingProfile(RatingProfileDto ratingProfileDto, String authToken);

    ResponseEntity createRatingProfileVoucher(RatingProfileVoucherDto ratingProfileVoucherDto);

    ResponseEntity getRatingProfile(Integer ratingProfileId);

    List<RatingProfileDto> getAllRatingProfile();

    ResponseEntity editRatingProfile(Integer ratingProfileId, String callingParty);

    String deleteRatingProfile(Integer ratingProfileId);
}

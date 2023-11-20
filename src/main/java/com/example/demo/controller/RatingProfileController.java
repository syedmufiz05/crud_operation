package com.example.demo.controller;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.service.RatingProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rating/profile")
@CrossOrigin("http://localhost:5173/")
public class RatingProfileController {
    @Autowired
    private RatingProfileService ratingProfileService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    public RatingProfileDto createRatingProfile(@RequestBody RatingProfileDto ratingProfileDto, HttpServletRequest httpServletRequest) {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return ratingProfileService.createRatingProfile(ratingProfileDto, authToken);
    }

    @RequestMapping(value = "/get/{rating_profile_id}", method = RequestMethod.GET)
    public ResponseEntity<RatingProfileDto> getRatingProfile(@PathVariable("rating_profile_id") Integer ratingProfileId) {
        return ratingProfileService.getRatingProfile(ratingProfileId);
    }

    @RequestMapping(value = "/edit/{rating_profile_id}", method = RequestMethod.PUT)
    public ResponseEntity<RatingProfileDto> editRatingProfile(@PathVariable("rating_profile_id") Integer ratingProfileId, @RequestParam("calling_party") String callingParty) {
        return ratingProfileService.editRatingProfile(ratingProfileId, callingParty);
    }

    @RequestMapping(value = "/delete/{rating_profile_id}", method = RequestMethod.DELETE)
    public String deleteRatingProfile(@PathVariable("rating_profile_id") Integer ratingProfileId) {
        return ratingProfileService.deleteRatingProfile(ratingProfileId);
    }
}

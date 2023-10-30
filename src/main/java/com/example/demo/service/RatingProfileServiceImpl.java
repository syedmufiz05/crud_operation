package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.model.Category;
import com.example.demo.model.RatingPlan;
import com.example.demo.model.RatingProfile;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RatingPlanRepository;
import com.example.demo.repository.RatingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingProfileServiceImpl implements RatingProfileService {
    @Autowired
    private RatingProfileRepository ratingProfileRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RatingPlanRepository ratingPlanRepository;

    @Override
    public RatingProfileDto createRatingProfile(RatingProfileDto ratingProfileDto, String authToken) {
        Optional<Category> category = categoryRepository.findById(ratingProfileDto.getCategoryId());
        Optional<RatingPlan> ratingPlan = ratingPlanRepository.findById(ratingProfileDto.getRatingPlanId());
        if (category.isPresent() && ratingPlan.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfile.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getId(), ratingProfile.getCallingParty(), ratingPlanDb.getId());
        } else if (category.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = new RatingPlan();
            ratingPlanRepository.save(ratingPlanDb);
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfile.getCallingParty() : "");
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getId(), ratingProfile.getCallingParty(), ratingPlanDb.getId());
        } else if (ratingPlan.isPresent()) {
            Category categoryDb = new Category();
            categoryRepository.save(categoryDb);
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfile.getCallingParty() : "");
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getId(), ratingProfile.getCallingParty(), ratingPlanDb.getId());
        }
        Category categoryNew = new Category();
        categoryRepository.save(categoryNew);
        RatingPlan ratingPlanNew = new RatingPlan();
        ratingPlanRepository.save(ratingPlanNew);
        RatingProfile ratingProfile = new RatingProfile();
        ratingProfile.setId(1212);
        ratingProfile.setCategory(categoryNew);
        ratingProfile.setRatingPlan(ratingPlanNew);
        return new RatingProfileDto(ratingProfile.getId(), categoryNew.getId(), ratingProfile.getCallingParty(), ratingPlanNew.getId());
    }

    @Override
    public String editRatingProfile() {
        return null;
    }

    @Override
    public String deleteRatingProfile() {
        return null;
    }
}

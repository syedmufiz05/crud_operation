package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.Category;
import com.example.demo.model.RatingPlan;
import com.example.demo.model.RatingProfile;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RatingPlanRepository;
import com.example.demo.repository.RatingProfileRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
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
        Optional<Category> category = categoryRepository.findByName(ratingProfileDto.getCategoryName());
        Optional<RatingPlan> ratingPlan = ratingPlanRepository.findById(ratingProfileDto.getRatingPlanId());
        if (category.isPresent() && ratingPlan.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        } else if (category.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = new RatingPlan();
            ratingPlanRepository.save(ratingPlanDb);
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        } else if (ratingPlan.isPresent()) {
            Category categoryDb = new Category();
            categoryRepository.save(categoryDb);
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        }
        Category categoryNew = new Category();
        categoryNew.setName(ratingProfileDto.getCategoryName());
        categoryRepository.save(categoryNew);
        RatingPlan ratingPlanNew = new RatingPlan();
        ratingPlanRepository.save(ratingPlanNew);
        RatingProfile ratingProfile = new RatingProfile();
        ratingProfile.setId(1212);
        ratingProfile.setCallingParty(ratingProfileDto.getCallingParty());
        ratingProfile.setCategory(categoryNew);
        ratingProfile.setRatingPlan(ratingPlanNew);
        ratingProfileRepository.save(ratingProfile);
        return new RatingProfileDto(ratingProfile.getId(), categoryNew.getName(), ratingProfile.getCallingParty(), ratingPlanNew.getRatingPlanId());
    }

    @Override
    public ResponseEntity getRatingProfile(Integer ratingProfileId) {
        Optional<RatingProfile> ratingProfile = ratingProfileRepository.findById(ratingProfileId);
        if (ratingProfile.isPresent()) {
            RatingProfile ratingProfileDb = ratingProfile.get();
            RatingProfileDto ratingProfileDto = new RatingProfileDto();
            ratingProfileDto.setRatingProfileId(ratingProfileDb.getId());
            ratingProfileDto.setCategoryName(ratingProfileDb.getCategory().getName());
            ratingProfileDto.setCallingParty(ratingProfileDb.getCallingParty());
            ratingProfileDto.setRatingPlanId(ratingProfileDb.getRatingPlan().getRatingPlanId());
            return new ResponseEntity<>(ratingProfileDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Rating Profile Id does n't exist"));
    }

    @Override
    public List<RatingProfileDto> getAllRatingProfile() {
        return ratingProfileRepository.fetchAll();
    }

    @Override
    public ResponseEntity editRatingProfile(Integer ratingProfileId, String callingParty) {
        Optional<RatingProfile> ratingProfile = ratingProfileRepository.findById(ratingProfileId);
        if (ratingProfile.isPresent()) {
            RatingProfile ratingProfileDb = ratingProfile.get();
            ratingProfileDb.setCallingParty(!callingParty.isEmpty() ? callingParty : ratingProfileDb.getCallingParty());
            ratingProfileRepository.save(ratingProfileDb);
            RatingProfileDto ratingProfileDto = new RatingProfileDto(ratingProfileDb.getId(), ratingProfileDb.getCategory().getName(), ratingProfileDb.getCallingParty(), ratingProfileDb.getRatingPlan().getRatingPlanId());
            return new ResponseEntity<>(ratingProfileDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Rating Profile Id does n't exist"));
    }

    @Override
    public String deleteRatingProfile(Integer ratingProfileId) {
        ratingProfileRepository.deleteById(ratingProfileId);
        return "Deleted successfully";
    }
}

package com.example.demo.repository;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.model.RatingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingProfileRepository extends JpaRepository<RatingProfile, Integer> {
    @Query("select new com.example.demo.dto.RatingProfileDto(ratingProfile.id,ratingProfile.category.name,ratingProfile.callingParty,ratingProfile.ratingPlan.ratingPlanId) from RatingProfile ratingProfile")
    List<RatingProfileDto> fetchAll();
}

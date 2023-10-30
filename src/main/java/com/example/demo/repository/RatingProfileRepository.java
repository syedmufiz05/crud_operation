package com.example.demo.repository;

import com.example.demo.model.RatingProfile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingProfileRepository extends JpaRepository<RatingProfile, Integer> {
}

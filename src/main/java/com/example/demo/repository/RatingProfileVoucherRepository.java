package com.example.demo.repository;

import com.example.demo.model.RatingProfileVoucher;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingProfileVoucherRepository extends JpaRepository<RatingProfileVoucher, Integer> {
}

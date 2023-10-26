package com.example.demo.repository;

import com.example.demo.model.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatesRepository extends JpaRepository<Rates, Integer> {
}

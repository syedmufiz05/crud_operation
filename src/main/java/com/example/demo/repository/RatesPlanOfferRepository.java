package com.example.demo.repository;

import com.example.demo.dto.RatesPlanOfferDto;
import com.example.demo.model.RatesPlanOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatesPlanOfferRepository extends JpaRepository<RatesPlanOffer, Integer> {
    @Query("select new com.example.demo.dto.RatesPlanOfferDto(ratesPlanOffer.id,ratesPlanOffer.name,ratesPlanOffer.period,ratesPlanOffer.description,ratesPlanOffer.active) from RatesPlanOffer ratesPlanOffer")
    List<RatesPlanOfferDto> fetchAllRatesPlan();
}

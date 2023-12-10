package com.example.demo.repository;

import com.example.demo.dto.RatesOfferDto;
import com.example.demo.model.RatesOffer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatesOfferRepository extends JpaRepository<RatesOffer, Integer> {
    @Query("select new com.example.demo.dto.RatesOfferDto(ratesOffer.id,ratesOffer.price,ratesOffer.priceType,ratesOffer.period,ratesOffer.description)from RatesOffer ratesOffer")
    List<RatesOfferDto> fecthAllRatesOffer();
}

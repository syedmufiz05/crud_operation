package com.example.demo.repository;

import com.example.demo.dto.RatesDto;
import com.example.demo.model.Rates;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatesRepository extends JpaRepository<Rates, Integer> {
    @Query("select new com.example.demo.dto.RatesDto(rates.id,rates.destName,rates.destType,rates.ratesIndex,rates.description,rates.isRatesActive,rates.accessLogs.id) from Rates rates")
    List<RatesDto> fetchAllRates();
}

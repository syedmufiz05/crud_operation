package com.example.demo.service;

import com.example.demo.dto.RatingPlanDto;
import com.example.demo.model.DestinationRates;
import com.example.demo.model.RatingPlan;
import com.example.demo.repository.DestinationRatesRepository;
import com.example.demo.repository.RatingPlanRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RatingPlanServiceImpl implements RatingPlanService {
    @Autowired
    private DestinationRatesRepository destinationRatesRepository;
    @Autowired
    private RatingPlanRepository ratingPlanRepository;

    @Override
    public RatingPlanDto createRatingPlan(RatingPlanDto ratingPlanDto, String authToken) {
        Optional<RatingPlan> ratingPlan = ratingPlanRepository.findById(ratingPlanDto.getRatingPlanId());
        if (ratingPlan.isPresent()) {
            RatingPlan ratingPlanDb = ratingPlan.get();
            DestinationRates destinationRates = new DestinationRates();
            destinationRatesRepository.save(destinationRates);
            ratingPlanDb.setDestinationRates(destinationRates);
            ratingPlanRepository.save(ratingPlanDb);
            return new RatingPlanDto(ratingPlanDb.getId(), destinationRates.getId());
        }
        RatingPlan ratingPlanNew = new RatingPlan();
        DestinationRates destinationRates = new DestinationRates();
        destinationRatesRepository.save(destinationRates);
        ratingPlanNew.setDestinationRates(destinationRates);
        ratingPlanRepository.save(ratingPlanNew);
        return new RatingPlanDto(ratingPlanNew.getId(), destinationRates.getId());
    }
}
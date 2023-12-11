package com.example.demo.controller;

import com.example.demo.dto.RatesPlanOfferDto;
import com.example.demo.service.RatesPlanOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rates/plan/offer")
@CrossOrigin("http://localhost:5173/")
public class RatesPlanOfferController {
    @Autowired
    private RatesPlanOfferService ratesPlanOfferService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<RatesPlanOfferDto> saveRatesPlanOffer(@RequestBody RatesPlanOfferDto ratesPlanOfferDto) {
        return ratesPlanOfferService.saveRatesPlanDetail(ratesPlanOfferDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<String> getAllRatesPlanOffer() {
        return ratesPlanOfferService.getAllRatesPlans();
    }
}

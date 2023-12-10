package com.example.demo.controller;

import com.example.demo.dto.RatesOfferDto;
import com.example.demo.service.RatesOfferService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/rates/offer")
public class RatesOfferController {
    @Autowired
    private RatesOfferService ratesOfferService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<RatesOfferDto> saveRatesOfferDetail(@RequestBody RatesOfferDto ratesOfferDto) {
        return ratesOfferService.saveRatesOffer(ratesOfferDto);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<String> getAllRatesOfferDetails() {
        return ratesOfferService.getAllRatesOffer();
    }

}

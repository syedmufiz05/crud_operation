package com.example.demo.controller;

import com.example.demo.dto.RatesDto;
import com.example.demo.service.RatesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rates/detail")
public class RatesController {

    @Autowired
    private RatesService ratesService;

    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public RatesDto addRates(@RequestBody RatesDto ratesDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return ratesService.addRates(ratesDto, authToken);
    }

    @RequestMapping(value = "/get/{rates_id}", method = RequestMethod.GET)
    public ResponseEntity<RatesDto> getRates(@PathVariable("rates_id") Integer ratesId) {
        return ratesService.getRates(ratesId);
    }

    @RequestMapping(value = "/edit/{rates_id}", method = RequestMethod.PUT)
    public ResponseEntity<RatesDto> editRates(@PathVariable("rates_id") Integer ratesId, @RequestBody RatesDto ratesDto) throws JsonProcessingException {
        return ratesService.editRates(ratesId, ratesDto);
    }

    @RequestMapping(value = "/delete/{rates_id}", method = RequestMethod.DELETE)
    public String deleteRates(@PathVariable("rates_id") Integer ratesId) {
        return ratesService.deleteRates(ratesId);
    }
}

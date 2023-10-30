package com.example.demo.controller;

import com.example.demo.dto.RatesDto;
import com.example.demo.service.RatesService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/rates")
public class RatesController {

    @Autowired
    private RatesService ratesService;

    @RequestMapping(value = "/detail/add", method = RequestMethod.POST)
    public RatesDto addRates(@RequestBody RatesDto ratesDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return ratesService.addRates(ratesDto, authToken);
    }

    @RequestMapping(value = "/detail/edit/{rates_id}", method = RequestMethod.PUT)
    public RatesDto editRates(@PathVariable("rates_id") Integer ratesId, @RequestBody RatesDto ratesDto) throws JsonProcessingException {
        return ratesService.editRates(ratesId, ratesDto);
    }

    @RequestMapping(value = "/detail/delete/{rates_id}", method = RequestMethod.DELETE)
    public String deleteRates(@PathVariable("rates_id") Integer ratesId) {
        return ratesService.deleteRates(ratesId);
    }
}

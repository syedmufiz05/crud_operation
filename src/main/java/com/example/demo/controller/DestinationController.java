package com.example.demo.controller;

import com.example.demo.dto.DestinationDto;
import com.example.demo.service.DestinationService;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/destination")
public class DestinationController {
    @Autowired
    private DestinationService destinationService;

    @RequestMapping(value = "/detail/add", method = RequestMethod.POST)
    public DestinationDto saveDestinationDetails(@RequestBody DestinationDto destinationDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return destinationService.addDestination(destinationDto, authToken);
    }

    @RequestMapping(value = "/detail/get/{destination_id}", method = RequestMethod.GET)
    public ResponseEntity<DestinationDto> getDestinationDetails(@PathVariable("destination_id") Integer destinationId) {
        return destinationService.getDestinationDetail(destinationId);
    }

    @RequestMapping(value = "/detail/edit/{destination_id}", method = RequestMethod.PUT)
    public ResponseEntity<DestinationDto> editDestinationDetails(@PathVariable("destination_id") Integer destinationId, @RequestBody DestinationDto destinationDto) throws JsonProcessingException {
        return destinationService.editDestination(destinationId, destinationDto);
    }

    @RequestMapping(value = "/detail/delete/{destination_id}", method = RequestMethod.DELETE)
    public String deleteDestinationDetails(@PathVariable("destination_id") Integer destinationId) {
        return destinationService.deleteDestination(destinationId);
    }
}

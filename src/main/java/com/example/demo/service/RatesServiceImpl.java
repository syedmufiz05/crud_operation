package com.example.demo.service;

import com.example.demo.dto.RatesDto;
import com.example.demo.model.*;
import com.example.demo.repository.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class RatesServiceImpl implements RatesService {
    @Autowired
    private RatesRepository ratesRepository;
    @Autowired
    private RatingPlanRepository ratingPlanRepository;
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private DestinationRatesRepository destinationRatesRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Override
    public String addRates(RatesDto ratesDto, String authToken) throws JsonProcessingException {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);

        Rates rates = new Rates();
        rates.setDestName(ratesDto.getDestName() != null ? ratesDto.getDestName() : "");
        rates.setDestType(ratesDto.getDestType() != null ? ratesDto.getDestType() : "");
        rates.setRatesIndex(ratesDto.getIndex() != null ? ratesDto.getIndex() : Integer.valueOf(""));
        rates.setDescription(ratesDto.getDescription() != null ? ratesDto.getDescription() : "");
        rates.setIsRatesActive(ratesDto.getIsRatesActive() != null ? ratesDto.getIsRatesActive() : false);
        rates.setAccessLogs(accessLogs);
        ratesRepository.save(rates);

        Optional<Destination> destination = destinationRepository.findByName(rates.getDestName());
        if (destination.isPresent()) {
            Destination destinationDb = destination.get();
            DestinationRates destinationRates = new DestinationRates();
            destinationRates.setRates(rates);
            destinationRates.setDestination(destinationDb);
            DestinationRates destinationRatesDb = destinationRatesRepository.save(destinationRates);
            RatingPlan ratingPlan = new RatingPlan();
            ratingPlan.setDestinationRates(destinationRatesDb);
            ratingPlanRepository.save(ratingPlan);
        }
        return saveRatesRequestPayload(ratesDto, rates, accessLogs);
    }

    @Override
    public String editRates(Integer ratesId, RatesDto ratesDto) throws JsonProcessingException {
        Optional<Rates> rates = ratesRepository.findById(ratesId);
        if (rates.isPresent()) {
            Rates ratesDb = rates.get();
            ratesDb.setRatesIndex(ratesDto.getIndex() != null ? ratesDto.getIndex() : ratesDb.getRatesIndex());
            ratesDb.setDescription(ratesDto.getDescription() != null ? ratesDto.getDescription() : ratesDb.getDescription());
            ratesDb.setIsRatesActive(ratesDto.getIsRatesActive() != null ? ratesDto.getIsRatesActive() : ratesDb.getIsRatesActive());
            ratesRepository.save(ratesDb);

            Optional<AccessLogs> accessLogs = accessLogsRepository.findByIdAccessLogsId(ratesDb.getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogsDb = accessLogs.get();
            accessLogsDb.setAccessDateTime(new Date());
            return editRatesRequestPayload(ratesDto, ratesDb, accessLogsDb);
        }
        return "Please insert the valid Rate Id...";
    }

    @Override
    public String deleteRates(Integer ratesId) {
        ratesRepository.deleteById(ratesId);
        return "Rates Details deleted successfully...";
    }


    private String saveRatesRequestPayload(RatesDto ratesDto, Rates rates, AccessLogs accessLogs) throws JsonProcessingException {
        ratesDto.setRatesId(rates.getId());
        ratesDto.setDestName(rates.getDestName());
        ratesDto.setDestType(rates.getDestType());
        ratesDto.setIndex(rates.getRatesIndex());
        ratesDto.setDescription(rates.getDescription());
        ratesDto.setIsRatesActive(rates.getIsRatesActive());
        ratesDto.setAccessId(rates.getAccessLogs().getIdAccessLogsId());
        String reqPayload = convertEntityToJson(ratesDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "Rates details saved successfully...";
    }

    private String editRatesRequestPayload(RatesDto ratesDto, Rates rates, AccessLogs accessLogs) throws JsonProcessingException {
        ratesDto.setRatesId(rates.getId());
        ratesDto.setDestName(rates.getDestName());
        ratesDto.setDestType(rates.getDestType());
        ratesDto.setIndex(rates.getRatesIndex());
        ratesDto.setDescription(rates.getDescription());
        ratesDto.setIsRatesActive(rates.getIsRatesActive());
        ratesDto.setAccessId(rates.getAccessLogs().getIdAccessLogsId());
        String reqPayload = convertEntityToJson(ratesDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "Rates details updated successfully...";
    }

    private String convertEntityToJson(RatesDto ratesDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(ratesDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

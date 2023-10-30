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
    public RatesDto addRates(RatesDto ratesDto, String authToken) throws JsonProcessingException {
        Optional<Rates> rates = ratesRepository.findById(ratesDto.getRatesId());
        if (rates.isPresent()) {
            Rates ratesDb = rates.get();
            ratesDb.setDestName(ratesDto.getDestName() != null ? ratesDto.getDestName() : "");
            ratesDb.setDestType(ratesDto.getDestType() != null ? ratesDto.getDestType() : "");
            ratesDb.setRatesIndex(ratesDto.getIndex() != null ? ratesDto.getIndex() : Integer.valueOf(""));
            ratesDb.setDescription(ratesDto.getDescription() != null ? ratesDto.getDescription() : "");
            ratesDb.setIsRatesActive(ratesDto.getIsRatesActive() != null ? ratesDto.getIsRatesActive() : false);
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(ratesDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogsDb.isPresent()) {
                AccessLogs accessLogs = accessLogsDb.get();
                ratesRepository.save(ratesDb);
                return saveRatesRequestPayload(ratesDto, ratesDb, accessLogs);
            }
            AccessLogs accessLogs = new AccessLogs();
            accessLogs.setUserId(1212);
            accessLogs.setResponsePayload("");
            accessLogs.setAuthToken(authToken);
            accessLogsRepository.save(accessLogs);
            return saveRatesRequestPayload(ratesDto, ratesDb, accessLogs);
        }

        Rates ratesNew = new Rates();
        ratesNew.setDestName(ratesDto.getDestName() != null ? ratesDto.getDestName() : "");
        ratesNew.setDestType(ratesDto.getDestType() != null ? ratesDto.getDestType() : "");
        ratesNew.setRatesIndex(ratesDto.getIndex() != null ? ratesDto.getIndex() : Integer.valueOf(""));
        ratesNew.setDescription(ratesDto.getDescription() != null ? ratesDto.getDescription() : "");
        ratesNew.setIsRatesActive(ratesDto.getIsRatesActive() != null ? ratesDto.getIsRatesActive() : false);

        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        ratesNew.setAccessLogs(accessLogs);
        ratesRepository.save(ratesNew);
        return saveRatesRequestPayload(ratesDto, ratesNew, accessLogs);
    }

    @Override
    public RatesDto editRates(Integer ratesId, RatesDto ratesDto) throws JsonProcessingException {
        Optional<Rates> rates = ratesRepository.findById(ratesId);
        if (rates.isPresent()) {
            Rates ratesDb = rates.get();
            ratesDb.setDestName(ratesDto.getDestName() != null ? ratesDto.getDestName() : ratesDb.getDestName());
            ratesDb.setDestType(ratesDto.getDestType() != null ? ratesDto.getDestType() : ratesDb.getDestType());
            ratesDb.setRatesIndex(ratesDto.getIndex() != null ? ratesDto.getIndex() : ratesDb.getRatesIndex());
            ratesDb.setDescription(ratesDto.getDescription() != null ? ratesDto.getDescription() : ratesDb.getDescription());
            ratesDb.setIsRatesActive(ratesDto.getIsRatesActive() != null ? ratesDto.getIsRatesActive() : ratesDb.getIsRatesActive());

            Optional<AccessLogs> accessLogs = accessLogsRepository.findByIdAccessLogsId(ratesDb.getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogsDb = accessLogs.get();
            accessLogsDb.setAccessDateTime(new Date());
            return editRatesRequestPayload(ratesDto, ratesDb, accessLogsDb);
        }
        return new RatesDto();
    }

    @Override
    public String deleteRates(Integer ratesId) {
        ratesRepository.deleteById(ratesId);
        return "Rates Details deleted successfully...";
    }


    private RatesDto saveRatesRequestPayload(RatesDto ratesDto, Rates rates, AccessLogs accessLogs) throws JsonProcessingException {
        ratesDto.setRatesId(rates.getId());
        ratesDto.setDestName(rates.getDestName());
        ratesDto.setDestType(rates.getDestType());
        ratesDto.setIndex(rates.getRatesIndex());
        ratesDto.setDescription(rates.getDescription());
        ratesDto.setIsRatesActive(rates.getIsRatesActive());
        ratesDto.setAccessId(accessLogs.getIdAccessLogsId());
        String reqPayload = convertEntityToJson(ratesDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return new RatesDto(ratesDto.getRatesId(), ratesDto.getDestName(), ratesDto.getDestType(), ratesDto.getIndex(), ratesDto.getDescription(), ratesDto.getIsRatesActive(), ratesDto.getAccessId());
    }

    private RatesDto editRatesRequestPayload(RatesDto ratesDto, Rates rates, AccessLogs accessLogs) throws JsonProcessingException {
        ratesDto.setRatesId(rates.getId());
        ratesDto.setDestName(rates.getDestName());
        ratesDto.setDestType(rates.getDestType());
        ratesDto.setIndex(rates.getRatesIndex());
        ratesDto.setDescription(rates.getDescription());
        ratesDto.setIsRatesActive(rates.getIsRatesActive());
        ratesDto.setAccessId(accessLogs.getIdAccessLogsId());
        String reqPayload = convertEntityToJson(ratesDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return new RatesDto(ratesDto.getRatesId(), ratesDto.getDestName(), ratesDto.getDestType(), ratesDto.getIndex(), ratesDto.getDescription(), ratesDto.getIsRatesActive(), ratesDto.getAccessId());
    }

    private String convertEntityToJson(RatesDto ratesDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(ratesDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

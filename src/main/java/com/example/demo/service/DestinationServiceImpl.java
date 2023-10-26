package com.example.demo.service;

import com.example.demo.dto.DestinationDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Destination;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.DestinationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class DestinationServiceImpl implements DestinationService {
    @Autowired
    private DestinationRepository destinationRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    @Override
    public String addDestination(DestinationDto destinationDto, String authToken) throws JsonProcessingException {
        if (destinationDto != null) {
            AccessLogs accessLogs = new AccessLogs();
            accessLogs.setUserId(1212);
            accessLogs.setResponsePayload("");
            accessLogs.setAuthToken(authToken);
            accessLogsRepository.save(accessLogs);
            Destination destination = new Destination();
            destination.setName(destinationDto.getName() != null ? destinationDto.getName() : "");
            destination.setType(destinationDto.getType() != null ? destinationDto.getType() : "");
            destination.setRemarks(destinationDto.getRemarks() != null ? destinationDto.getRemarks() : "");
            destination.setActive(destinationDto.getActive() != null ? destinationDto.getActive() : false);
            destination.setAccessLogs(accessLogs);
            destinationRepository.save(destination);
            return saveDestinationRequestPayload(destinationDto, destination, accessLogs);
        }
        return "Please set the destination fields...";
    }

    @Override
    public String editDestination(Integer destinationId, DestinationDto destinationDto) throws JsonProcessingException {
        Optional<Destination> destination = destinationRepository.findById(destinationId);
        if (destination.isPresent()) {
            Destination destinationDb = destination.get();
            destinationDb.setName(destinationDto.getName() != null ? destinationDto.getName() : "");
            destinationDb.setType(destinationDto.getType() != null ? destinationDto.getType() : "");
            destinationDb.setRemarks(destinationDto.getRemarks() != null ? destinationDto.getRemarks() : "");
            destinationDb.setActive(destinationDto.getActive() != null ? destinationDto.getActive() : false);
            destinationRepository.save(destinationDb);
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(destinationDb.getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogs = accessLogsDb.get();
            accessLogs.setAccessDateTime(new Date());
            return editDestinationRequestPayload(destinationDto, destinationDb, accessLogs);
        }
        return "Please insert the valid Destination id...";
    }

    @Override
    public String deleteDestination(Integer destinationId) {
        destinationRepository.deleteById(destinationId);
        return "Destination details deleted successfully...";
    }

    private String saveDestinationRequestPayload(DestinationDto destinationDto, Destination destination, AccessLogs accessLogs) throws JsonProcessingException {
        destinationDto.setDestinationId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setType(destination.getType());
        destinationDto.setRemarks(destination.getRemarks());
        destinationDto.setActive(destination.getActive());
        destinationDto.setAccessId(destination.getAccessLogs().getIdAccessLogsId() != null ? destination.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(destinationDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "Destination details saved successfully...";
    }

    private String editDestinationRequestPayload(DestinationDto destinationDto, Destination destination, AccessLogs accessLogs) throws JsonProcessingException {
        destinationDto.setDestinationId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setType(destination.getType());
        destinationDto.setRemarks(destination.getRemarks());
        destinationDto.setActive(destination.getActive());
        destinationDto.setAccessId(destination.getAccessLogs().getIdAccessLogsId() != null ? destination.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(destinationDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "Destination details updated successfully...";
    }

    private String convertEntityToJson(DestinationDto destinationDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(destinationDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

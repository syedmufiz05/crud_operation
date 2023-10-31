package com.example.demo.service;

import com.example.demo.dto.DestinationDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Destination;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.DestinationRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public DestinationDto addDestination(DestinationDto destinationDto, String authToken) throws JsonProcessingException {
        Optional<Destination> destination = destinationRepository.findById(destinationDto.getDestinationId());
        if (destination.isPresent()) {
            Destination destinationDb = destination.get();
            destinationDb.setType(destinationDto.getType() != null ? destinationDto.getType() : "");
            destinationDb.setName(destinationDto.getName() != null ? destinationDto.getName() : "");
            destinationDb.setRemarks(destinationDto.getRemarks() != null ? destinationDto.getRemarks() : "");
            destinationDb.setActive(destinationDto.getActive() != null ? destinationDto.getActive() : false);

            Optional<AccessLogs> accessLogs = accessLogsRepository.findByIdAccessLogsId(destinationDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogs.isPresent()) {
                AccessLogs accessLogsDb = accessLogs.get();
                accessLogsDb.setUserId(1212);
                accessLogsDb.setResponsePayload("");
                accessLogsDb.setAuthToken(authToken);
                accessLogsDb.setAccessDateTime(new Date());
                accessLogsRepository.save(accessLogsDb);
                return saveDestinationRequestPayload(destinationDto, destinationDb, accessLogsDb);
            }
            AccessLogs accessLogsNew = new AccessLogs();
            accessLogsNew.setUserId(1212);
            accessLogsNew.setResponsePayload("");
            accessLogsNew.setAuthToken(authToken);
            accessLogsNew.setAccessDateTime(new Date());
            accessLogsRepository.save(accessLogsNew);
            return saveDestinationRequestPayload(destinationDto, destinationDb, accessLogsNew);
        }

        Destination destinationNew = new Destination();
        destinationNew.setType(destinationDto.getType() != null ? destinationDto.getType() : "");
        destinationNew.setName(destinationDto.getName() != null ? destinationDto.getName() : "");
        destinationNew.setRemarks(destinationDto.getRemarks() != null ? destinationDto.getRemarks() : "");
        destinationNew.setActive(destinationDto.getActive() != null ? destinationDto.getActive() : false);
        destinationRepository.save(destinationNew);
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogs.setAccessDateTime(new Date());
        accessLogsRepository.save(accessLogs);
        return saveDestinationRequestPayload(destinationDto, destinationNew, accessLogs);
    }

    @Override
    public ResponseEntity editDestination(Integer destinationId, DestinationDto destinationDto) throws JsonProcessingException {
        Optional<Destination> destination = destinationRepository.findById(destinationId);
        if (destination.isPresent()) {
            Destination destinationDb = destination.get();
            destinationDb.setName(destinationDto.getName() != null ? destinationDto.getName() : destinationDb.getName());
            destinationDb.setType(destinationDto.getType() != null ? destinationDto.getType() : destinationDb.getType());
            destinationDb.setRemarks(destinationDto.getRemarks() != null ? destinationDto.getRemarks() : destinationDb.getRemarks());
            destinationDb.setActive(destinationDto.getActive() != null ? destinationDto.getActive() : destinationDb.getActive());
            destinationRepository.save(destinationDb);
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(destinationDb.getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogs = accessLogsDb.get();
            accessLogs.setAccessDateTime(new Date());
            return editDestinationRequestPayload(destinationDto, destinationDb, accessLogs);
        }
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Destination Id does n't exist"));
    }

    @Override
    public String deleteDestination(Integer destinationId) {
        destinationRepository.deleteById(destinationId);
        return "Destination details deleted successfully...";
    }

    private DestinationDto saveDestinationRequestPayload(DestinationDto destinationDto, Destination destination, AccessLogs accessLogs) throws JsonProcessingException {
        destinationDto.setDestinationId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setType(destination.getType());
        destinationDto.setRemarks(destination.getRemarks());
        destinationDto.setActive(destination.getActive());
        destinationDto.setAccessId(destination.getAccessLogs().getIdAccessLogsId() != null ? destination.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(destinationDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        destination.setAccessLogs(accessLogs);
        destinationRepository.save(destination);
        return new DestinationDto(destinationDto.getDestinationId(), destinationDto.getName(), destinationDto.getType(), destinationDto.getRemarks(), destinationDto.getActive(), destinationDto.getAccessId());
    }

    private ResponseEntity editDestinationRequestPayload(DestinationDto destinationDto, Destination destination, AccessLogs accessLogs) throws JsonProcessingException {
        destinationDto.setDestinationId(destination.getId());
        destinationDto.setName(destination.getName());
        destinationDto.setType(destination.getType());
        destinationDto.setRemarks(destination.getRemarks());
        destinationDto.setActive(destination.getActive());
        destinationDto.setAccessId(destination.getAccessLogs().getIdAccessLogsId() != null ? destination.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(destinationDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        DestinationDto destinationDtoNew = new DestinationDto(destinationDto.getDestinationId(), destinationDto.getName(), destinationDto.getType(), destinationDto.getRemarks(), destinationDto.getActive(), destinationDto.getAccessId());
        return new ResponseEntity<>(destinationDtoNew, HttpStatus.OK);
    }

    private String convertEntityToJson(DestinationDto destinationDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(destinationDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

package com.example.demo.service;

import com.example.demo.dto.AucDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Auc;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.AucRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AucServiceImpl implements AucService {
    @Autowired
    private AucRepository aucRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    public ResponseEntity saveAucDetails(AucDto aucDto, String authToken) throws JsonProcessingException {
        String imsi = aucDto.getImsi();
        Optional<Auc> auc = aucRepository.findByImsi(imsi);
        if (!auc.isPresent()) {
            AccessLogs accessLogs = new AccessLogs();
            accessLogs.setUserId(1212);
            accessLogs.setResponsePayload("");
            accessLogs.setAuthToken(authToken != null ? authToken : "");
            accessLogsRepository.save(accessLogs);
            Auc aucDb = new Auc();
            aucDb.setAccessLogs(accessLogs);
            aucDb.setImsi(aucDto.getImsi() != null ? aucDto.getImsi() : "");
            aucDb.setKi(aucDto.getKi() != null ? aucDto.getKi() : "");
            aucDb.setOpc(aucDto.getOpc() != null ? aucDto.getOpc() : "");
            aucDb.setA3a8Version(aucDto.getA3a8Version() != null ? aucDto.getA3a8Version() : Integer.valueOf(""));
            aucDb.setStatus(aucDto.getStatus() != null ? aucDto.getStatus() : "");
            aucRepository.save(aucDb);
            saveAucRequestPayload(aucDb, aucDto, accessLogs);
            AucDto aucDtoNew = new AucDto(aucDb.getAucId(), aucDb.getImsi(), aucDb.getKi(), aucDb.getOpc(), aucDb.getA3a8Version(), aucDb.getStatus(), aucDb.getAccessLogs().getIdAccessLogsId());
            return new ResponseEntity<>(aucDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Imsi id already exist"));
    }

    public ResponseEntity updateAucDetails(String imsi, AucDto aucDto) throws JsonProcessingException {
        Optional<Auc> auc = aucRepository.findByImsi(imsi);
        if (auc.isPresent()) {
            Auc aucDb = auc.get();
            aucDb.setKi(aucDto.getKi() != null ? aucDto.getKi() : "");
            aucDb.setOpc(aucDto.getOpc() != null ? aucDto.getOpc() : "");
            aucDb.setA3a8Version(aucDto.getA3a8Version() != null ? aucDto.getA3a8Version() : Integer.valueOf(""));
            aucDb.setStatus(aucDto.getStatus() != null ? aucDto.getStatus() : "");
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(aucDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogsDb.isPresent()) {
                AccessLogs accessLogs = accessLogsDb.get();
                aucDto.setImsi(aucDb.getImsi() != null ? aucDb.getImsi() : "");
                aucDto.setAccessId(accessLogs.getIdAccessLogsId());
                accessLogs.setReqPayload(convertEntityToJson(aucDto));
                aucRepository.save(aucDb);
                accessLogsRepository.save(accessLogs);
            }
            AucDto aucDtoNew = new AucDto(aucDb.getAucId(), aucDb.getImsi(), aucDb.getKi(), aucDb.getOpc(), aucDb.getA3a8Version(), aucDb.getStatus(), aucDb.getAccessLogs().getIdAccessLogsId());
            return new ResponseEntity<>(aucDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Imsi Id does n't exist"));
    }

    @Transactional
    public void deleteAucDetails(String imsi) {
        aucRepository.deleteByImsi(imsi);
    }

    private void saveAucRequestPayload(Auc auc, AucDto aucDto, AccessLogs accessLogs) throws JsonProcessingException {
        aucDto.setAucId(auc.getAucId());
        aucDto.setAccessId(auc.getAccessLogs().getIdAccessLogsId() != null ? auc.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(aucDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
    }

    private String convertEntityToJson(AucDto aucDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(aucDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }

}

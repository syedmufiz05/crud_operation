package com.example.demo.service;

import com.example.demo.dto.AucDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Auc;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.AucRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class AucService {
    @Autowired
    private AucRepository aucRepository;
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    public String saveAucDetails(AucDto aucDto, String authToken) throws JsonProcessingException {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken != null ? authToken : "");
        accessLogsRepository.save(accessLogs);
        Auc auc = new Auc();
        auc.setAccessLogs(accessLogs);
        auc.setImsi(aucDto.getImsi() != null ? aucDto.getImsi() : "");
        auc.setKi(aucDto.getKi() != null ? aucDto.getKi() : "");
        auc.setOpc(aucDto.getOpc() != null ? aucDto.getOpc() : "");
        auc.setA3a8Version(aucDto.getA3a8Version() != null ? aucDto.getA3a8Version() : "");
        auc.setStatus(aucDto.getStatus() != null ? aucDto.getStatus() : "");
        aucRepository.save(auc);
        return saveAucRequestPayload(auc, aucDto, accessLogs);
    }

    public String updateAucDetails(String imsi, AucDto aucDto) throws JsonProcessingException {
        Optional<Auc> auc = aucRepository.findByImsi(imsi);
        if (auc.isPresent()) {
            Auc aucDb = auc.get();
            aucDb.setKi(aucDto.getKi() != null ? aucDto.getKi() : "");
            aucDb.setOpc(aucDto.getOpc() != null ? aucDto.getOpc() : "");
            aucDb.setA3a8Version(aucDto.getA3a8Version() != null ? aucDto.getA3a8Version() : "");
            aucDb.setStatus(aucDto.getStatus() != null ? aucDto.getStatus() : "");
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(aucDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogsDb.isPresent()) {
                AccessLogs accessLogs = accessLogsDb.get();
                aucDto.setImsi(aucDb.getImsi() != null ? aucDb.getImsi() : "");
                aucDto.setAccessId(accessLogs.getIdAccessLogsId());
                accessLogs.setReqPayload(convertEntityToJson(aucDto));
                aucRepository.save(aucDb);
                accessLogsRepository.save(accessLogs);
                return "AUC Details are updated...";
            }
        }
        return "Invalid IMSI";
    }

    @Transactional
    public void deleteAucDetails(String imsi) {
        aucRepository.deleteByImsi(imsi);
    }

    private String saveAucRequestPayload(Auc auc, AucDto aucDto, AccessLogs accessLogs) throws JsonProcessingException {
        aucDto.setAccessId(auc.getAccessLogs().getIdAccessLogsId() != null ? auc.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(aucDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "AUC Details are saved...";
    }

    private String convertEntityToJson(AucDto aucDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(aucDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }
}

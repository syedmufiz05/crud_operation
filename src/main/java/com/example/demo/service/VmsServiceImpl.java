package com.example.demo.service;

import com.example.demo.dto.VmsDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.Vms;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.VmsRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Optional;

@Service
public class VmsServiceImpl implements VmsService {
    @Autowired
    private AccessLogsRepository accessLogsRepository;
    @Autowired
    private VmsRepository vmsRepository;

    @Override
    public String saveVmsDetails(VmsDto vmsDto, String authToken) throws JsonProcessingException {
        String msisdn = vmsDto.getMsisdn();
        Optional<Vms> vmsDb = vmsRepository.findByMsisdn(msisdn);
        if (!vmsDb.isPresent()) {
            AccessLogs accessLogs = new AccessLogs();
            accessLogs.setUserId(1212);
            accessLogs.setResponsePayload("");
            accessLogs.setAuthToken(authToken != null ? authToken : "");
            accessLogsRepository.save(accessLogs);
            Vms vms = new Vms();
            vms.setAccessLogs(accessLogs);
            vms.setMsisdn(vmsDto.getMsisdn() != null ? vmsDto.getMsisdn() : "");
            vms.setSystemId(vmsDto.getSystemId() != null ? vmsDto.getSystemId() : Integer.valueOf(""));
            vms.setMailboxId(vmsDto.getMailboxId() != null ? vmsDto.getMailboxId() : Integer.valueOf(""));
            vms.setRegisterFlag(vmsDto.getRegisterFlag() != null ? vmsDto.getRegisterFlag() : false);
            vms.setActiveFlag(vmsDto.getActiveFlag() != null ? vmsDto.getActiveFlag() : false);
            vms.setLockedFlag(vmsDto.getLockedFlag() != null ? vmsDto.getLockedFlag() : false);
            vms.setLanguage(vmsDto.getLanguage() != null ? vmsDto.getLanguage() : Integer.valueOf(""));
            vms.setTemporaryGreeting(vmsDto.getTemporaryGreeting() != null ? vmsDto.getTemporaryGreeting() : false);
            vms.setGreetingTypeSystem(vmsDto.getGreetingTypeSystem() != null ? vmsDto.getGreetingTypeSystem() : "");
            vms.setPasswordFlag(vmsDto.getPasswordFlag() != null ? vmsDto.getPasswordFlag() : false);
            vms.setCallbackFlag(vmsDto.getCallbackFlag() != null ? vmsDto.getCallbackFlag() : false);
            vms.setCliFlag(vmsDto.getCliFlag() != null ? vmsDto.getCliFlag() : false);
            vms.setPassword(vmsDto.getPassword() != null ? vmsDto.getPassword() : "");
            vms.setCallbackTimeout(vmsDto.getCallbackTimeout() != null ? vmsDto.getCallbackTimeout() : "");
            vmsRepository.save(vms);
            return saveVmsRequestPayload(vms, vmsDto, accessLogs);
        }
        return msisdn + " is already exist";
    }

    @Override
    public String updateVmsDetails(VmsDto vmsDto, String msisdn) throws JsonProcessingException {
        Optional<Vms> vms = vmsRepository.findByMsisdn(msisdn);
        if (vms.isPresent()) {
            Vms vmsDb = vms.get();
            vmsDb.setSystemId(vmsDto.getSystemId() != null ? vmsDto.getSystemId() : Integer.valueOf(""));
            vmsDb.setMailboxId(vmsDto.getMailboxId() != null ? vmsDto.getMailboxId() : Integer.valueOf(""));
            vmsDb.setRegisterFlag(vmsDto.getRegisterFlag() != null ? vmsDto.getRegisterFlag() : false);
            vmsDb.setActiveFlag(vmsDto.getActiveFlag() != null ? vmsDto.getActiveFlag() : false);
            vmsDb.setLockedFlag(vmsDto.getLockedFlag() != null ? vmsDto.getLockedFlag() : false);
            vmsDb.setLanguage(vmsDto.getLanguage() != null ? vmsDto.getLanguage() : Integer.valueOf(""));
            vmsDb.setTemporaryGreeting(vmsDto.getTemporaryGreeting() != null ? vmsDto.getTemporaryGreeting() : false);
            vmsDb.setGreetingTypeSystem(vmsDto.getGreetingTypeSystem() != null ? vmsDto.getGreetingTypeSystem() : "");
            vmsDb.setPasswordFlag(vmsDto.getPasswordFlag() != null ? vmsDto.getPasswordFlag() : false);
            vmsDb.setCallbackFlag(vmsDto.getCallbackFlag() != null ? vmsDto.getCallbackFlag() : false);
            vmsDb.setCliFlag(vmsDto.getCliFlag() != null ? vmsDto.getCliFlag() : false);
            vmsDb.setPassword(vmsDto.getPassword() != null ? vmsDto.getPassword() : "");
            vmsDb.setCallbackTimeout(vmsDto.getCallbackTimeout() != null ? vmsDto.getCallbackTimeout() : "");
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(vmsDb.getAccessLogs().getIdAccessLogsId());
            if (accessLogsDb.isPresent()) {
                AccessLogs accessLogs = accessLogsDb.get();
                vmsDto.setVmsId(vmsDb.getVmsId() != null ? vmsDb.getVmsId() : Integer.valueOf(""));
                vmsDto.setMsisdn(vmsDb.getMsisdn() != null ? vmsDb.getMsisdn() : "");
                vmsDto.setAccessId(accessLogs.getIdAccessLogsId());
                accessLogs.setReqPayload(convertEntityToJson(vmsDto));
                vmsRepository.save(vmsDb);
                accessLogsRepository.save(accessLogs);
                return "VMS Details are updated...";
            }
        }
        return "Invalid IMSI";
    }

    @Transactional
    @Override
    public String deleteVmsDetails(String msisdn) {
        vmsRepository.deleteByMsisdn(msisdn);
        return "VMS Details are deleted";
    }

    private String saveVmsRequestPayload(Vms vms, VmsDto vmsDto, AccessLogs accessLogs) throws JsonProcessingException {
        vmsDto.setVmsId(vms.getVmsId());
        vmsDto.setAccessId(vms.getAccessLogs().getIdAccessLogsId() != null ? vms.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(vmsDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return "VMS Details are saved...";
    }

    private String convertEntityToJson(VmsDto vmsDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(vmsDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }

}

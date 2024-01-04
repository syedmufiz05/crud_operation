package com.example.demo.service;

import com.example.demo.dto.CallSessionUsageDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.CallSessionUsage;
import com.example.demo.repository.CallSessionUsageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CallSessionUsageServiceImpl implements CallSessionUsageService {

    @Autowired
    private CallSessionUsageRepo callSessionUsageRepo;

    @Override
    public ResponseEntity saveCallSessionUsage(CallSessionUsageDto callSessionUsageDto) {
        Optional<CallSessionUsage> callSessionUsage = callSessionUsageRepo.findByImsi(callSessionUsageDto.getImsi());
        if (!callSessionUsage.isPresent()) {
            CallSessionUsage callSessionUsageDb = new CallSessionUsage();
            callSessionUsageDb.setPeerSessionId(callSessionUsageDto.getPeerSessionId());
            callSessionUsageDb.setMsisdn(callSessionUsageDto.getMsisdn());
            callSessionUsageDb.setImsi(callSessionUsageDto.getImsi());
            callSessionUsageDb.setCalledMsisdn(callSessionUsageDto.getCalledMsisdn());
            callSessionUsageDb.setLocationInfo(callSessionUsageDto.getLocationInfo());
            callSessionUsageDb.setSessionState(callSessionUsageDto.getSessionState());
            callSessionUsageDb.setCallStartTs(callSessionUsageDto.getCallStartTs());
            callSessionUsageDb.setCallEndTs(callSessionUsageDto.getCallEndTs());
            callSessionUsageDb.setTotalSeconds(callSessionUsageDto.getTotalSeconds());
            callSessionUsageDb.setCallStatus(callSessionUsageDto.getCallStatus());
            callSessionUsageRepo.save(callSessionUsageDb);
            CallSessionUsageDto callSessionUsageDtoNew = new CallSessionUsageDto(callSessionUsageDb.getId(), callSessionUsageDb.getPeerSessionId(), callSessionUsageDb.getMsisdn(), callSessionUsageDb.getImsi(), callSessionUsageDb.getCalledMsisdn(), callSessionUsageDb.getLocationInfo(), callSessionUsageDb.getSessionState(), callSessionUsageDb.getCallStartTs(), callSessionUsageDb.getCallEndTs(), callSessionUsageDb.getTotalSeconds(), callSessionUsageDb.getCallStatus());
            return new ResponseEntity<>(callSessionUsageDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "IMSI Id already exist"));
    }

    @Override
    public List<CallSessionUsageDto> getAllCallSessionUsage() {
        return callSessionUsageRepo.fetchAllCallSessionUsage();
    }
}

package com.example.demo.service;

import com.example.demo.dto.DataSessionUsageDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.DataSessionUsage;
import com.example.demo.repository.DataSessionUsageRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DataSessionUsageServiceImpl implements DataSessionUsageService {
    @Autowired
    private DataSessionUsageRepo dataSessionUsageRepo;

    @Override
    public ResponseEntity saveDataSessionUsage(DataSessionUsageDto dataSessionUsageDto) {
        Optional<DataSessionUsage> dataSessionUsageDb = dataSessionUsageRepo.findByImsi(dataSessionUsageDto.getImsi() != null ? dataSessionUsageDto.getImsi() : "0");
        if (!dataSessionUsageDb.isPresent()) {
            DataSessionUsage dataSessionUsage = new DataSessionUsage();
            dataSessionUsage.setPeerSessionId(dataSessionUsageDto.getPeerSessionId());
            dataSessionUsage.setMsisdn(dataSessionUsageDto.getMsisdn());
            dataSessionUsage.setImsi(dataSessionUsageDto.getImsi());
            dataSessionUsage.setFramedIp(dataSessionUsageDto.getFramedIp());
            dataSessionUsage.setLocationInfo(dataSessionUsageDto.getLocationInfo());
            dataSessionUsage.setSgsnAddress(dataSessionUsageDto.getSgsnAddress());
            dataSessionUsage.setCalledStationId(dataSessionUsageDto.getCalledStationId());
            dataSessionUsage.setSessionState(dataSessionUsageDto.getSessionState());
            dataSessionUsage.setSessionStartTs(dataSessionUsageDto.getSessionStartTs());
            dataSessionUsage.setSessionEndTs(dataSessionUsageDto.getSessionEndTs());
            dataSessionUsage.setTotalOctates(dataSessionUsageDto.getTotalOctates());
            dataSessionUsage.setBitrate(dataSessionUsageDto.getBitrate());
            dataSessionUsage.setTotalInputOctets(dataSessionUsageDto.getTotalInputOctets());
            dataSessionUsage.setTotalOutputOctets(dataSessionUsageDto.getTotalOutputOctets());
            dataSessionUsage.setSessionStatus(dataSessionUsageDto.getSessionStatus());
            dataSessionUsageRepo.save(dataSessionUsage);
            DataSessionUsageDto dataSessionUsageDtoNew = new DataSessionUsageDto(dataSessionUsage.getId(), dataSessionUsage.getPeerSessionId(), dataSessionUsage.getMsisdn(), dataSessionUsage.getImsi(), dataSessionUsage.getFramedIp(), dataSessionUsage.getLocationInfo(), dataSessionUsage.getSgsnAddress(), dataSessionUsage.getCalledStationId(), dataSessionUsage.getSessionState(), dataSessionUsage.getSessionStartTs(), dataSessionUsage.getSessionEndTs(), dataSessionUsage.getTotalOctates(), dataSessionUsage.getBitrate(), dataSessionUsage.getTotalInputOctets(), dataSessionUsage.getTotalOutputOctets(), dataSessionUsage.getSessionStatus());
            return new ResponseEntity<>(dataSessionUsageDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "IMSI Id already exist"));
    }

    @Override
    public List<DataSessionUsageDto> getAllDataSessionUsage() {
        return dataSessionUsageRepo.fetchAllDataSessionUsage();
    }
}

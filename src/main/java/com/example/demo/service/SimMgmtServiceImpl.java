package com.example.demo.service;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.SimMgmt;
import com.example.demo.repository.SimMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class SimMgmtServiceImpl implements SimMgmtService {
    @Autowired
    private SimMgmtRepository simMgmtRepository;

    @Override
    public ResponseEntity saveSimMgmt(SimMgmtDto simMgmtDto) {
        Optional<SimMgmt> simMgmt = simMgmtRepository.findByImsi(simMgmtDto.getImsi());
        if (!simMgmt.isPresent()) {
            SimMgmt simMgmtDb = new SimMgmt();
            simMgmtDb.setImsi(simMgmtDto.getImsi() != null ? simMgmtDto.getImsi() : "");
            simMgmtDb.setBatchNo(simMgmtDto.getBatchNo() != null ? simMgmtDto.getBatchNo() : "");
            simMgmtDb.setSimType(simMgmtDto.getSimType() != null ? simMgmtDto.getSimType() : "");
            simMgmtDb.setKeyId(simMgmtDto.getKeyId() != null ? simMgmtDto.getKeyId() : Integer.valueOf(""));
            simMgmtDb.setAuthId(simMgmtDto.getAuthId() != null ? simMgmtDto.getAuthId() : Integer.valueOf(""));
            simMgmtDb.setVendorName(simMgmtDto.getVendorName() != null ? simMgmtDto.getVendorName() : "");
            simMgmtDb.setStatus(simMgmtDto.getStatus() != null ? simMgmtDto.getStatus() : false);
            simMgmtRepository.save(simMgmtDb);
            SimMgmtDto simMgmtDtoNew = new SimMgmtDto(simMgmtDb.getId(), simMgmtDb.getImsi(), simMgmtDb.getBatchNo(), simMgmtDb.getBatchDate(), simMgmtDb.getAllocationDate(), simMgmtDb.getSimType(), simMgmtDb.getKeyId(), simMgmtDb.getAuthId(), simMgmtDb.getVendorName(), simMgmtDb.getStatus());
            return new ResponseEntity<>(simMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "IMSI Id already exist"));
    }

    @Override
    public List<SimMgmtDto> getAllSimMgmt() {
        return simMgmtRepository.fetchAllSimMgmt();
    }
}

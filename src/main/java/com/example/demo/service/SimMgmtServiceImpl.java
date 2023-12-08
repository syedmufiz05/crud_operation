package com.example.demo.service;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.SimMgmt;
import com.example.demo.repository.SimMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public ResponseEntity editSimMgmt(Integer simId, SimMgmtDto simMgmtDto) {
        Optional<SimMgmt> simMgmt = simMgmtRepository.findById(simId);
        if (simMgmt.isPresent()) {
            SimMgmt simMgmtDb = simMgmt.get();
            simMgmtDb.setImsi(simMgmtDto.getImsi() != null ? simMgmtDto.getImsi() : simMgmtDb.getImsi());
            simMgmtDb.setBatchNo(simMgmtDto.getBatchNo() != null ? simMgmtDto.getBatchNo() : simMgmtDb.getBatchNo());
            simMgmtDb.setSimType(simMgmtDto.getSimType() != null ? simMgmtDto.getSimType() : simMgmtDb.getSimType());
            simMgmtDb.setKeyId(simMgmtDto.getKeyId() != null ? simMgmtDto.getKeyId() : simMgmtDb.getKeyId());
            simMgmtDb.setAuthId(simMgmtDto.getAuthId() != null ? simMgmtDto.getAuthId() : simMgmtDb.getAuthId());
            simMgmtDb.setVendorName(simMgmtDto.getVendorName() != null ? simMgmtDto.getVendorName() : simMgmtDb.getVendorName());
            simMgmtDb.setStatus(simMgmtDto.getStatus() != null ? simMgmtDto.getStatus() : simMgmtDb.getStatus());
            simMgmtRepository.save(simMgmtDb);
            SimMgmtDto simMgmtDtoNew = new SimMgmtDto(simMgmtDb.getId(), simMgmtDb.getImsi(), simMgmtDb.getBatchNo(), simMgmtDb.getBatchDate(), simMgmtDb.getAllocationDate(), simMgmtDb.getSimType(), simMgmtDb.getKeyId(), simMgmtDb.getAuthId(), simMgmtDb.getVendorName(), simMgmtDb.getStatus());
            return new ResponseEntity<>(simMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Sim Id"));
    }

    @Transactional
    @Override
    public ResponseEntity deleteSimMgmt(Integer simId) {
        Optional<SimMgmt> simMgmt = simMgmtRepository.findById(simId);
        if (simMgmt.isPresent()) {
            simMgmtRepository.deleteById(simId);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK.value(), "Deleted Successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Sim Id"));
    }

    @Override
    public List<SimMgmtDto> getAllSimMgmt() {
        return simMgmtRepository.fetchAllSimMgmt();
    }

    @Override
    public List<SimMgmtDto> searchRecord(String keyword) {
        List<SimMgmt> simMgmtDbList = simMgmtRepository.searchItemsByName(keyword);
        List<SimMgmtDto> simMgmtDtoList = new ArrayList<>();
        for (SimMgmt simMgmtDb : simMgmtDbList) {
            SimMgmtDto simMgmtDto = new SimMgmtDto();
            simMgmtDto.setSimId(simMgmtDb.getId());
            simMgmtDto.setImsi(simMgmtDb.getImsi());
            simMgmtDto.setBatchNo(simMgmtDb.getBatchNo());
            simMgmtDto.setBatchDate(simMgmtDb.getBatchDate());
            simMgmtDto.setAllocationDate(simMgmtDb.getAllocationDate());
            simMgmtDto.setSimType(simMgmtDb.getSimType());
            simMgmtDto.setKeyId(simMgmtDb.getKeyId());
            simMgmtDto.setAuthId(simMgmtDb.getAuthId());
            simMgmtDto.setVendorName(simMgmtDb.getVendorName());
            simMgmtDto.setStatus(simMgmtDb.getStatus());
            simMgmtDtoList.add(simMgmtDto);
        }
        return simMgmtDtoList;
    }
}

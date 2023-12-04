package com.example.demo.service;

import com.example.demo.dto.SimMgmtDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface SimMgmtService {
    ResponseEntity saveSimMgmt(SimMgmtDto simMgmtDto);
    List<SimMgmtDto> getAllSimMgmt();
}

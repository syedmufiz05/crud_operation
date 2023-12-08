package com.example.demo.service;

import com.example.demo.dto.MsisdnMgmtDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface MsisdnMgmtService {
    ResponseEntity saveMsisdnMgmt(MsisdnMgmtDto msisdnMgmtDto);

    ResponseEntity editMsisdnMgmt(Integer msisdnId, MsisdnMgmtDto msisdnMgmtDto);

    ResponseEntity deleteMsisdnMgmt(Integer msisdnId);

    List<MsisdnMgmtDto> getAllMsisdnDetail();

    List<MsisdnMgmtDto> searchRecord(String keyword);
}

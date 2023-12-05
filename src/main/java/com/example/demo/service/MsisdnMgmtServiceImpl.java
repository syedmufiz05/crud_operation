package com.example.demo.service;

import com.example.demo.dto.MsisdnMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.MsisdnMgmt;
import com.example.demo.repository.MsisdnMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MsisdnMgmtServiceImpl implements MsisdnMgmtService {
    @Autowired
    private MsisdnMgmtRepository msisdnMgmtRepository;

    @Override
    public ResponseEntity saveMsisdnMgmt(MsisdnMgmtDto msisdnMgmtDto) {
        Optional<MsisdnMgmt> msisdnMgmt = msisdnMgmtRepository.findByMsisdn(msisdnMgmtDto.getMsisdn());
        if (!msisdnMgmt.isPresent()) {
            MsisdnMgmt msisdnMgmtDb = new MsisdnMgmt();
            msisdnMgmtDb.setMsisdn(msisdnMgmtDto.getMsisdn() != null ? msisdnMgmtDto.getMsisdn() : "");
            msisdnMgmtDb.setCategory(msisdnMgmtDto.getCategory() != null ? msisdnMgmtDto.getCategory() : "");
            msisdnMgmtDb.setSeriesId(msisdnMgmtDto.getSeriesId() != null ? msisdnMgmtDto.getSeriesId() : Integer.valueOf(""));
            msisdnMgmtDb.setIsPrepaid(msisdnMgmtDto.getIsPrepaid() != null ? msisdnMgmtDto.getIsPrepaid() : false);
            msisdnMgmtDb.setIsPostpaid(msisdnMgmtDto.getIsPostpaid() != null ? msisdnMgmtDto.getIsPostpaid() : false);
            msisdnMgmtDb.setIsM2M(msisdnMgmtDto.getIsM2M() != null ? msisdnMgmtDto.getIsM2M() : false);
            msisdnMgmtDb.setIsSpecialNo(msisdnMgmtDto.getIsSpecialNo() != null ? msisdnMgmtDto.getIsSpecialNo() : false);
            msisdnMgmtDb.setStatus(msisdnMgmtDto.getStatus() != null ? msisdnMgmtDto.getStatus() : false);
            msisdnMgmtRepository.save(msisdnMgmtDb);
            MsisdnMgmtDto msisdnMgmtDtoNew = new MsisdnMgmtDto(msisdnMgmtDb.getId(), msisdnMgmtDb.getMsisdn(), msisdnMgmtDb.getCategory(), msisdnMgmtDb.getSeriesId(), msisdnMgmtDb.getIsPrepaid(), msisdnMgmtDb.getIsPostpaid(), msisdnMgmtDb.getIsM2M(), msisdnMgmtDb.getIsSpecialNo(), msisdnMgmtDb.getAllocationDate(), msisdnMgmtDb.getStatus());
            return new ResponseEntity<>(msisdnMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "MSISDN already exist"));
    }

    @Override
    public List<MsisdnMgmtDto> getAllMsisdnDetail() {
        return msisdnMgmtRepository.fetchAllMsisdnMgmtRecord();
    }
}

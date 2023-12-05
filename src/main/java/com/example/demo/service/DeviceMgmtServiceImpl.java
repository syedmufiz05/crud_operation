package com.example.demo.service;

import com.example.demo.dto.DeviceMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.DeviceMgmt;
import com.example.demo.repository.DeviceMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DeviceMgmtServiceImpl implements DeviceMgmtService {
    @Autowired
    private DeviceMgmtRepository deviceMgmtRepository;

    @Override
    public ResponseEntity saveDeviceMgmtDetail(DeviceMgmtDto deviceMgmtDto) {
        Optional<DeviceMgmt> deviceMgmt = deviceMgmtRepository.findByDeviceId(deviceMgmtDto.getDeviceId());
        if (!deviceMgmt.isPresent()) {
            DeviceMgmt deviceMgmtDb = new DeviceMgmt();
            deviceMgmtDb.setImeiPrimary(deviceMgmtDto.getImeiPrimary() != null ? deviceMgmtDto.getImeiPrimary() : "");
            deviceMgmtDb.setImeiList(deviceMgmtDto.getImeiList() != null ? deviceMgmtDto.getImeiList() : "");
            deviceMgmtDb.setUserAgent(deviceMgmtDto.getUserAgent() != null ? deviceMgmtDto.getUserAgent() : "");
            deviceMgmtDb.setFootPrint(deviceMgmtDto.getFootPrint() != null ? deviceMgmtDto.getFootPrint() : "");
            deviceMgmtDb.setEirTrackId(deviceMgmtDto.getEirTrackId() != null ? deviceMgmtDto.getEirTrackId() : Integer.valueOf(""));
            deviceMgmtDb.setIsESim(deviceMgmtDto.getIsESim() != null ? deviceMgmtDto.getIsESim() : false);
            deviceMgmtDb.setIsUicc(deviceMgmtDto.getIsUicc() != null ? deviceMgmtDto.getIsUicc() : false);
            deviceMgmtDb.setStatus(deviceMgmtDto.getStatus() != null ? deviceMgmtDto.getStatus() : false);
            deviceMgmtRepository.save(deviceMgmtDb);
            DeviceMgmtDto deviceMgmtDtoNew = new DeviceMgmtDto(deviceMgmtDb.getDeviceId(), deviceMgmtDb.getImeiPrimary(), deviceMgmtDb.getImeiList(), deviceMgmtDb.getUserAgent(), deviceMgmtDb.getFootPrint(), deviceMgmtDb.getEirTrackId(), deviceMgmtDb.getIsESim(), deviceMgmtDb.getIsUicc(), deviceMgmtDb.getRegistrationDate(), deviceMgmtDb.getStatus());
            return new ResponseEntity<>(deviceMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Device Id already exist"));
    }

    @Override
    public List<DeviceMgmtDto> fetchAllDeviceMgmtDetail() {
        return deviceMgmtRepository.fetchAllDeviceMgmtDetail();
    }
}

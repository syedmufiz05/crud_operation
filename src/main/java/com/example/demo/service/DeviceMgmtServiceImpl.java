package com.example.demo.service;

import com.example.demo.dto.DeviceMgmtDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.DeviceMgmt;
import com.example.demo.repository.DeviceMgmtRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
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
    public ResponseEntity editDeviceMgmtDetail(Integer deviceId, DeviceMgmtDto deviceMgmtDto) {
        Optional<DeviceMgmt> deviceMgmt = deviceMgmtRepository.findByDeviceId(deviceId);
        if (deviceMgmt.isPresent()) {
            DeviceMgmt deviceMgmtDb = deviceMgmt.get();
            deviceMgmtDb.setImeiPrimary(deviceMgmtDto.getImeiPrimary() != null ? deviceMgmtDto.getImeiPrimary() : deviceMgmtDb.getImeiPrimary());
            deviceMgmtDb.setImeiList(deviceMgmtDto.getImeiList() != null ? deviceMgmtDto.getImeiList() : deviceMgmtDb.getImeiList());
            deviceMgmtDb.setUserAgent(deviceMgmtDto.getUserAgent() != null ? deviceMgmtDto.getUserAgent() : deviceMgmtDb.getUserAgent());
            deviceMgmtDb.setFootPrint(deviceMgmtDto.getFootPrint() != null ? deviceMgmtDto.getFootPrint() : deviceMgmtDb.getFootPrint());
            deviceMgmtDb.setEirTrackId(deviceMgmtDto.getEirTrackId() != null ? deviceMgmtDto.getEirTrackId() : deviceMgmtDb.getEirTrackId());
            deviceMgmtDb.setIsESim(deviceMgmtDto.getIsESim() != null ? deviceMgmtDto.getIsESim() : deviceMgmtDb.getIsESim());
            deviceMgmtDb.setIsUicc(deviceMgmtDto.getIsUicc() != null ? deviceMgmtDto.getIsUicc() : deviceMgmtDb.getIsUicc());
            deviceMgmtDb.setStatus(deviceMgmtDto.getStatus() != null ? deviceMgmtDto.getStatus() : deviceMgmtDb.getStatus());
            DeviceMgmtDto deviceMgmtDtoNew = new DeviceMgmtDto(deviceMgmtDb.getDeviceId(), deviceMgmtDb.getImeiPrimary(), deviceMgmtDb.getImeiList(), deviceMgmtDb.getUserAgent(), deviceMgmtDb.getFootPrint(), deviceMgmtDb.getEirTrackId(), deviceMgmtDb.getIsESim(), deviceMgmtDb.getIsUicc(), deviceMgmtDb.getRegistrationDate(), deviceMgmtDb.getStatus());
            deviceMgmtRepository.save(deviceMgmtDb);
            return new ResponseEntity<>(deviceMgmtDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid device Id"));
    }

    @Transactional
    @Override
    public ResponseEntity deleteDeviceMgmtDetail(Integer deviceId) {
        Optional<DeviceMgmt> deviceMgmt = deviceMgmtRepository.findByDeviceId(deviceId);
        if (deviceMgmt.isPresent()) {
            deviceMgmtRepository.deleteByDeviceId(deviceId);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK.value(), "Deleted Successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid device Id"));
    }

    @Override
    public List<DeviceMgmtDto> fetchAllDeviceMgmtDetail() {
        return deviceMgmtRepository.fetchAllDeviceMgmtDetail();
    }

    @Override
    public List<DeviceMgmtDto> searchItems(String keyword) {
        List<DeviceMgmt> deviceMgmtDbList = deviceMgmtRepository.searchItemsByName(keyword);
        List<DeviceMgmtDto> deviceMgmtList = new ArrayList<>();
        for (DeviceMgmt deviceMgmtDb : deviceMgmtDbList) {
            DeviceMgmtDto deviceMgmtDto = new DeviceMgmtDto();
            deviceMgmtDto.setDeviceId(deviceMgmtDb.getDeviceId());
            deviceMgmtDto.setImeiPrimary(deviceMgmtDb.getImeiPrimary());
            deviceMgmtDto.setImeiList(deviceMgmtDb.getImeiList());
            deviceMgmtDto.setUserAgent(deviceMgmtDb.getUserAgent());
            deviceMgmtDto.setFootPrint(deviceMgmtDb.getFootPrint());
            deviceMgmtDto.setEirTrackId(deviceMgmtDb.getEirTrackId());
            deviceMgmtDto.setIsESim(deviceMgmtDb.getIsESim());
            deviceMgmtDto.setIsUicc(deviceMgmtDb.getIsUicc());
            deviceMgmtDto.setRegistrationDate(deviceMgmtDb.getRegistrationDate());
            deviceMgmtDto.setStatus(deviceMgmtDb.getStatus());
            deviceMgmtList.add(deviceMgmtDto);
        }
        return deviceMgmtList;
    }
}

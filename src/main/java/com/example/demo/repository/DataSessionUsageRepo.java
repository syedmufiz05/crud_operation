package com.example.demo.repository;

import com.example.demo.dto.DataSessionUsageDto;
import com.example.demo.model.DataSessionUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface DataSessionUsageRepo extends JpaRepository<DataSessionUsage, Integer> {
    Optional<DataSessionUsage> findByImsi(String imsi);

    @Query("select new com.example.demo.dto.DataSessionUsageDto(dataSessionUsage.id,dataSessionUsage.peerSessionId,dataSessionUsage.msisdn,dataSessionUsage.imsi,dataSessionUsage.framedIp,dataSessionUsage.locationInfo,dataSessionUsage.sgsnAddress,dataSessionUsage.calledStationId,dataSessionUsage.sessionState,dataSessionUsage.sessionStartTs,dataSessionUsage.sessionEndTs,dataSessionUsage.totalOctates,dataSessionUsage.bitrate,dataSessionUsage.totalInputOctets,dataSessionUsage.totalOutputOctets,dataSessionUsage.sessionStatus) from DataSessionUsage dataSessionUsage")
    List<DataSessionUsageDto> fetchAllDataSessionUsage();
}

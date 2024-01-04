package com.example.demo.repository;

import com.example.demo.dto.CallSessionUsageDto;
import com.example.demo.model.CallSessionUsage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CallSessionUsageRepo extends JpaRepository<CallSessionUsage, Integer> {
    Optional<CallSessionUsage> findByImsi(String imsi);

    @Query("select new com.example.demo.dto.CallSessionUsageDto(csu.id,csu.peerSessionId,csu.msisdn,csu.imsi,csu.calledMsisdn,csu.locationInfo,csu.sessionState,csu.callStartTs,csu.callEndTs,csu.totalSeconds,csu.callStatus) from CallSessionUsage csu")
    List<CallSessionUsageDto> fetchAllCallSessionUsage();
}

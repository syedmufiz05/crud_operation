package com.example.demo.repository;

import com.example.demo.dto.SimMgmtDto;
import com.example.demo.model.SimMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface SimMgmtRepository extends JpaRepository<SimMgmt, Integer> {
    @Query("select new com.example.demo.dto.SimMgmtDto(simMgmt.id,simMgmt.imsi,simMgmt.batchNo,simMgmt.batchDate,simMgmt.allocationDate,simMgmt.simType,simMgmt.keyId,simMgmt.authId,simMgmt.vendorName,simMgmt.status)from SimMgmt simMgmt")
    List<SimMgmtDto> fetchAllSimMgmt();
    Optional<SimMgmt> findByImsi(String imsi);
}

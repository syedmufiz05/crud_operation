package com.example.demo.repository;

import com.example.demo.dto.MsisdnMgmtDto;
import com.example.demo.model.MsisdnMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MsisdnMgmtRepository extends JpaRepository<MsisdnMgmt, Integer> {
    Optional<MsisdnMgmt> findByMsisdn(String msisdn);

    @Query("select new com.example.demo.dto.MsisdnMgmtDto(msisdnMgmt.id,msisdnMgmt.msisdn,msisdnMgmt.category,msisdnMgmt.seriesId,msisdnMgmt.isPrepaid,msisdnMgmt.isPostpaid,msisdnMgmt.isM2M,msisdnMgmt.isSpecialNo,msisdnMgmt.allocationDate,msisdnMgmt.status)from MsisdnMgmt msisdnMgmt")
    List<MsisdnMgmtDto> fetchAllMsisdnMgmtRecord();
}

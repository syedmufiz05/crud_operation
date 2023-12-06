package com.example.demo.repository;

import com.example.demo.dto.InventoryMgmtDto;
import com.example.demo.model.DeviceMgmt;
import com.example.demo.model.InventoryMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface InventoryMgmtRepository extends JpaRepository<InventoryMgmt, Integer> {
    @Query("select new com.example.demo.dto.InventoryMgmtDto(invMgmt.id,invMgmt.imsi,invMgmt.pImsi,invMgmt.batchId,invMgmt.vendorId,invMgmt.msisdn,invMgmt.status,invMgmt.provStatus,invMgmt.allocationDate,invMgmt.activationDate)from InventoryMgmt invMgmt")
    List<InventoryMgmtDto> fetchAllInventoriesMgmt();

    Optional<InventoryMgmt> findByImsi(String imsi);

    @Query("select invMgmt from InventoryMgmt invMgmt where (invMgmt.imsi) like LOWER(CONCAT('%', :keyword, '%')) or (invMgmt.pImsi) like LOWER(CONCAT('%', :keyword, '%')) or (invMgmt.msisdn) like LOWER(CONCAT('%', :keyword, '%'))")
    List<InventoryMgmt> searchItemsByName(@Param("keyword") String keyword);
}

package com.example.demo.repository;

import com.example.demo.dto.VendorMgmtDto;
import com.example.demo.model.VendorMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VendorMgmtRepository extends JpaRepository<VendorMgmt, Integer> {
    @Query("select new com.example.demo.dto.VendorMgmtDto(vm.id,vm.vendorName,vm.email,vm.contact,vm.address,vm.type,vm.identification,vm.batchPrefix,vm.registrationDate,vm.status) from VendorMgmt vm")
    List<VendorMgmtDto> fetchAllVendors();

    @Query("select vendorMgmt from VendorMgmt vendorMgmt where (vendorMgmt.vendorName) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.email) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.contact) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.address) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.type) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.identification) like LOWER(CONCAT('%', :keyword, '%')) or (vendorMgmt.batchPrefix) like LOWER(CONCAT('%', :keyword, '%'))")
    List<VendorMgmt> searchItemsByName(String keyword);
}

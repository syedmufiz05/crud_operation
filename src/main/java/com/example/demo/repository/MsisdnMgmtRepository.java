package com.example.demo.repository;

import com.example.demo.model.MsisdnMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MsisdnMgmtRepository extends JpaRepository<MsisdnMgmt, Integer> {
}

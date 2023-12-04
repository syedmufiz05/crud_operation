package com.example.demo.repository;

import com.example.demo.model.SimMgmt;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SimMgmtRepository extends JpaRepository<SimMgmt, Integer> {
}

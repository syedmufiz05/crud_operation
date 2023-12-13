package com.example.demo.repository;

import com.example.demo.model.HssProvNew;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface HssProvRepositoryNew extends JpaRepository<HssProvNew, Integer> {
    Optional<HssProvNew> findByImsiOrMsisdn(String imsi, String msisdn);
}

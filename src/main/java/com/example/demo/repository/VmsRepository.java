package com.example.demo.repository;

import com.example.demo.model.Vms;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VmsRepository extends JpaRepository<Vms, Integer> {

    Optional<Vms> findByMsisdn(String msisdn);

    void deleteByMsisdn(String msisdn);

}

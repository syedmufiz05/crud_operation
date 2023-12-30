package com.example.demo.repository;

import com.example.demo.model.PrepaidAccounts;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PrepaidAccountsRepository extends JpaRepository<PrepaidAccounts, Integer> {

    Optional<PrepaidAccounts> findByAccountId(Integer accountId);

    Optional<PrepaidAccounts> findByMsisdn(String msisdn);
}

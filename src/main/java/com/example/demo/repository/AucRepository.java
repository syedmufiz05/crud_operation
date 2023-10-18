package com.example.demo.repository;

import com.example.demo.model.Auc;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AucRepository extends JpaRepository<Auc, Integer> {
    Optional<Auc> findByImsi(String imsi);

    void deleteByImsi(String imsi);
}

package com.example.demo.repository;

import com.example.demo.model.AccessLogs;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccessLogsRepository extends JpaRepository<AccessLogs, Integer> {
}




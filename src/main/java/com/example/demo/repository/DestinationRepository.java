package com.example.demo.repository;

import com.example.demo.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    Optional<Destination> findByName(String name);
}

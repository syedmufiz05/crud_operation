package com.example.demo.repository;

import com.example.demo.dto.DestinationDto;
import com.example.demo.model.Destination;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DestinationRepository extends JpaRepository<Destination, Integer> {
    @Query("select new com.example.demo.dto.DestinationDto(dest.id,dest.name,dest.type,dest.remarks,dest.active,dest.accessLogs.id) from Destination dest")
    List<DestinationDto> fetchAllDestination();
}

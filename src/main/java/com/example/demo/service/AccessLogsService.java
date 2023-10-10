package com.example.demo.service;

import com.example.demo.model.AccessLogs;
import com.example.demo.repository.AccessLogsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccessLogsService {
    @Autowired
    private AccessLogsRepository accessLogsRepository;

    public void saveAccessLogs(AccessLogs accessLogs) {
        accessLogsRepository.save(accessLogs);
    }
}

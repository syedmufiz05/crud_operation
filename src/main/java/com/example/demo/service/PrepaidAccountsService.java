package com.example.demo.service;

import com.example.demo.dto.PrepaidAccountsDto;
import org.springframework.http.ResponseEntity;

public interface PrepaidAccountsService {
    ResponseEntity savePrepaidAccount(PrepaidAccountsDto prepaidAccountsDto);

    ResponseEntity editPrepaidAccount(Integer accountId, PrepaidAccountsDto prepaidAccountsDto);

    ResponseEntity deletePrepaidAccount(Integer accountId);

    ResponseEntity getPrepaidAccount(Integer accountId);
}

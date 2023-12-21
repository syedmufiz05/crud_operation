package com.example.demo.controller;

import com.example.demo.dto.PrepaidRoamingAccountsDto;
import com.example.demo.service.PrepaidRoamingAccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/prepaid/roaming/account")
public class PrepaidRoamingAccountController {
    @Autowired
    private PrepaidRoamingAccountsService prepaidRoamingAccountsService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<PrepaidRoamingAccountsDto> savePrepaidRoamingAccount(@RequestBody PrepaidRoamingAccountsDto prepaidRoamingAccountsDto) {
        return prepaidRoamingAccountsService.savePrepaidRoamingAccount(prepaidRoamingAccountsDto);
    }

    @RequestMapping(value = "/edit/{roaming_account_id}", method = RequestMethod.PUT)
    public ResponseEntity<PrepaidRoamingAccountsDto> editPrepaidRoamingAccount(@PathVariable("roaming_account_id") Integer roamingAccountId, @RequestBody PrepaidRoamingAccountsDto prepaidRoamingAccountsDto) {
        return prepaidRoamingAccountsService.editPrepaidRoamingAccount(roamingAccountId, prepaidRoamingAccountsDto);
    }

    @RequestMapping(value = "/delete/{roaming_account_id}", method = RequestMethod.DELETE)
    public ResponseEntity deletePrepaidRoamingAccount(@PathVariable("roaming_account_id") Integer roamingAccountId) {
        return prepaidRoamingAccountsService.deletePrepaidRoamingAccount(roamingAccountId);
    }

    @RequestMapping(value = "/get/{roaming_account_id}", method = RequestMethod.GET)
    public ResponseEntity<PrepaidRoamingAccountsDto> getPrepaidRoamingAccount(@PathVariable("roaming_account_id") Integer roamingAccountId) {
        return prepaidRoamingAccountsService.getPrepaidRoamingAccount(roamingAccountId);
    }
}


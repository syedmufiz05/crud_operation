package com.example.demo.service;

import com.example.demo.dto.DeductionDto;
import com.example.demo.dto.PrepaidAccountsDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.PrepaidAccounts;
import com.example.demo.repository.PrepaidAccountsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.math.BigDecimal;
import java.util.Optional;

@Service
public class PrepaidAccountsServiceImpl implements PrepaidAccountsService {
    @Autowired
    private PrepaidAccountsRepository prepaidAccountsRepository;

    @Override
    public ResponseEntity savePrepaidAccount(PrepaidAccountsDto prepaidAccountsDto) {
        Optional<PrepaidAccounts> prepaidAccount = prepaidAccountsRepository.findByAccountId(prepaidAccountsDto.getAccountId() != null ? prepaidAccountsDto.getAccountId() : 0);
        if (!prepaidAccount.isPresent()) {
            PrepaidAccounts prepaidAccountDb = new PrepaidAccounts();
            prepaidAccountDb.setCustomerId(prepaidAccountsDto.getCustomerId() != null ? prepaidAccountsDto.getCustomerId() : Integer.valueOf(""));
            prepaidAccountDb.setMsisdn(prepaidAccountsDto.getMsisdn() != null ? prepaidAccountsDto.getMsisdn() : "");
            prepaidAccountDb.setCsVoiceCallSeconds(prepaidAccountsDto.getCsVoiceCallSeconds() != null ? prepaidAccountsDto.getCsVoiceCallSeconds() : Integer.valueOf(""));
            prepaidAccountDb.setFourGDataOctets(prepaidAccountsDto.getFourGDataOctets() != null ? prepaidAccountsDto.getFourGDataOctets() : Integer.valueOf(""));
            prepaidAccountDb.setFiveGDataOctets(prepaidAccountsDto.getFiveGDataOctets() != null ? prepaidAccountsDto.getFiveGDataOctets() : Integer.valueOf(""));
            prepaidAccountDb.setVolteCallSeconds(prepaidAccountsDto.getVolteCallSeconds() != null ? prepaidAccountsDto.getVolteCallSeconds() : Integer.valueOf(""));
            prepaidAccountDb.setTotalDataOctetsAvailable(prepaidAccountsDto.getTotalDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalDataOctetsAvailable() : Integer.valueOf(""));
            prepaidAccountDb.setTotalInputDataOctetsAvailable(prepaidAccountsDto.getTotalInputDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalInputDataOctetsAvailable() : Integer.valueOf(""));
            prepaidAccountDb.setTotalOutputDataOctetsAvailable(prepaidAccountsDto.getTotalOutputDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalOutputDataOctetsAvailable() : Integer.valueOf(""));
            prepaidAccountDb.setTotalDataOctetsConsumed(prepaidAccountsDto.getTotalDataOctetsConsumed() != null ? prepaidAccountsDto.getTotalDataOctetsConsumed() : Integer.valueOf(""));
            prepaidAccountDb.setTotalCallSecondsAvailable(prepaidAccountsDto.getTotalCallSecondsAvailable() != null ? prepaidAccountsDto.getTotalCallSecondsAvailable() : Integer.valueOf(""));
            prepaidAccountDb.setTotalCallSecondsConsumed(prepaidAccountsDto.getTotalCallSecondsConsumed() != null ? prepaidAccountsDto.getTotalCallSecondsConsumed() : Integer.valueOf(""));
            prepaidAccountDb.setTotalSmsAvailable(prepaidAccountsDto.getTotalSmsAvailable() != null ? prepaidAccountsDto.getTotalSmsAvailable() : Integer.valueOf(""));
            prepaidAccountDb.setTotalSmsConsumed(prepaidAccountsDto.getTotalSmsConsumed() != null ? prepaidAccountsDto.getTotalSmsConsumed() : Integer.valueOf(""));
            prepaidAccountsRepository.save(prepaidAccountDb);
            PrepaidAccountsDto prepaidAccountsDtoNew = new PrepaidAccountsDto(prepaidAccountDb.getAccountId(), prepaidAccountDb.getCustomerId(), prepaidAccountDb.getMsisdn(), prepaidAccountDb.getCsVoiceCallSeconds(), prepaidAccountDb.getFourGDataOctets(), prepaidAccountDb.getFiveGDataOctets(), prepaidAccountDb.getVolteCallSeconds(), prepaidAccountDb.getTotalDataOctetsAvailable(), prepaidAccountDb.getTotalInputDataOctetsAvailable(), prepaidAccountDb.getTotalOutputDataOctetsAvailable(), prepaidAccountDb.getTotalDataOctetsConsumed(), prepaidAccountDb.getTotalCallSecondsAvailable(), prepaidAccountDb.getTotalCallSecondsConsumed(), prepaidAccountDb.getTotalSmsAvailable(), prepaidAccountDb.getTotalSmsConsumed());
            return new ResponseEntity(prepaidAccountsDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Account Id already exist"));
    }

    @Override
    public String saveDeductionRecord(DeductionDto deductionDto) {
        PrepaidAccounts prepaidAccounts = new PrepaidAccounts();
        prepaidAccounts.setTotalDataOctetsAvailable(convertGigabytesToBytes(deductionDto.getConsumedOctets().getTotal()));
        prepaidAccounts.setTotalInputDataOctetsAvailable(convertGigabytesToBytes(deductionDto.getConsumedOctets().getInput()));
        prepaidAccounts.setTotalOutputDataOctetsAvailable(convertGigabytesToBytes(deductionDto.getConsumedOctets().getOutput()));
        prepaidAccounts.setTotalCallSecondsConsumed(convertGigabytesToBytes(deductionDto.getConsumedTimeSeconds()));
        prepaidAccountsRepository.save(prepaidAccounts);
        return "Saved successfully";
    }

    @Override
    public ResponseEntity editPrepaidAccount(Integer accountId, PrepaidAccountsDto prepaidAccountsDto) {
        Optional<PrepaidAccounts> prepaidAccount = prepaidAccountsRepository.findByAccountId(accountId);
        if (prepaidAccount.isPresent()) {
            PrepaidAccounts prepaidAccountDb = prepaidAccount.get();
            prepaidAccountDb.setCustomerId(prepaidAccountsDto.getCustomerId() != null ? prepaidAccountsDto.getCustomerId() : prepaidAccountDb.getCustomerId());
            prepaidAccountDb.setMsisdn(prepaidAccountsDto.getMsisdn() != null ? prepaidAccountsDto.getMsisdn() : prepaidAccountDb.getMsisdn());
            prepaidAccountDb.setCsVoiceCallSeconds(prepaidAccountsDto.getCsVoiceCallSeconds() != null ? prepaidAccountsDto.getCsVoiceCallSeconds() : prepaidAccountDb.getCsVoiceCallSeconds());
            prepaidAccountDb.setFourGDataOctets(prepaidAccountsDto.getFourGDataOctets() != null ? prepaidAccountsDto.getFourGDataOctets() : prepaidAccountDb.getFourGDataOctets());
            prepaidAccountDb.setFiveGDataOctets(prepaidAccountsDto.getFiveGDataOctets() != null ? prepaidAccountsDto.getFiveGDataOctets() : prepaidAccountDb.getFiveGDataOctets());
            prepaidAccountDb.setVolteCallSeconds(prepaidAccountsDto.getVolteCallSeconds() != null ? prepaidAccountsDto.getVolteCallSeconds() : prepaidAccountDb.getVolteCallSeconds());
            prepaidAccountDb.setTotalDataOctetsAvailable(prepaidAccountsDto.getTotalDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalDataOctetsAvailable() : prepaidAccountDb.getTotalDataOctetsAvailable());
            prepaidAccountDb.setTotalInputDataOctetsAvailable(prepaidAccountsDto.getTotalInputDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalInputDataOctetsAvailable() : prepaidAccountDb.getTotalInputDataOctetsAvailable());
            prepaidAccountDb.setTotalOutputDataOctetsAvailable(prepaidAccountsDto.getTotalOutputDataOctetsAvailable() != null ? prepaidAccountsDto.getTotalOutputDataOctetsAvailable() : prepaidAccountDb.getTotalOutputDataOctetsAvailable());
            prepaidAccountDb.setTotalDataOctetsConsumed(prepaidAccountsDto.getTotalDataOctetsConsumed() != null ? prepaidAccountsDto.getTotalDataOctetsConsumed() : prepaidAccountDb.getTotalDataOctetsConsumed());
            prepaidAccountDb.setTotalCallSecondsAvailable(prepaidAccountsDto.getTotalCallSecondsAvailable() != null ? prepaidAccountsDto.getTotalCallSecondsAvailable() : prepaidAccountDb.getTotalCallSecondsAvailable());
            prepaidAccountDb.setTotalCallSecondsConsumed(prepaidAccountsDto.getTotalCallSecondsConsumed() != null ? prepaidAccountsDto.getTotalCallSecondsConsumed() : prepaidAccountDb.getTotalCallSecondsConsumed());
            prepaidAccountDb.setTotalSmsAvailable(prepaidAccountsDto.getTotalSmsAvailable() != null ? prepaidAccountsDto.getTotalSmsAvailable() : prepaidAccountDb.getTotalSmsAvailable());
            prepaidAccountDb.setTotalSmsConsumed(prepaidAccountsDto.getTotalSmsConsumed() != null ? prepaidAccountsDto.getTotalSmsConsumed() : prepaidAccountDb.getTotalSmsConsumed());
            prepaidAccountsRepository.save(prepaidAccountDb);
            PrepaidAccountsDto prepaidAccountsDtoNew = new PrepaidAccountsDto(prepaidAccountDb.getAccountId(), prepaidAccountDb.getCustomerId(), prepaidAccountDb.getMsisdn(), prepaidAccountDb.getCsVoiceCallSeconds(), prepaidAccountDb.getFourGDataOctets(), prepaidAccountDb.getFiveGDataOctets(), prepaidAccountDb.getVolteCallSeconds(), prepaidAccountDb.getTotalDataOctetsAvailable(), prepaidAccountDb.getTotalInputDataOctetsAvailable(), prepaidAccountDb.getTotalOutputDataOctetsAvailable(), prepaidAccountDb.getTotalDataOctetsConsumed(), prepaidAccountDb.getTotalCallSecondsAvailable(), prepaidAccountDb.getTotalCallSecondsConsumed(), prepaidAccountDb.getTotalSmsAvailable(), prepaidAccountDb.getTotalSmsConsumed());
            return new ResponseEntity(prepaidAccountsDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid account Id"));
    }

    @Transactional
    @Override
    public ResponseEntity deletePrepaidAccount(Integer accountId) {
        Optional<PrepaidAccounts> prepaidAccounts = prepaidAccountsRepository.findByAccountId(accountId);
        if (prepaidAccounts.isPresent()) {
            prepaidAccountsRepository.deleteById(accountId);
            return ResponseEntity.status(HttpStatus.OK).body(new CustomMessage(HttpStatus.OK.value(), "Deleted Successfully"));
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Account Id"));
    }

    @Override
    public ResponseEntity getPrepaidAccount(Integer accountId) {
        Optional<PrepaidAccounts> prepaidAccounts = prepaidAccountsRepository.findByAccountId(accountId);
        if (prepaidAccounts.isPresent()) {
            PrepaidAccounts prepaidAccountsDb = prepaidAccounts.get();
            PrepaidAccountsDto prepaidAccountsDto = new PrepaidAccountsDto();
            prepaidAccountsDto.setAccountId(prepaidAccountsDb.getAccountId());
            prepaidAccountsDto.setCustomerId(prepaidAccountsDb.getCustomerId());
            prepaidAccountsDto.setMsisdn(prepaidAccountsDb.getMsisdn());
            prepaidAccountsDto.setCsVoiceCallSeconds(prepaidAccountsDb.getCsVoiceCallSeconds());
            prepaidAccountsDto.setFourGDataOctets(prepaidAccountsDb.getFourGDataOctets());
            prepaidAccountsDto.setFiveGDataOctets(prepaidAccountsDb.getFiveGDataOctets());
            prepaidAccountsDto.setVolteCallSeconds(prepaidAccountsDb.getVolteCallSeconds());
            prepaidAccountsDto.setTotalDataOctetsAvailable(prepaidAccountsDb.getTotalDataOctetsAvailable());
            prepaidAccountsDto.setTotalInputDataOctetsAvailable(prepaidAccountsDb.getTotalInputDataOctetsAvailable());
            prepaidAccountsDto.setTotalOutputDataOctetsAvailable(prepaidAccountsDb.getTotalOutputDataOctetsAvailable());
            prepaidAccountsDto.setTotalDataOctetsConsumed(prepaidAccountsDb.getTotalDataOctetsConsumed());
            prepaidAccountsDto.setTotalCallSecondsAvailable(prepaidAccountsDb.getTotalCallSecondsAvailable());
            prepaidAccountsDto.setTotalCallSecondsConsumed(prepaidAccountsDb.getTotalCallSecondsConsumed());
            prepaidAccountsDto.setTotalSmsAvailable(prepaidAccountsDb.getTotalSmsAvailable());
            prepaidAccountsDto.setTotalSmsConsumed(prepaidAccountsDb.getTotalSmsConsumed());
            return new ResponseEntity<>(prepaidAccountsDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Account Id"));
    }

    public static long convertGigabytesToBytes(Long gigabytes) {
        // 1 GB = 1024^3 bytes
        BigDecimal gigabytesBigDecimal = new BigDecimal(String.valueOf(gigabytes));
        BigDecimal bytesBigDecimal = gigabytesBigDecimal.multiply(BigDecimal.valueOf(Math.pow(1024, 3)));

        // Convert to long, rounding down to the nearest whole number
        return bytesBigDecimal.longValue();
    }
}

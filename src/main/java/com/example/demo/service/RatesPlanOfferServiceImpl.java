package com.example.demo.service;

import com.example.demo.dto.RatesPlanOfferDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.RatesPlanOffer;
import com.example.demo.repository.RatesPlanOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatesPlanOfferServiceImpl implements RatesPlanOfferService {
    @Autowired
    private RatesPlanOfferRepository ratesPlanOfferRepository;

    @Override
    public ResponseEntity saveRatesPlanDetail(RatesPlanOfferDto ratesPlanOfferDto) {
        Optional<RatesPlanOffer> ratesPlanOffer = ratesPlanOfferRepository.findById(ratesPlanOfferDto.getRatesPlanOfferId() != null ? ratesPlanOfferDto.getRatesPlanOfferId() : 0);
        if (!ratesPlanOffer.isPresent()) {
            RatesPlanOffer ratesPlanOfferDb = new RatesPlanOffer();
            ratesPlanOfferDb.setName(ratesPlanOfferDto.getName() != null ? ratesPlanOfferDto.getName() : "");
            ratesPlanOfferDb.setPeriod(ratesPlanOfferDto.getPeriod() != null ? ratesPlanOfferDto.getPeriod() : Integer.valueOf(""));
            ratesPlanOfferDb.setDescription(ratesPlanOfferDto.getDescription() != null ? ratesPlanOfferDto.getDescription() : "");
            ratesPlanOfferDb.setActive(ratesPlanOfferDto.getActive() != null ? ratesPlanOfferDto.getActive() : false);
            ratesPlanOfferRepository.save(ratesPlanOfferDb);
            RatesPlanOfferDto ratesPlanOfferDtoNew = new RatesPlanOfferDto(ratesPlanOfferDb.getId(), ratesPlanOfferDb.getName(), ratesPlanOfferDb.getPeriod(), ratesPlanOfferDb.getDescription(), ratesPlanOfferDb.getActive());
            return new ResponseEntity<>(ratesPlanOfferDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Rating Plan Id already exist"));
    }

    @Override
    public List<String> getAllRatesPlans() {
        List<RatesPlanOfferDto> ratesPlanOfferDtoList = ratesPlanOfferRepository.fetchAllRatesPlan();
        List<String> ratesPlanVoucherList = new ArrayList<>();
        for (RatesPlanOfferDto ratesPlanOfferDto : ratesPlanOfferDtoList) {
            if (ratesPlanOfferDto.getName().equals("1GB")) {
                ratesPlanOfferDto.setDescription("1GB/Month");
                ratesPlanVoucherList.add(ratesPlanOfferDto.getDescription());
            }
            if (ratesPlanOfferDto.getName().equals("2GB")) {
                ratesPlanOfferDto.setDescription("2GB/Month");
                ratesPlanVoucherList.add(ratesPlanOfferDto.getDescription());
            }
            if (ratesPlanOfferDto.getName().equals("3GB")) {
                ratesPlanOfferDto.setDescription("3GB/Month");
                ratesPlanVoucherList.add(ratesPlanOfferDto.getDescription());
            }
            if (ratesPlanOfferDto.getName().equals("4GB")) {
                ratesPlanOfferDto.setDescription("4GB/Month");
                ratesPlanVoucherList.add(ratesPlanOfferDto.getDescription());
            }
            if (ratesPlanOfferDto.getName().equals("5GB")) {
                ratesPlanOfferDto.setDescription("5GB/Month");
                ratesPlanVoucherList.add(ratesPlanOfferDto.getDescription());
            }
        }
        return ratesPlanVoucherList;
    }
}

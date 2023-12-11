package com.example.demo.service;

import com.example.demo.dto.RatesOfferDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.RatesOffer;
import com.example.demo.repository.RatesOfferRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class RatesOfferServiceImpl implements RatesOfferService {
    @Autowired
    private RatesOfferRepository ratesOfferRepository;

    @Override
    public ResponseEntity saveRatesOffer(RatesOfferDto ratesOfferDto) {
        Optional<RatesOffer> ratesOffer = ratesOfferRepository.findById(ratesOfferDto.getRatesId() != null ? ratesOfferDto.getRatesId() : 0);
        if (!ratesOffer.isPresent()) {
            RatesOffer ratesOfferDb = new RatesOffer();
            ratesOfferDb.setPrice(ratesOfferDto.getPrice() != null ? ratesOfferDto.getPrice() : Integer.valueOf(""));
            ratesOfferDb.setPriceType(ratesOfferDto.getPriceType() != null ? ratesOfferDto.getPriceType() : "");
            ratesOfferDb.setPeriod(ratesOfferDto.getPeriod() != null ? ratesOfferDto.getPeriod() : Integer.valueOf(""));
            ratesOfferDb.setDescription(ratesOfferDto.getDescription() != null ? ratesOfferDto.getDescription() : "");
            ratesOfferRepository.save(ratesOfferDb);
            RatesOfferDto ratesOfferDtoNew = new RatesOfferDto(ratesOfferDb.getId(), ratesOfferDb.getPrice(), ratesOfferDb.getPriceType(), ratesOfferDb.getPeriod(), ratesOfferDb.getDescription());
            return new ResponseEntity<>(ratesOfferDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Rates Id already exist"));
    }

    @Override
    public List<String> getAllRatesOffer() {
        List<RatesOfferDto> ratesOfferDtoList = ratesOfferRepository.fecthAllRatesOffer();
        List<String> ratesVoucherList = new ArrayList<>();
        for (RatesOfferDto ratesOfferDto : ratesOfferDtoList) {
            if (ratesOfferDto.getPrice() == 99) {
                ratesOfferDto.setDescription(ratesOfferDto.getPrice() + " Rs/" + "Month");
                ratesVoucherList.add(ratesOfferDto.getDescription());
            }
            if (ratesOfferDto.getPrice() == 199) {
                ratesOfferDto.setDescription(ratesOfferDto.getPrice() + " Rs/" + "Month");
                ratesVoucherList.add(ratesOfferDto.getDescription());
            }
            if (ratesOfferDto.getPrice() == 299) {
                ratesOfferDto.setDescription(ratesOfferDto.getPrice() + " Rs/" + "Month");
                ratesVoucherList.add(ratesOfferDto.getDescription());
            }
            if (ratesOfferDto.getPrice() == 399) {
                ratesOfferDto.setDescription(ratesOfferDto.getPrice() + " Rs/" + "Month");
                ratesVoucherList.add(ratesOfferDto.getDescription());
            }
            if (ratesOfferDto.getPrice() == 499) {
                ratesOfferDto.setDescription(ratesOfferDto.getPrice() + " Rs/" + "Month");
                ratesVoucherList.add(ratesOfferDto.getDescription());
            }
        }
        return ratesVoucherList;
    }
}

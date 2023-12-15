package com.example.demo.service;

import com.example.demo.dto.RatingProfileDto;
import com.example.demo.dto.RatingProfileVoucherDto;
import com.example.demo.exception.CustomMessage;
import com.example.demo.model.Category;
import com.example.demo.model.RatingPlan;
import com.example.demo.model.RatingProfile;
import com.example.demo.model.RatingProfileVoucher;
import com.example.demo.repository.CategoryRepository;
import com.example.demo.repository.RatingPlanRepository;
import com.example.demo.repository.RatingProfileRepository;
import com.example.demo.repository.RatingProfileVoucherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

@Service
public class RatingProfileServiceImpl implements RatingProfileService {
    @Autowired
    private RatingProfileRepository ratingProfileRepository;
    @Autowired
    private CategoryRepository categoryRepository;
    @Autowired
    private RatingPlanRepository ratingPlanRepository;
    @Autowired
    private RatingProfileVoucherRepository ratingProfileVoucherRepository;

    @Override
    public RatingProfileDto createRatingProfile(RatingProfileDto ratingProfileDto, String authToken) {
        Optional<Category> category = categoryRepository.findByName(ratingProfileDto.getCategoryName());
        Optional<RatingPlan> ratingPlan = ratingPlanRepository.findById(ratingProfileDto.getRatingPlanId());
        if (category.isPresent() && ratingPlan.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        } else if (category.isPresent()) {
            Category categoryDb = category.get();
            RatingPlan ratingPlanDb = new RatingPlan();
            ratingPlanRepository.save(ratingPlanDb);
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        } else if (ratingPlan.isPresent()) {
            Category categoryDb = new Category();
            categoryRepository.save(categoryDb);
            RatingPlan ratingPlanDb = ratingPlan.get();
            RatingProfile ratingProfile = new RatingProfile();
            ratingProfile.setId(1212);
            ratingProfile.setCategory(categoryDb);
            ratingProfile.setRatingPlan(ratingPlanDb);
            ratingProfile.setCallingParty(ratingProfileDto.getCallingParty() != null ? ratingProfileDto.getCallingParty() : "");
            ratingProfileRepository.save(ratingProfile);
            return new RatingProfileDto(ratingProfile.getId(), categoryDb.getName(), ratingProfile.getCallingParty(), ratingPlanDb.getRatingPlanId());
        }
        Category categoryNew = new Category();
        categoryNew.setName(ratingProfileDto.getCategoryName());
        categoryRepository.save(categoryNew);
        RatingPlan ratingPlanNew = new RatingPlan();
        ratingPlanRepository.save(ratingPlanNew);
        RatingProfile ratingProfile = new RatingProfile();
        ratingProfile.setId(1212);
        ratingProfile.setCallingParty(ratingProfileDto.getCallingParty());
        ratingProfile.setCategory(categoryNew);
        ratingProfile.setRatingPlan(ratingPlanNew);
        ratingProfileRepository.save(ratingProfile);
        return new RatingProfileDto(ratingProfile.getId(), categoryNew.getName(), ratingProfile.getCallingParty(), ratingPlanNew.getRatingPlanId());
    }

    @Override
    public ResponseEntity createRatingProfileVoucher(RatingProfileVoucherDto ratingProfileVoucherDto) {
        Optional<RatingProfileVoucher> ratingProfileVoucherDb = ratingProfileVoucherRepository.findById(ratingProfileVoucherDto.getRatingProfileId() != null ? ratingProfileVoucherDto.getRatingProfileId() : 0);
        if (!ratingProfileVoucherDb.isPresent()) {
            RatingProfileVoucher ratingProfileVoucher = new RatingProfileVoucher();
            ratingProfileVoucher.setCategoryOffer(ratingProfileVoucherDto.getCategoryNameDtoList().toString());
            ratingProfileVoucher.setRatesOffer(ratingProfileVoucherDto.getRatesOfferDtoList().toString());
            ratingProfileVoucher.setRatesPlanOffer(ratingProfileVoucherDto.getRatingPlanDtoList().toString());
            ratingProfileVoucherRepository.save(ratingProfileVoucher);
            List<String> categoryList = convertStringToList(ratingProfileVoucher.getCategoryOfferList());
            List<String> ratesOfferList = convertStringToList(ratingProfileVoucher.getRatesOfferList());
            List<String> ratesPlanOfferList = convertStringToList(ratingProfileVoucher.getRatesPlanOfferList());
            RatingProfileVoucherDto ratingProfileDtoNew = new RatingProfileVoucherDto(ratingProfileVoucher.getId(), categoryList, ratesOfferList, ratesPlanOfferList);
            return new ResponseEntity<>(ratingProfileDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.CONFLICT).body(new CustomMessage(HttpStatus.CONFLICT.value(), "Duplicate Rating Profile Id"));
    }

    @Override
    public ResponseEntity editRatingProfileVoucher(RatingProfileVoucherDto ratingProfileVoucherDto) {
        Optional<RatingProfileVoucher> ratingProfileVoucher = ratingProfileVoucherRepository.findById(ratingProfileVoucherDto.getRatingProfileId());
        if (ratingProfileVoucher.isPresent()) {
            RatingProfileVoucher ratingProfileVoucherDb = ratingProfileVoucher.get();
            ratingProfileVoucherDb.setCategoryOffer(ratingProfileVoucherDto.getCategoryNameDtoList().toString() != null ? ratingProfileVoucherDto.getCategoryNameDtoList().toString() : ratingProfileVoucherDb.getCategoryOfferList());
            ratingProfileVoucherDb.setRatesOffer(ratingProfileVoucherDto.getRatesOfferDtoList().toString() != null ? ratingProfileVoucherDto.getRatesOfferDtoList().toString() : ratingProfileVoucherDb.getRatesOfferList());
            ratingProfileVoucherDb.setRatesPlanOffer(ratingProfileVoucherDto.getRatingPlanDtoList().toString() != null ? ratingProfileVoucherDto.getRatingPlanDtoList().toString() : ratingProfileVoucherDb.getRatesPlanOfferList());
            ratingProfileVoucherRepository.save(ratingProfileVoucherDb);
            List<String> categoryList = convertStringToList(ratingProfileVoucherDb.getCategoryOfferList());
            List<String> ratesOfferList = convertStringToList(ratingProfileVoucherDb.getRatesOfferList());
            List<String> ratesPlanOfferList = convertStringToList(ratingProfileVoucherDb.getRatesPlanOfferList());
            RatingProfileVoucherDto ratingProfileVoucherDtoNew = new RatingProfileVoucherDto(ratingProfileVoucherDb.getId(), categoryList, ratesOfferList, ratesPlanOfferList);
            return new ResponseEntity<>(ratingProfileVoucherDtoNew, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Invalid Rating Profile Id"));
    }

    @Override
    public ResponseEntity getRatingProfile(Integer ratingProfileId) {
        Optional<RatingProfile> ratingProfile = ratingProfileRepository.findById(ratingProfileId);
        if (ratingProfile.isPresent()) {
            RatingProfile ratingProfileDb = ratingProfile.get();
            RatingProfileDto ratingProfileDto = new RatingProfileDto();
            ratingProfileDto.setRatingProfileId(ratingProfileDb.getId());
            ratingProfileDto.setCategoryName(ratingProfileDb.getCategory().getName());
            ratingProfileDto.setCallingParty(ratingProfileDb.getCallingParty());
            ratingProfileDto.setRatingPlanId(ratingProfileDb.getRatingPlan().getRatingPlanId());
            return new ResponseEntity<>(ratingProfileDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Rating Profile Id does n't exist"));
    }

    @Override
    public List<RatingProfileDto> getAllRatingProfile() {
        return ratingProfileRepository.fetchAll();
    }

    @Override
    public ResponseEntity editRatingProfile(Integer ratingProfileId, String callingParty) {
        Optional<RatingProfile> ratingProfile = ratingProfileRepository.findById(ratingProfileId);
        if (ratingProfile.isPresent()) {
            RatingProfile ratingProfileDb = ratingProfile.get();
            ratingProfileDb.setCallingParty(!callingParty.isEmpty() ? callingParty : ratingProfileDb.getCallingParty());
            ratingProfileRepository.save(ratingProfileDb);
            RatingProfileDto ratingProfileDto = new RatingProfileDto(ratingProfileDb.getId(), ratingProfileDb.getCategory().getName(), ratingProfileDb.getCallingParty(), ratingProfileDb.getRatingPlan().getRatingPlanId());
            return new ResponseEntity<>(ratingProfileDto, HttpStatus.OK);
        }
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new CustomMessage(HttpStatus.NOT_FOUND.value(), "Rating Profile Id does n't exist"));
    }

    @Override
    public String deleteRatingProfile(Integer ratingProfileId) {
        ratingProfileRepository.deleteById(ratingProfileId);
        return "Deleted successfully";
    }

    private static List<String> convertStringToList(String data) {
        String[] dataArray = data.split(",");
        return Arrays.asList(dataArray);
    }
}

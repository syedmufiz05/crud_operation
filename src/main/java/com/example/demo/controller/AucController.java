package com.example.demo.controller;

import com.example.demo.dto.AucDto;
import com.example.demo.service.AucServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
@RequestMapping("/api/auc/detail")
@CrossOrigin("http://localhost:5173/")
public class AucController {
    @Autowired
    private AucServiceImpl aucService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<AucDto> saveAucDetails(@RequestBody AucDto aucDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return aucService.saveAucDetails(aucDto, authToken);
    }

    @RequestMapping(value = "/get/{imsi}", method = RequestMethod.GET)
    public ResponseEntity<AucDto> getAucDetails(@PathVariable("imsi") String imsi) {
        return aucService.getAucDetails(imsi);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<AucDto> getAllAucDetails() {
        return aucService.getAllAucDetails();
    }

    @RequestMapping(value = "/update", method = RequestMethod.PUT)
    public ResponseEntity<AucDto> updateAucDetails(@RequestParam String imsi, @RequestBody AucDto aucDto) throws JsonProcessingException {
        return aucService.updateAucDetails(imsi, aucDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public void deleteAucDetails(@RequestParam String imsi) {
        aucService.deleteAucDetails(imsi);
    }

}

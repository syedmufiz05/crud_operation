package com.example.demo.controller;

import com.example.demo.dto.AucDto;
import com.example.demo.service.AucServiceImpl;
import com.fasterxml.jackson.core.JsonProcessingException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("/api/v2")
public class AucController {
    @Autowired
    private AucServiceImpl aucService;

    @RequestMapping(value = "/save/auc/detail", method = RequestMethod.POST)
    public String saveAucDetails(@RequestBody AucDto aucDto, HttpServletRequest httpServletRequest) throws JsonProcessingException {
        String authToken = httpServletRequest.getHeader("Authorization").replace("Bearer", "");
        return aucService.saveAucDetails(aucDto, authToken);
    }

    @RequestMapping(value = "/update/auc/detail", method = RequestMethod.PUT)
    public String updateAucDetails(@RequestParam String imsi, @RequestBody AucDto aucDto) throws JsonProcessingException {
        return aucService.updateAucDetails(imsi, aucDto);
    }

    @RequestMapping(value = "/delete/auc/detail", method = RequestMethod.DELETE)
    public void deleteAucDetails(@RequestParam String imsi) {
        aucService.deleteAucDetails(imsi);
    }

}

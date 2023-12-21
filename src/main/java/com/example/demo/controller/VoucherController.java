package com.example.demo.controller;

import com.example.demo.dto.VouchersDto;
import com.example.demo.service.VouchersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/voucher/detail")
public class VoucherController {
    @Autowired
    private VouchersService vouchersService;

    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public ResponseEntity<VouchersDto> saveVoucher(@RequestBody VouchersDto vouchersDto) {
        return vouchersService.saveVoucher(vouchersDto);
    }

    @RequestMapping(value = "/get", method = RequestMethod.GET)
    public ResponseEntity<VouchersDto> getVoucher(@RequestParam("voucher_no") String voucherNo) {
        return vouchersService.getVoucher(voucherNo);
    }

    @RequestMapping(value = "/get/all", method = RequestMethod.GET)
    public List<VouchersDto> getAllVoucher() {
        return vouchersService.getAllVoucher();
    }

    @RequestMapping(value = "/edit", method = RequestMethod.PUT)
    public ResponseEntity<VouchersDto> editVoucher(@RequestParam("voucher_no") String voucherNo, @RequestBody VouchersDto vouchersDto) {
        return vouchersService.editVoucher(voucherNo, vouchersDto);
    }

    @RequestMapping(value = "/delete", method = RequestMethod.DELETE)
    public ResponseEntity deleteVoucher(@RequestParam("voucher_no") String voucherNo) {
        return vouchersService.deleteVoucher(voucherNo);
    }
}

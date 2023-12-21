package com.example.demo.service;

import com.example.demo.dto.VouchersDto;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface VouchersService {
    public ResponseEntity saveVoucher(VouchersDto vouchersDto);

    public ResponseEntity editVoucher(String voucherNo, VouchersDto vouchersDto);

    public ResponseEntity deleteVoucher(String voucherNo);

    public ResponseEntity getVoucher(String voucherNo);

    public List<VouchersDto> getAllVoucher();
}

package com.example.demo.service;

import com.example.demo.dto.VouchersDto;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

public interface VouchersService {
    public ResponseEntity saveVoucher(VouchersDto vouchersDto);

    public ResponseEntity editVoucher(String voucherNo, VouchersDto vouchersDto);

    public ResponseEntity deleteVoucher(String voucherNo);
}

package com.example.demo.repository;

import com.example.demo.dto.VouchersDtoList;
import com.example.demo.model.Vouchers;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface VouchersRepository extends JpaRepository<Vouchers, Integer> {
    Optional<Vouchers> findByVoucherNo(String voucherNo);

    @Query("select new com.example.demo.dto.VouchersDtoList(voucher.id,voucher.activatedDate,voucher.amount,voucher.batchId,voucher.createdDate,voucher.currency,voucher.expiryDate,voucher.groupCode,voucher.instructionId,voucher.payeeFunctionalId,voucher.registeringInstitutionId,voucher.requestId,voucher.serialNo,voucher.status,voucher.voucherNo) from Vouchers voucher")
    List<VouchersDtoList> fetchAllVouchers();
}

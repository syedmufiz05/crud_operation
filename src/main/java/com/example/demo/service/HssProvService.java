package com.example.demo.service;

import com.example.demo.dto.HssProvDto;
import com.example.demo.dto.ReqPayloadDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.HssProv;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.HssProvRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.Optional;

@Service
public class HssProvService {
    @Autowired
    @Qualifier("hssProvRepository")
    private HssProvRepository hssProvRepository;
    @Autowired
    @Qualifier("accessLogsRepository")
    private AccessLogsRepository accessLogsRepository;

    public String saveHssProv(HssProv hssProv, String authToken) {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        hssProv.setAccessLogs(accessLogs);
        hssProvRepository.save(hssProv);
        return saveAccessRequestPayload(hssProv, accessLogs);
    }


    @Transactional
    public String deleteHssProv(String imsi, String msisdn) {
        if (imsi.isEmpty() || msisdn.isEmpty()) {
            hssProvRepository.deleteByImsiOrMsisdn(imsi, msisdn);
            return "Successfully deleted...";
        }
        return "Imsi or Msisdn Id is empty";
    }

    @Transactional
    public String updateHssProv(String imsi, String msisdn, HssProvDto hssProvDto) {
        Optional<HssProv> hssProv = hssProvRepository.findByImsiOrMsisdn(imsi, msisdn);
        if (hssProv.isPresent()) {
            HssProv hssProvDb = hssProv.get();
            hssProvDb.setImsFlag(hssProvDto.getImsiFlag());
            hssProvDb.setNam(hssProvDto.getNam());
            hssProvDb.setOdb(hssProvDto.getOdb());
            hssProvDb.setBaoc(hssProvDto.getBaoc());
            hssProvDb.setBoic(hssProvDto.getBoic());
            hssProvDb.setOsb1(hssProvDto.getOsb1());
            hssProvDb.setOsb2(hssProvDto.getOsb2());
            hssProvDb.setBaic(hssProvDto.getBaic());
            hssProvDb.setRoaming(hssProvDto.getRoaming());
            hssProvDb.setBearerService(hssProvDto.getBearerService());
            hssProvDb.setTelephone(hssProvDto.getTelephone());
            hssProvDb.setSms(hssProvDto.getSms());
            hssProvDb.setCfuA(hssProvDto.getCfuA());
            hssProvDb.setCfuR(hssProvDto.getCfuR());
            hssProvDb.setCfuP(hssProvDto.getCfuP());
            hssProvDb.setCfbP(hssProvDto.getCfbP());
            hssProvDb.setCfnryP(hssProvDto.getCfnryP());
            hssProvDb.setCfnryT(hssProvDto.getCfnryT());
            hssProvDb.setCfnrcP(hssProvDto.getCfnrcP());
            hssProvDb.setCwA(hssProvDto.getCwA());
            hssProvDb.setCwP(hssProvDto.getCwP());
            hssProvDb.setChP(hssProvDto.getChP());
            hssProvDb.setCamel(hssProvDto.getCamel());
            hssProvDb.setOCsi(hssProvDto.getOCsi());
            hssProvDb.setTCsi(hssProvDto.getTCsi());
            hssProvDb.setSsCsi(hssProvDto.getSsCsi());
            hssProvDb.setSmsCsi(hssProvDto.getSmsCsi());
            hssProvDb.setOCsiScfNo(hssProvDto.getOCsiScfNo());
            hssProvDb.setTCsiScfNo(hssProvDto.getTCsiScfNo());
            hssProvDb.setSsCsiScfNo(hssProvDto.getSsCsiScfNo());
            hssProvDb.setSmsSciScfNo(hssProvDto.getSmsSciScfNo());
            hssProvDb.setGprsFlag(hssProvDto.getGprsFlag());
            hssProvDb.setEpsFlag(hssProvDto.getEpsFlag());
            hssProvDb.setArd(hssProvDto.getArd());
            hssProvDb.setEpsUserTpl(hssProvDto.getEpsUserTpl());
            hssProvDb.setContextD(hssProvDto.getContextD());
            hssProvDb.setApnCtxtList(hssProvDto.getApnCtxtList());
            hssProvDb.setImsFlag(hssProvDto.getImsFlag());
            hssProvDb.setSubscriberProfId(hssProvDto.getSubscriberProfId());
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(hssProv.get().getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogs = accessLogsDb.get();
            accessLogs.setAccessDateTime(new Date());
            accessLogs.setReqPayload(updateAccessRequestPayload(hssProvDb, accessLogs));
            accessLogsRepository.save(accessLogs);
            hssProvRepository.save(hssProvDb);
            return "Successfully updated...";
        }
        return "Invalid Imsi or Msisdn Id";
    }

    private String saveAccessRequestPayload(HssProv hssProv, AccessLogs accessLogs) {
        accessLogs.setReqPayload(String.valueOf(new ReqPayloadDto(hssProv.getHssprovId(), hssProv.getImsi(), hssProv.getImsiFlag(), hssProv.getMsisdn(), hssProv.getNam(), hssProv.getOdb(), hssProv.getBaoc(), hssProv.getBoic(), hssProv.getOsb1(), hssProv.getOsb2(), hssProv.getBaic(), hssProv.getRoaming(), hssProv.getBearerService(), hssProv.getTelephone(), hssProv.getSms(), hssProv.getCfuA(), hssProv.getCfuR(), hssProv.getCfuP(), hssProv.getCfbP(), hssProv.getCfnryP(), hssProv.getCfnryT(), hssProv.getCfnrcP(), hssProv.getCwA(), hssProv.getCwP(), hssProv.getChP(), hssProv.getCamel(), hssProv.getOCsi(), hssProv.getTCsi(), hssProv.getSsCsi(), hssProv.getSmsCsi(), hssProv.getOCsiScfNo(), hssProv.getTCsiScfNo(), hssProv.getSsCsiScfNo(), hssProv.getSmsSciScfNo(), hssProv.getGprsFlag(), hssProv.getEpsFlag(), hssProv.getArd(), hssProv.getEpsUserTpl(), hssProv.getContextD(), hssProv.getApnCtxtList(), hssProv.getImsFlag(), hssProv.getSubscriberProfId(), hssProv.getAccessLogs().getIdAccessLogsId())));
        accessLogsRepository.save(accessLogs);
        return "Successfully saved...";
    }

    private String updateAccessRequestPayload(HssProv hssProv, AccessLogs accessLogs) {
        accessLogs.setReqPayload(String.valueOf(new ReqPayloadDto(hssProv.getHssprovId(), hssProv.getImsi(), hssProv.getImsiFlag(), hssProv.getMsisdn(), hssProv.getNam(), hssProv.getOdb(), hssProv.getBaoc(), hssProv.getBoic(), hssProv.getOsb1(), hssProv.getOsb2(), hssProv.getBaic(), hssProv.getRoaming(), hssProv.getBearerService(), hssProv.getTelephone(), hssProv.getSms(), hssProv.getCfuA(), hssProv.getCfuR(), hssProv.getCfuP(), hssProv.getCfbP(), hssProv.getCfnryP(), hssProv.getCfnryT(), hssProv.getCfnrcP(), hssProv.getCwA(), hssProv.getCwP(), hssProv.getChP(), hssProv.getCamel(), hssProv.getOCsi(), hssProv.getTCsi(), hssProv.getSsCsi(), hssProv.getSmsCsi(), hssProv.getOCsiScfNo(), hssProv.getTCsiScfNo(), hssProv.getSsCsiScfNo(), hssProv.getSmsSciScfNo(), hssProv.getGprsFlag(), hssProv.getEpsFlag(), hssProv.getArd(), hssProv.getEpsUserTpl(), hssProv.getContextD(), hssProv.getApnCtxtList(), hssProv.getImsFlag(), hssProv.getSubscriberProfId(), hssProv.getAccessLogs().getIdAccessLogsId())));
        accessLogsRepository.save(accessLogs);
        String reqPayload = accessLogs.getReqPayload();
        return reqPayload;
    }
}

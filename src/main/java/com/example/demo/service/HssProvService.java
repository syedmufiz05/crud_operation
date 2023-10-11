package com.example.demo.service;

import com.example.demo.dto.HssProvDto;
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

    public void saveHssProv(HssProv hssProv, String authToken) {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        hssProvRepository.save(hssProv);
        saveAccessRequestPayload(hssProv, accessLogs);
    }

    private void saveAccessRequestPayload(HssProv hssProv, AccessLogs accessLogs) {
//      accessLogs.setReqPayload(String.valueOf(new ReqPayloadDto(hssProv.getHssprovId(), hssProv.getImsi(), hssProv.getImsiFlag(), hssProv.getMsisdn(), hssProv.getNam(), hssProv.getOdb(), hssProv.getBaoc(), hssProv.getBoic(), hssProv.getOsb1(), hssProv.getOsb2(), hssProv.getBaic(), hssProv.getRoaming(), hssProv.getBearerService(), hssProv.getTelephone(), hssProv.getSms(), hssProv.getCfuA(), hssProv.getCfuR(), hssProv.getCfuP(), hssProv.getCfbP(), hssProv.getCfnryP(), hssProv.getCfnryT(), hssProv.getCfnrcP(), hssProv.getCwA(), hssProv.getCwP(), hssProv.getChP(), hssProv.getCamel(), hssProv.getOCsi(), hssProv.getTCsi(), hssProv.getSsCsi(), hssProv.getSmsCsi(), hssProv.getOCsiScfNo(), hssProv.getTCsiScfNo(), hssProv.getSsCsiScfNo(), hssProv.getSmsSciScfNo(), hssProv.getGprsFlag(), hssProv.getEpsFlag(), hssProv.getArd(), hssProv.getEpsUserTpl(), hssProv.getContextD(), hssProv.getApnCtxtList(), hssProv.getImsFlag(), hssProv.getSubscriberProfId(), hssProv.getAccessId())));
        accessLogs.setReqPayload(String.valueOf(hssProv));
        accessLogsRepository.save(accessLogs);
    }

    @Transactional
    public void deleteHssProv(String imsi, String msisdn) {
        hssProvRepository.deleteByImsiOrMsisdn(imsi, msisdn);
    }

    @Transactional
    public HssProv updateHssProv(String imsi, String msisdn, HssProvDto hssProvDto) {
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
            accessLogs.setReqPayload(String.valueOf(hssProvDb));
            accessLogsRepository.save(accessLogs);
            return hssProvRepository.save(hssProvDb);
        }
        return null;
    }
}

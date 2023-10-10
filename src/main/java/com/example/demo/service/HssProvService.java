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

    public void saveHssProv(HssProvDto hssProvDto, String authToken) {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);

        HssProv hssProvDb = new HssProv();
        hssProvDb.setAccessId(accessLogs.getIdAccessLogsId());
        hssProvDb.setImsi(hssProvDto.getImsi());
        hssProvDb.setImsiFlag(hssProvDto.getImsiFlag());
        hssProvDb.setMsisdn(hssProvDto.getMsisdn());
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
        hssProvRepository.save(hssProvDb);
        saveAccessRequestPayload(hssProvDb, accessLogs);
    }

    private void saveAccessRequestPayload(HssProv hssProvDb, AccessLogs accessLogs) {
        accessLogs.setReqPayload(String.valueOf(hssProvDb));
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
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(hssProv.get().getAccessId());
            AccessLogs accessLogs = accessLogsDb.get();
            accessLogs.setAccessDateTime(new Date());
            accessLogs.setReqPayload(String.valueOf(hssProvDb));
            accessLogsRepository.save(accessLogs);
            return hssProvRepository.save(hssProvDb);
        }
        return null;
    }
}

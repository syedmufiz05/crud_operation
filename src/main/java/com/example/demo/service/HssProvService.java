package com.example.demo.service;

import com.example.demo.dto.HssProvDto;
import com.example.demo.dto.SocketMsgDto;
import com.example.demo.model.AccessLogs;
import com.example.demo.model.HssProv;
import com.example.demo.repository.AccessLogsRepository;
import com.example.demo.repository.HssProvRepository;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
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
    @Autowired
    private HSSSocketClient hssSocketClient;

    public String saveHssProv(HssProvDto hssProvDto, String authToken) throws JsonProcessingException {
        AccessLogs accessLogs = new AccessLogs();
        accessLogs.setUserId(1212);
        accessLogs.setResponsePayload("");
        accessLogs.setAuthToken(authToken);
        accessLogsRepository.save(accessLogs);
        HssProv hssProv = new HssProv();
        hssProv.setImsi(hssProvDto.getImsi() != null ? hssProvDto.getImsi() : "");
        hssProv.setImsiFlag(hssProvDto.getImsiFlag() != null ? hssProvDto.getImsiFlag() : Integer.valueOf(""));
        hssProv.setMsisdn(hssProvDto.getMsisdn() != null ? hssProvDto.getMsisdn() : "");
        hssProv.setNam(hssProvDto.getNam() != null ? hssProvDto.getNam() : "");
        hssProv.setOdb(hssProvDto.getOdb() != null ? hssProvDto.getOdb() : Integer.valueOf(""));
        hssProv.setBaoc(hssProvDto.getBaoc() != null ? hssProvDto.getBaoc() : Integer.valueOf(""));
        hssProv.setBoic(hssProvDto.getBoic() != null ? hssProvDto.getBoic() : Integer.valueOf(""));
        hssProv.setOsb1(hssProvDto.getOsb1() != null ? hssProvDto.getOsb1() : Integer.valueOf(""));
        hssProv.setOsb2(hssProvDto.getOsb2() != null ? hssProvDto.getOsb2() : Integer.valueOf(""));
        hssProv.setBaic(hssProvDto.getBaic() != null ? hssProvDto.getBaic() : Integer.valueOf(""));
        hssProv.setRoaming(hssProvDto.getRoaming() != null ? hssProvDto.getRoaming() : Integer.valueOf(""));
        hssProv.setBearerService(hssProvDto.getBearerService() != null ? hssProvDto.getBearerService() : "");
        hssProv.setTelephone(hssProvDto.getTelephone() != null ? hssProvDto.getTelephone() : Integer.valueOf(""));
        hssProv.setSms(hssProvDto.getSms() != null ? hssProvDto.getSms() : "");
        hssProv.setCfuA(hssProvDto.getCfuA() != null ? hssProvDto.getCfuA() : Integer.valueOf(""));
        hssProv.setCfuR(hssProvDto.getCfuR() != null ? hssProvDto.getCfuR() : Integer.valueOf(""));
        hssProv.setCfuP(hssProvDto.getCfuP() != null ? hssProvDto.getCfuP() : Integer.valueOf(""));
        hssProv.setCfbP(hssProvDto.getCfbP() != null ? hssProvDto.getCfbP() : Integer.valueOf(""));
        hssProv.setCfnryP(hssProvDto.getCfnryP() != null ? hssProvDto.getCfnryP() : Integer.valueOf(""));
        hssProv.setCfnryT(hssProvDto.getCfnryT() != null ? hssProvDto.getCfnryT() : Integer.valueOf(""));
        hssProv.setCfnrcP(hssProvDto.getCfnrcP() != null ? hssProvDto.getCfnrcP() : Integer.valueOf(""));
        hssProv.setCwA(hssProvDto.getCwA() != null ? hssProvDto.getCwA() : Integer.valueOf(""));
        hssProv.setCwP(hssProvDto.getCwP() != null ? hssProvDto.getCwP() : Integer.valueOf(""));
        hssProv.setChP(hssProvDto.getChP() != null ? hssProvDto.getChP() : Integer.valueOf(""));
        hssProv.setCamel(hssProvDto.getCamel() != null ? hssProvDto.getCamel() : Integer.valueOf(""));
        hssProv.setOCsi(hssProvDto.getOCsi() != null ? hssProvDto.getOCsi() : Integer.valueOf(""));
        hssProv.setTCsi(hssProvDto.getTCsi() != null ? hssProvDto.getTCsi() : Integer.valueOf(""));
        hssProv.setSsCsi(hssProvDto.getSsCsi() != null ? hssProvDto.getSsCsi() : Integer.valueOf(""));
        hssProv.setSmsCsi(hssProvDto.getSmsCsi() != null ? hssProvDto.getSmsCsi() : Integer.valueOf(""));
        hssProv.setOCsiScfNo(hssProvDto.getOCsiScfNo() != null ? hssProvDto.getOCsiScfNo() : "");
        hssProv.setTCsiScfNo(hssProvDto.getTCsiScfNo() != null ? hssProvDto.getTCsiScfNo() : "");
        hssProv.setSsCsiScfNo(hssProvDto.getSsCsiScfNo() != null ? hssProvDto.getSsCsiScfNo() : "");
        hssProv.setSmsSciScfNo(hssProvDto.getSmsSciScfNo() != null ? hssProvDto.getSmsSciScfNo() : "");
        hssProv.setGprsFlag(hssProvDto.getGprsFlag() != null ? hssProvDto.getGprsFlag() : Integer.valueOf(""));
        hssProv.setEpsFlag(hssProvDto.getEpsFlag() != null ? hssProvDto.getEpsFlag() : Integer.valueOf(""));
        hssProv.setArd(hssProvDto.getArd() != null ? hssProvDto.getArd() : "");
        hssProv.setEpsUserTpl(hssProvDto.getEpsUserTpl() != null ? hssProvDto.getEpsUserTpl() : "");
        hssProv.setDefEps(hssProvDto.getDefEps() != null ? hssProvDto.getDefEps() : "");
        hssProv.setContextD(hssProvDto.getContextD() != null ? hssProvDto.getContextD() : "");
        hssProv.setApnCtxtList(hssProvDto.getApnCtxtList() != null ? hssProvDto.getApnCtxtList() : "");
        hssProv.setImsFlag(hssProvDto.getImsFlag() != null ? hssProvDto.getImsFlag() : Integer.valueOf(""));
        hssProv.setSubscriberProfId(hssProvDto.getSubscriberProfId() != null ? hssProvDto.getSubscriberProfId() : Integer.valueOf(""));
        hssProv.setAccessLogs(accessLogs);
        hssProvRepository.save(hssProv);
        return saveAccessRequestPayload(hssProvDto, hssProv, accessLogs);
    }

    @Transactional
    public String updateHssProv(String imsi, String msisdn, HssProvDto hssProvDto) throws JsonProcessingException {
        Optional<HssProv> hssProv = hssProvRepository.findByImsiOrMsisdn(imsi, msisdn);
        if (hssProv.isPresent()) {
            HssProv hssProvDb = hssProv.get();
            hssProvDb.setImsiFlag(hssProvDto.getImsiFlag() != null ? hssProvDto.getImsiFlag() : Integer.valueOf(""));
            hssProvDb.setMsisdn(hssProvDto.getMsisdn() != null ? hssProvDto.getMsisdn() : "");
            hssProvDb.setNam(hssProvDto.getNam() != null ? hssProvDto.getNam() : "");
            hssProvDb.setOdb(hssProvDto.getOdb() != null ? hssProvDto.getOdb() : Integer.valueOf(""));
            hssProvDb.setBaoc(hssProvDto.getBaoc() != null ? hssProvDto.getBaoc() : Integer.valueOf(""));
            hssProvDb.setBoic(hssProvDto.getBoic() != null ? hssProvDto.getBoic() : Integer.valueOf(""));
            hssProvDb.setOsb1(hssProvDto.getOsb1() != null ? hssProvDto.getOsb1() : Integer.valueOf(""));
            hssProvDb.setOsb2(hssProvDto.getOsb2() != null ? hssProvDto.getOsb2() : Integer.valueOf(""));
            hssProvDb.setBaic(hssProvDto.getBaic() != null ? hssProvDto.getBaic() : Integer.valueOf(""));
            hssProvDb.setRoaming(hssProvDto.getRoaming() != null ? hssProvDto.getRoaming() : Integer.valueOf(""));
            hssProvDb.setBearerService(hssProvDto.getBearerService() != null ? hssProvDto.getBearerService() : "");
            hssProvDb.setTelephone(hssProvDto.getTelephone() != null ? hssProvDto.getTelephone() : Integer.valueOf(""));
            hssProvDb.setSms(hssProvDto.getSms() != null ? hssProvDto.getSms() : "");
            hssProvDb.setCfuA(hssProvDto.getCfuA() != null ? hssProvDto.getCfuA() : Integer.valueOf(""));
            hssProvDb.setCfuR(hssProvDto.getCfuR() != null ? hssProvDto.getCfuR() : Integer.valueOf(""));
            hssProvDb.setCfuP(hssProvDto.getCfuP() != null ? hssProvDto.getCfuP() : Integer.valueOf(""));
            hssProvDb.setCfbP(hssProvDto.getCfbP() != null ? hssProvDto.getCfbP() : Integer.valueOf(""));
            hssProvDb.setCfnryP(hssProvDto.getCfnryP() != null ? hssProvDto.getCfnryP() : Integer.valueOf(""));
            hssProvDb.setCfnryT(hssProvDto.getCfnryT() != null ? hssProvDto.getCfnryT() : Integer.valueOf(""));
            hssProvDb.setCfnrcP(hssProvDto.getCfnrcP() != null ? hssProvDto.getCfnrcP() : Integer.valueOf(""));
            hssProvDb.setCwA(hssProvDto.getCwA() != null ? hssProvDto.getCwA() : Integer.valueOf(""));
            hssProvDb.setCwP(hssProvDto.getCwP() != null ? hssProvDto.getCwP() : Integer.valueOf(""));
            hssProvDb.setChP(hssProvDto.getChP() != null ? hssProvDto.getChP() : Integer.valueOf(""));
            hssProvDb.setCamel(hssProvDto.getCamel() != null ? hssProvDto.getCamel() : Integer.valueOf(""));
            hssProvDb.setOCsi(hssProvDto.getOCsi() != null ? hssProvDto.getOCsi() : Integer.valueOf(""));
            hssProvDb.setTCsi(hssProvDto.getTCsi() != null ? hssProvDto.getTCsi() : Integer.valueOf(""));
            hssProvDb.setSsCsi(hssProvDto.getSsCsi() != null ? hssProvDto.getSsCsi() : Integer.valueOf(""));
            hssProvDb.setSmsCsi(hssProvDto.getSmsCsi() != null ? hssProvDto.getSmsCsi() : Integer.valueOf(""));
            hssProvDb.setOCsiScfNo(hssProvDto.getOCsiScfNo() != null ? hssProvDto.getOCsiScfNo() : "");
            hssProvDb.setTCsiScfNo(hssProvDto.getTCsiScfNo() != null ? hssProvDto.getTCsiScfNo() : "");
            hssProvDb.setSsCsiScfNo(hssProvDto.getSsCsiScfNo() != null ? hssProvDto.getSsCsiScfNo() : "");
            hssProvDb.setSmsSciScfNo(hssProvDto.getSmsSciScfNo() != null ? hssProvDto.getSmsSciScfNo() : "");
            hssProvDb.setGprsFlag(hssProvDto.getGprsFlag() != null ? hssProvDto.getGprsFlag() : Integer.valueOf(""));
            hssProvDb.setEpsFlag(hssProvDto.getEpsFlag() != null ? hssProvDto.getEpsFlag() : Integer.valueOf(""));
            hssProvDb.setArd(hssProvDto.getArd() != null ? hssProvDto.getArd() : "");
            hssProvDb.setEpsUserTpl(hssProvDto.getEpsUserTpl() != null ? hssProvDto.getEpsUserTpl() : "");
            hssProvDb.setDefEps(hssProvDto.getDefEps() != null ? hssProvDto.getDefEps() : "");
            hssProvDb.setContextD(hssProvDto.getContextD() != null ? hssProvDto.getContextD() : "");
            hssProvDb.setApnCtxtList(hssProvDto.getApnCtxtList() != null ? hssProvDto.getApnCtxtList() : "");
            hssProvDb.setImsFlag(hssProvDto.getImsFlag() != null ? hssProvDto.getImsFlag() : Integer.valueOf(""));
            hssProvDb.setSubscriberProfId(hssProvDto.getSubscriberProfId() != null ? hssProvDto.getSubscriberProfId() : Integer.valueOf(""));
            Optional<AccessLogs> accessLogsDb = accessLogsRepository.findByIdAccessLogsId(hssProvDb.getAccessLogs().getIdAccessLogsId());
            AccessLogs accessLogs = accessLogsDb.get();
            accessLogs.setAccessDateTime(new Date());
            accessLogs.setReqPayload(updateAccessRequestPayload(hssProvDto, hssProvDb, accessLogs));
            accessLogsRepository.save(accessLogs);
            hssProvRepository.save(hssProvDb);
            return "Successfully updated...";
        }
        return "Invalid Imsi or Msisdn Id";
    }

    @Transactional
    public String deleteHssProv(String imsi, String msisdn) {
        if (imsi.isEmpty() || msisdn.isEmpty()) {
            hssProvRepository.deleteByImsiOrMsisdn(imsi, msisdn);
            return "Successfully deleted...";
        }
        return "Imsi or Msisdn Id is empty";
    }

    private String saveAccessRequestPayload(HssProvDto hssProvDto, HssProv hssProv, AccessLogs accessLogs) throws JsonProcessingException {
        hssProvDto.setHssProvId(hssProv.getHssprovId());
        hssProvDto.setAccessId(hssProv.getAccessLogs().getIdAccessLogsId() != null ? hssProv.getAccessLogs().getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(hssProvDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        SocketMsgDto socketMsgDto = new SocketMsgDto(hssProvDto.getImsi(), hssProvDto.getImsiFlag(), hssProvDto.getMsisdn(), hssProvDto.getNam(), hssProvDto.getOdb(), hssProvDto.getBaoc(), hssProvDto.getBoic(), hssProvDto.getOsb1(), hssProvDto.getOsb2(), hssProvDto.getBaic(), hssProvDto.getRoaming(), hssProvDto.getBearerService(), hssProvDto.getTelephone(), hssProvDto.getSms(), hssProvDto.getCfuA(), hssProvDto.getCfuR(), hssProvDto.getCfuP(), hssProvDto.getCfbP(), hssProvDto.getCfnryP(), hssProvDto.getCfnryT(), hssProvDto.getCfnrcP(), hssProvDto.getCwA(), hssProvDto.getCwP(), hssProvDto.getChP(), hssProvDto.getCamel(), hssProvDto.getOCsi(), hssProvDto.getTCsi(), hssProvDto.getSsCsi(), hssProvDto.getSmsCsi(), hssProvDto.getOCsiScfNo(), hssProvDto.getTCsiScfNo(), hssProvDto.getSsCsiScfNo(), hssProvDto.getSmsSciScfNo(), hssProvDto.getGprsFlag(), hssProvDto.getEpsFlag(), hssProvDto.getArd(), hssProvDto.getEpsUserTpl(), hssProvDto.getDefEps(), hssProvDto.getContextD(), hssProvDto.getApnCtxtList(), hssProvDto.getImsFlag());
        String reqMsg = convertDtoToJson(socketMsgDto);
        hssSocketClient.sendMessage(reqMsg);
        return "Successfully saved...";
    }

    private String updateAccessRequestPayload(HssProvDto hssProvDto, HssProv hssProvDb, AccessLogs accessLogs) throws JsonProcessingException {
        hssProvDto.setHssProvId(hssProvDb.getHssprovId());
        hssProvDto.setDefEps(hssProvDb.getDefEps() != null ? hssProvDb.getDefEps() : "");
        hssProvDto.setAccessId(accessLogs.getIdAccessLogsId() != null ? accessLogs.getIdAccessLogsId() : Integer.valueOf(""));
        String reqPayload = convertEntityToJson(hssProvDto);
        accessLogs.setReqPayload(reqPayload);
        accessLogsRepository.save(accessLogs);
        return reqPayload;
    }

    private static String convertEntityToJson(HssProvDto hssProvDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(hssProvDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }

    private static String convertDtoToJson(SocketMsgDto socketMsgDto) throws JsonProcessingException {
        ObjectWriter ow = new ObjectMapper().writer().withDefaultPrettyPrinter();
        String body = ow.writeValueAsString(socketMsgDto);
        body = body.replaceAll("(\\r|\\n)", "");
        return body;
    }

}

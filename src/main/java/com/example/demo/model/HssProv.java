package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hssprov")
public class HssProv {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "hssprov_id")
    @JsonProperty("hssprov_id")
    private Integer hssprovId;

    @Column(name = "imsi")
    @JsonProperty("imsi")
    private String imsi;

    @Column(name = "imsi_flag")
    @JsonProperty("imsi_flag")
    private Integer imsiFlag;

    @Column(name = "msisdn")
    @JsonProperty("msisdn")
    private String msisdn;

    @Column(name = "nam")
    @JsonProperty("nam")
    private String nam;

    @Column(name = "odb")
    @JsonProperty("odb")
    private Integer odb;

    @Column(name = "baoc")
    @JsonProperty("baoc")
    private Integer baoc;

    @Column(name = "boic")
    @JsonProperty("boic")
    private Integer boic;

    @Column(name = "osb1")
    @JsonProperty("osb1")
    private Integer osb1;

    @Column(name = "osb2")
    @JsonProperty("osb2")
    private Integer osb2;

    @Column(name = "baic")
    @JsonProperty("baic")
    private Integer baic;

    @Column(name = "roaming")
    @JsonProperty("roaming")
    private Integer roaming;

    @Column(name = "bearer_service")
    @JsonProperty("bearer_service")
    private String bearerService;

    @Column(name = "telephone")
    @JsonProperty("telephone")
    private Integer telephone;

    @Column(name = "sms")
    @JsonProperty("sms")
    private String sms;

    @Column(name = "cfu_a")
    @JsonProperty("cfu_a")
    private Integer cfuA;

    @Column(name = "cfu_r")
    @JsonProperty("cfu_r")
    private Integer cfuR;

    @Column(name = "cfu_p")
    @JsonProperty("cfu_p")
    private Integer cfuP;

    @Column(name = "cfb_p")
    @JsonProperty("cfb_p")
    private Integer cfbP;

    @Column(name = "cfnry_p")
    @JsonProperty("cfnry_p")
    private Integer cfnryP;

    @Column(name = "cfnry_t")
    @JsonProperty("cfnry_t")
    private Integer cfnryT;

    @Column(name = "cfnrc_p")
    @JsonProperty("cfnrc_p")
    private Integer cfnrcP;

    @Column(name = "cw_a")
    @JsonProperty("cw_a")
    private Integer cwA;

    @Column(name = "cw_p")
    @JsonProperty("cw_p")
    private Integer cwP;

    @Column(name = "ch_p")
    @JsonProperty("ch_p")
    private Integer chP;

    @Column(name = "camel")
    @JsonProperty("camel")
    private Integer camel;

    @Column(name = "o_csi")
    @JsonProperty("o_csi")
    private Integer oCsi;

    @Column(name = "t_csi")
    @JsonProperty("t_csi")
    private Integer tCsi;

    @Column(name = "ss_csi")
    @JsonProperty("ss_csi")
    private Integer ssCsi;

    @Column(name = "sms_csi")
    @JsonProperty("sms_csi")
    private Integer smsCsi;

    @Column(name = "o_csi_scf_no")
    @JsonProperty("o_csi_scf_no")
    private String oCsiScfNo;

    @Column(name = "t_csi_scf_no")
    @JsonProperty("t_csi_scf_no")
    private String tCsiScfNo;

    @Column(name = "ss_csi_scf_no")
    @JsonProperty("ss_csi_scf_no")
    private String ssCsiScfNo;

    @Column(name = "sms_sci_scf_no")
    @JsonProperty("sms_sci_scf_no")
    private String smsSciScfNo;

    @Column(name = "gprs_flag")
    @JsonProperty("gprs_flag")
    private Integer gprsFlag;

    @Column(name = "eps_flag")
    @JsonProperty("eps_flag")
    private Integer epsFlag;

    @Column(name = "ard")
    @JsonProperty("ard")
    private String ard;

    @Column(name = "eps_user_tpl")
    @JsonProperty("eps_user_tpl")
    private String epsUserTpl;

    @Column(name = "context_d")
    @JsonProperty("context_d")
    private String contextD;

    @Column(name = "apn_ctxt_list")
    @JsonProperty("apn_ctxt_list")
    private String apnCtxtList;

    @Column(name = "ims_flag")
    @JsonProperty("ims_flag")
    private Integer imsFlag;

    @Column(name = "subscriber_prof_id")
    @JsonProperty("subscriber_prof_id")
    private Integer subscriberProfId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "idaccess_logs_id")
    @JsonProperty("access_id")
    private AccessLogs accessLogs;
}

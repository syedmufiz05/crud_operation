package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hssprov_record")
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
    private Boolean imsiFlag;

    @Column(name = "msisdn")
    @JsonProperty("msisdn")
    private String msisdn;

    @Column(name = "nam")
    @JsonProperty("nam")
    private String nam;

    @Column(name = "odb")
    @JsonProperty("odb")
    private Boolean odb;

    @Column(name = "baoc")
    @JsonProperty("baoc")
    private Boolean baoc;

    @Column(name = "boic")
    @JsonProperty("boic")
    private Boolean boic;

    @Column(name = "osb1")
    @JsonProperty("osb1")
    private Boolean osb1;

    @Column(name = "osb2")
    @JsonProperty("osb2")
    private Boolean osb2;

    @Column(name = "baic")
    @JsonProperty("baic")
    private Boolean baic;

    @Column(name = "roaming")
    @JsonProperty("roaming")
    private Boolean roaming;

    @Column(name = "bearer_service")
    @JsonProperty("bearer_service")
    private String bearerService;

    @Column(name = "telephone")
    @JsonProperty("telephone")
    private Boolean telephone;

    @Column(name = "sms")
    @JsonProperty("sms")
    private String sms;

    @Column(name = "cfu_a")
    @JsonProperty("cfu_a")
    private Boolean cfuA;

    @Column(name = "cfu_r")
    @JsonProperty("cfu_r")
    private Boolean cfuR;

    @Column(name = "cfu_p")
    @JsonProperty("cfu_p")
    private Boolean cfuP;

    @Column(name = "cfb_p")
    @JsonProperty("cfb_p")
    private Boolean cfbP;

    @Column(name = "cfnry_p")
    @JsonProperty("cfnry_p")
    private Boolean cfnryP;

    @Column(name = "cfnry_t")
    @JsonProperty("cfnry_t")
    private Integer cfnryT;

    @Column(name = "cfnrc_p")
    @JsonProperty("cfnrc_p")
    private Boolean cfnrcP;

    @Column(name = "cw_a")
    @JsonProperty("cw_a")
    private Boolean cwA;

    @Column(name = "cw_p")
    @JsonProperty("cw_p")
    private Boolean cwP;

    @Column(name = "ch_p")
    @JsonProperty("ch_p")
    private Boolean chP;

    @Column(name = "camel")
    @JsonProperty("camel")
    private Boolean camel;

    @Column(name = "o_csi")
    @JsonProperty("o_csi")
    private Boolean oCsi;

    @Column(name = "t_csi")
    @JsonProperty("t_csi")
    private Boolean tCsi;

    @Column(name = "ss_csi")
    @JsonProperty("ss_csi")
    private Boolean ssCsi;

    @Column(name = "sms_csi")
    @JsonProperty("sms_csi")
    private Boolean smsCsi;

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
    private Boolean gprsFlag;

    @Column(name = "eps_flag")
    @JsonProperty("eps_flag")
    private Boolean epsFlag;

    @Column(name = "ard")
    @JsonProperty("ard")
    private String ard;

    @Column(name = "eps_user_tpl")
    @JsonProperty("eps_user_tpl")
    private String epsUserTpl;

    @Column(name = "def_eps")
    @JsonProperty("def_eps")
    private String defEps;

    @Column(name = "context_d")
    @JsonProperty("context_d")
    private String contextD;

    @Column(name = "apn_ctxt_list")
    @JsonProperty("apn_ctxt_list")
    private String apnCtxtList;

    @Column(name = "ims_flag")
    @JsonProperty("ims_flag")
    private Boolean imsFlag;

    @Column(name = "subscriber_prof_id")
    @JsonProperty("subscriber_prof_id")
    private Integer subscriberProfId;

    @OneToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "access_id", referencedColumnName = "idaccess_logs_id")
    @JsonProperty("access_id")
    private AccessLogs accessLogs;

}

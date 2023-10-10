package com.example.demo.model;

import lombok.Data;

import javax.persistence.*;

@Data
@Entity
@Table(name = "hssprov")
public class HssProv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "hssprov_id")
    private Integer hssprovId;
    @Column(name = "imsi")
    private String imsi;
    @Column(name = "imsi_flag")
    private Integer imsiFlag;
    @Column(name = "msisdn")
    private String msisdn;
    @Column(name = "nam")
    private String nam;
    @Column(name = "odb")
    private Integer odb;
    @Column(name = "baoc")
    private Integer baoc;
    @Column(name = "boic")
    private Integer boic;
    @Column(name = "osb1")
    private Integer osb1;
    @Column(name = "osb2")
    private Integer osb2;
    @Column(name = "baic")
    private Integer baic;
    @Column(name = "roaming")
    private Integer roaming;
    @Column(name = "bearer_service")
    private String bearerService;
    @Column(name = "telephone")
    private Integer telephone;
    @Column(name = "sms")
    private String sms;
    @Column(name = "cfu_a")
    private Integer cfuA;
    @Column(name = "cfu_r")
    private Integer cfuR;
    @Column(name = "cfu_p")
    private Integer cfuP;
    @Column(name = "cfb_p")
    private Integer cfbP;
    @Column(name = "cfnry_p")
    private Integer cfnryP;
    @Column(name = "cfnry_t")
    private Integer cfnryT;
    @Column(name = "cfnrc_p")
    private Integer cfnrcP;
    @Column(name = "cw_a")
    private Integer cwA;
    @Column(name = "cw_p")
    private Integer cwP;
    @Column(name = "ch_p")
    private Integer chP;
    @Column(name = "camel")
    private Integer camel;
    @Column(name = "o_csi")
    private Integer oCsi;
    @Column(name = "t_csi")
    private Integer tCsi;
    @Column(name = "ss_csi")
    private Integer ssCsi;
    @Column(name = "sms_csi")
    private Integer smsCsi;
    @Column(name = "o_csi_scf_no")
    private String oCsiScfNo;
    @Column(name = "t_csi_scf_no")
    private String tCsiScfNo;
    @Column(name = "ss_csi_scf_no")
    private String ssCsiScfNo;
    @Column(name = "sms_sci_scf_no")
    private String smsSciScfNo;
    @Column(name = "gprs_flag")
    private Integer gprsFlag;
    @Column(name = "eps_flag")
    private Integer epsFlag;
    @Column(name = "ard")
    private String ard;
    @Column(name = "eps_user_tpl")
    private String epsUserTpl;
    @Column(name = "context_d")
    private String contextD;
    @Column(name = "apn_ctxt_list")
    private String apnCtxtList;
    @Column(name = "ims_flag")
    private Integer imsFlag;
    @Column(name = "subscriber_prof_id")
    private Integer subscriberProfId;
    @Column(name = "access_id")
    private Integer accessId;
}

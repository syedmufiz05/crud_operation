package com.example.demo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "prepaid_roaming_accounts")
@Data
public class PrepaidRoamingAccounts {
    @Column(name = "roaming_account_id")
    private Integer roamingAccountId;

    @Column(name = "roaming_customer_id")
    private Integer roamingCustomerId;

    @Column(name = "msisdn")
    private String msisdn;

    @Column(name = "roaming_cs_voice_call_seconds")
    private Integer roamingCsVoiceCallSeconds;

    @Column(name = "roaming_4g_data_octets")
    private Integer roaming4gDataOctets;

    @Column(name = "roaming_5g_data_octets")
    private Integer roaming5gDataOctets;

    @Column(name = "roaming_volte_call_seconds")
    private Integer roamingVolteCallSeconds;

    @Column(name = "roaming_total_data_octets_available")
    private Integer roamingTotalDataOctetsAvailable;

    @Column(name = "roaming_total_data_octets_consumed")
    private Integer roamingTotalDataOctetsConsumed;

    @Column(name = "roaming_total_call_seconds_available")
    private Integer roamingTotalCallSecondsAvailable;

    @Column(name = "roaming_total_call_seconds_consumed")
    private Integer roamingTotalCallSecondsConsumed;

    @Column(name = "roaming_total_sms_available")
    private Integer roamingTotalSmsAvailable;

    @Column(name = "roaming_total_sms_consumed")
    private Integer roamingTotalSmsConsumed;
}

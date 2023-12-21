package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class PrepaidAccountsDto {
    @JsonProperty("account_id")
    private Integer accountId;

    @JsonProperty("customer_id")
    private Integer customerId;

    @JsonProperty("msisdn")
    private String msisdn;

    @JsonProperty("cs_voice_call_seconds")
    private Integer csVoiceCallSeconds;

    @JsonProperty("4g_data_octets")
    private Integer fourGDataOctets;

    @JsonProperty("5g_data_octets")
    private Integer fiveGDataOctets;

    @JsonProperty("volte_call_seconds")
    private Integer volteCallSeconds;

    @JsonProperty("total_data_octets_available")
    private Integer totalDataOctetsAvailable;

    @JsonProperty("total_input_data_octets_available")
    private Integer totalInputDataOctetsAvailable;

    @JsonProperty("total_output_data_octets_available")
    private Integer totalOutputDataOctetsAvailable;

    @JsonProperty("total_data_octets_consumed")
    private Integer totalDataOctetsConsumed;

    @JsonProperty("total_call_seconds_available")
    private Integer totalCallSecondsAvailable;

    @JsonProperty("total_call_seconds_consumed")
    private Integer totalCallSecondsConsumed;

    @JsonProperty("total_sms_available")
    private Integer totalSmsAvailable;

    @JsonProperty("total_sms_consumed")
    private Integer totalSmsConsumed;
}

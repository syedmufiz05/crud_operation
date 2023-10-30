package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingPlanDto {
    @JsonProperty("rating_plan_id")
    private Integer ratingPlanId;

    @JsonProperty("destination_rates_id")
    private Integer destinationRatesId;
}

package com.example.demo.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingProfileDto {
    @JsonProperty("rating_profile_id")
    private Integer ratingProfileId;

    @JsonProperty("category_id")
    private Integer categoryId;

    @JsonProperty("calling_party")
    private String callingParty;

    @JsonProperty("rating_plan_id")
    private Integer ratingPlanId;
}

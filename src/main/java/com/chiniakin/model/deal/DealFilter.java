package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
public class DealFilter {

    private UUID id;

    private String description;

    @JsonProperty("agreement_number")
    private String agreementNumber;

    @JsonProperty("agreement_date_from")
    private LocalDate agreementDateFrom;

    @JsonProperty("agreement_date_to")
    private LocalDate agreementDateTo;

    @JsonProperty("availability_date_from")
    private LocalDate availabilityDateFrom;

    @JsonProperty("availability_date_to")
    private LocalDate availabilityDateTo;

    @JsonProperty("type")
    private List<DealTypeResponse> dealTypeResponses;

    @JsonProperty("status")
    private List<DealStatusResponse> dealStatusResponses;

    @JsonProperty("close_dt_from")
    private LocalDateTime closeDtFrom;

    @JsonProperty("close_dt_to")
    private LocalDateTime closeDtTo;

    @JsonProperty("contractor_id")
    private String contractorId;

    private String name;

    private String inn;

    @JsonProperty("size")
    private Integer size = 10;

    @JsonProperty("page")
    private Integer page = 1;

}

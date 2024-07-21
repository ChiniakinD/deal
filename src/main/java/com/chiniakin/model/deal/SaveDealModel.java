package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
public class SaveDealModel {

    private UUID id;

    private String description;

    @JsonProperty("agreement_number")
    private String agreementNumber;

    @JsonProperty("agreement_date")
    private LocalDate agreementDate;

    @JsonProperty("agreement_start_dt")
    private LocalDateTime agreementStartDt;

    @JsonProperty("availability_date")
    private LocalDate availabilityDate;

    @JsonProperty("type")
    private DealTypeResponse dealTypeResponse;

    private BigDecimal sum;

    @JsonProperty("close_dt")
    private LocalDateTime closeDt;

}

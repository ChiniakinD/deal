package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Accessors(chain = true)
public class DealModel {

    private UUID id;

    private String description;

    @JsonProperty("agreement_number")
    private String agreementNumber;

    @JsonProperty("agreement_date")
    private String agreementDate;

    @JsonProperty("agreement_start_dt")
    private LocalDateTime agreementStartDt;

    @JsonProperty("availability_date")
    private LocalDate availabilityDate;

    @JsonProperty("type")
    private DealTypeResponse dealTypeResponse;

    @JsonProperty("status")
    private DealStatusResponse dealStatusResponse;

    private BigDecimal sum;
    @JsonProperty("close_dt")
    private LocalDateTime closeDt;

    @JsonProperty("contractors")
    private List<DealContractorResponse> contractors;

}

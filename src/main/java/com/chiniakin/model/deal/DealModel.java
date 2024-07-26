package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Модель сделок.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "Модель сделки")
public class DealModel {

    @Schema(description = "id сделки")
    private UUID id;

    @Schema(description = "Описание сделки")
    private String description;

    @JsonProperty("agreement_number")
    @Schema(description = "Номер договора")
    private String agreementNumber;

    @JsonProperty("agreement_date")
    @Schema(description = "Дата договора")
    private String agreementDate;

    @JsonProperty("agreement_start_dt")
    @Schema(description = "Дата и время вступления соглашения в силу")
    private LocalDateTime agreementStartDt;

    @JsonProperty("availability_date")
    @Schema(description = "Срок действия сделки")
    private LocalDate availabilityDate;

    @JsonProperty("type")
    @Schema(description = "Тип сделки", implementation = DealTypeResponse.class)
    private DealTypeResponse dealTypeResponse;

    @JsonProperty("status")
    @Schema(description = "Статус сделки", implementation = DealStatusResponse.class)
    private DealStatusResponse dealStatusResponse;

    @Schema(description = "Сумма сделки")
    private BigDecimal sum;

    @JsonProperty("close_dt")
    @Schema(description = "Дата и время закрытия сделки")
    private LocalDateTime closeDt;

    @JsonProperty("contractors")
    @Schema(description = "Список контрагентов сделки", implementation = DealContractorResponse.class)
    private List<DealContractorResponse> contractors;

}

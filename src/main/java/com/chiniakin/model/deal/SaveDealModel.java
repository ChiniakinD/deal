package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Модель сделки для сохранения новой/обновления существующей.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Schema(name = "Модель сделки для сохранения новой/обновления существующей.")
public class SaveDealModel {

    @Schema(name = "id сделки")
    private UUID id;

    @Schema(name = "Описание сделки")
    private String description;

    @JsonProperty("agreement_number")
    @Schema(description = "Номер договора")
    private String agreementNumber;

    @JsonProperty("agreement_date")
    @Schema(description = "Дата договора")
    private LocalDate agreementDate;

    @JsonProperty("agreement_start_dt")
    @Schema(description = "Дата и время вступления соглашения в силу")
    private LocalDateTime agreementStartDt;

    @JsonProperty("availability_date")
    @Schema(description = "Срок действия сделки")
    private LocalDate availabilityDate;

    @JsonProperty("type")
    @Schema(description = "Тип сделки", implementation = DealTypeResponse.class)
    private DealTypeResponse dealTypeResponse;

    @Schema(description = "Сумма сделки")
    private BigDecimal sum;

    @JsonProperty("close_dt")
    @Schema(description = "Дата и время закрытия сделки")
    private LocalDateTime closeDt;

}

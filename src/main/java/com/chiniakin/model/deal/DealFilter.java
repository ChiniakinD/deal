package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * Модель фильтра для сделок.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Модель фильтра для сделок")
public class DealFilter {

    @Schema(description = "id сделки")
    private UUID id;

    @Schema(description = "Описание сделки")
    private String description;

    @Schema(description = "Номер договора")
    @JsonProperty("agreement_number")
    private String agreementNumber;

    @Schema(description = "Дата начала договора")
    @JsonProperty("agreement_date_from")
    private LocalDate agreementDateFrom;

    @Schema(description = "Дата окончания договора")
    @JsonProperty("agreement_date_to")
    private LocalDate agreementDateTo;

    @Schema(description = "Дата начала срока действия сделки")
    @JsonProperty("availability_date_from")
    private LocalDate availabilityDateFrom;

    @Schema(description = "Дата окончания срока действия сделки")
    @JsonProperty("availability_date_to")
    private LocalDate availabilityDateTo;

    @JsonProperty("type")
    @Schema(description = "Список типов сделок", implementation = DealTypeResponse.class)
    private List<DealTypeResponse> dealTypeResponses;

    @JsonProperty("status")
    @Schema(description = "Список статусов сделок", implementation = DealStatusResponse.class)
    private List<DealStatusResponse> dealStatusResponses;

    @JsonProperty("close_dt_from")
    @Schema(description = "Дата и время закрытия сделки с")
    private LocalDateTime closeDtFrom;

    @JsonProperty("close_dt_to")
    @Schema(description = "Дата и время закрытия сделки по")
    private LocalDateTime closeDtTo;

    @JsonProperty("contractor_id")
    @Schema(description = "id контрагента сделки")
    private String contractorId;

    @Schema(description = "Имя контрагента сделки")
    private String name;

    @Schema(description = "ИНН контрагента сделки")
    private String inn;

    @Schema(description = "Размер страницы")
    @JsonProperty("size")
    private Integer size = 10;

    @JsonProperty("page")
    @Schema(description = "Номер страницы")
    private Integer page = 1;

}

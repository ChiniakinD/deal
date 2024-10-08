package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Модель для изменения статуса сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@RequiredArgsConstructor
@Schema(name = "Модель для изменения статуса сделки")
public class ChangeStatusModel {

    @JsonProperty("deal_id")
    @Schema(description = "id сделки")
    private UUID dealId;

    @JsonProperty("deal_status_id")
    @Schema(description = "id статуса сделки")
    private String dealStatusId;

}

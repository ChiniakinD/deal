package com.chiniakin.model.dealcontractor;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * Модель контрагента сделки для сохранения нового/обновления существующего.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Schema(name = "Модель контрагента сделки для сохранения нового/обновления существующего.")
public class SaveDealContractorModel {

    @Schema(description = "id контрагента сделки")
    private UUID id;

    @Schema(description = "id сделки")
    @JsonProperty("deal_id")
    private UUID dealId;

    @Schema(description = "id контрагента")
    @JsonProperty("contractor_id")
    private String contractorID;

    @Schema(description = "Имя контрагента сделки")
    private String name;

    @Schema(description = "ИНН контрагента сделки")
    private String inn;

    @Schema(description = "Является ли контрагент основным")
    private boolean main;

}

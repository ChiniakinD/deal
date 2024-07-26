package com.chiniakin.model.deal;

import com.chiniakin.model.contractorrole.ContractorRoleModel;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;
import java.util.UUID;

/**
 * Модель контрагента сделки для модели сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Accessors(chain = true)
@Schema(name = "Модель контрагента сделки для сделки")
public class DealContractorResponse {

    @Schema(description = "id контрагента сделки")
    private UUID id;

    @Schema(description = "id контрагента")
    private String contractorID;

    @Schema(description = "Имя контрагента")
    private String name;

    @Schema(description = "Является ли контрагент основным")
    private boolean main;

    @JsonProperty("roles")
    @Schema(description = "Роли контрагента")
    private List<ContractorRoleModel> dealContractorRoleModel;

}

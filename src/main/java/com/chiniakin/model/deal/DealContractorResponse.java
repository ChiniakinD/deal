package com.chiniakin.model.deal;

import com.chiniakin.model.contractorrole.ContractorRoleModel;
import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DealContractorResponse {

    private UUID id;

    private String contractorID;

    private String name;

    private boolean main;

    @JsonProperty("roles")
    private List<ContractorRoleModel> dealContractorRoleModel;

}

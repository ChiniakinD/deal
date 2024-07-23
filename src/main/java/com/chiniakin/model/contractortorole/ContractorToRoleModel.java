package com.chiniakin.model.contractortorole;

import com.chiniakin.entity.ContractorRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Модель контрагента сделки и его роли.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Модель роли контрагентов в сделке")
public class ContractorToRoleModel {

    @Id
    @JsonProperty("id")
    @Schema(description = "id контрагента сделки")
    private UUID id;

    @ManyToOne
    @JsonProperty("role_id")
    @Schema(description = "id роли")
    private ContractorRole roleId;

}

package com.chiniakin.model.contractorrole;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.Setter;

/**
 * Модель роли контрагента сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Schema(name = "Модель роли контрагентов")
public class ContractorRoleModel {

    @Schema(description = "id роли")
    private String id;

    @Schema(description = "Наименование")
    private String name;

    @Schema(description = "Категория роли")
    private String category;

}

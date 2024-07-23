package com.chiniakin.model.dealcontractor;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

/**
 * Модель контрагента сделки для фильтрации сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@RequiredArgsConstructor
@Schema(name = "Модель контрагента сделки для фильтрации сделок")
public class ContractorFilter {

    @Schema(description = "id контрагента сделки")
    private String id;

    @Schema(description = "Имя контрагента сделки")
    private String name;

    @Schema(description = "ИНН контрагента сделки")
    private String inn;

}

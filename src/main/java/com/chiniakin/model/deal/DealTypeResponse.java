package com.chiniakin.model.deal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Модель типа сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Schema(name = "Модель типа сделки")
public class DealTypeResponse {

    @Schema(description = "id типа сделки")
    private String id;

    @Schema(description = "Наименование")
    private String name;

}

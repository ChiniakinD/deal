package com.chiniakin.model.deal;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * Модель статуса сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@NoArgsConstructor
@Accessors(chain = true)
@Schema(name = "Модель статуса сделки")
public class DealStatusResponse {

    @Schema(description = "id статуса сделки")
    private String id;

    @Schema(description = "Наименование")
    private String name;

}

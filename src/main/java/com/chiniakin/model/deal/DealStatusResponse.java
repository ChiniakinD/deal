package com.chiniakin.model.deal;

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
public class DealStatusResponse {

    private String id;

    private String name;

}

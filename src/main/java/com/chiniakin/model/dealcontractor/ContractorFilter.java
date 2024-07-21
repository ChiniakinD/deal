package com.chiniakin.model.dealcontractor;

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
public class ContractorFilter {

    private String id;

    private String name;

    private String inn;

}

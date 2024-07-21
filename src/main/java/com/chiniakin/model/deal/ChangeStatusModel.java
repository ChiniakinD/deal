package com.chiniakin.model.deal;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import java.util.UUID;

/**
 * Модель для изменения статуса сделки.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@RequiredArgsConstructor
public class ChangeStatusModel {

    @JsonProperty("deal_id")
    private UUID dealId;

    @JsonProperty("deal_status_id")
    private String dealStatusId;

}

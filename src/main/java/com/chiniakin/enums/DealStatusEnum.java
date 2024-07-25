package com.chiniakin.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DealStatusEnum {

    DRAFT("DRAFT"),
    ACTIVE("ACTIVE"),
    CLOSED("CLOSED");

    private final String statusId;

    public static String getDealStatusEnumById(String statusId) {
        for (DealStatusEnum dealStatusEnum : DealStatusEnum.values()) {
            if (dealStatusEnum.getStatusId().equals(statusId)) {
                return dealStatusEnum.getStatusId();
            }
        }
        throw new IllegalArgumentException("Такого статуса не существует");
    }

}

package com.chiniakin.model.dealcontractor;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
public class SaveDealContractorModel {

    private UUID id;
    @JsonProperty("deal_id")
    private UUID dealId;

    @JsonProperty("contractor_id")
    private String contractorID;

    private String name;

    private String inn;

    private boolean main;

}

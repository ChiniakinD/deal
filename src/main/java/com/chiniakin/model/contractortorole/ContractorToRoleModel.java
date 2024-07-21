package com.chiniakin.model.contractortorole;

import com.chiniakin.entity.ContractorRole;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
public class ContractorToRoleModel {

    @Id
    @JsonProperty("id")
    private UUID id;

    @ManyToOne
    @JsonProperty("role_id")
    private ContractorRole roleId;

}

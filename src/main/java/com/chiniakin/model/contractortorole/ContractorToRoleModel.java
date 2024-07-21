package com.chiniakin.model.contractortorole;

import com.chiniakin.entity.ContractorRole;

import jakarta.persistence.Id;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
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
    @JoinColumn(name = "id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private ContractorRole roleId;

}
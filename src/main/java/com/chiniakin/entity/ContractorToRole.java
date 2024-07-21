package com.chiniakin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Column;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.UUID;

@Getter
@Setter
@Entity
@NoArgsConstructor
@Accessors(chain = true)
@Table(name = "contractor_to_role")
public class ContractorToRole {

    @Id
    @JoinColumn(name = "contractor_id")
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "role_id")
    private ContractorRole roleId;

    @Column(name = "is_active")
    private boolean isActive;

}

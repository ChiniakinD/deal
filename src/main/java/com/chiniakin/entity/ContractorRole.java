package com.chiniakin.entity;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "contractor_role")
@Schema(name = "")
@Accessors(chain = true)
public class ContractorRole {

    @Id
    @Column(name = "id")
    private String id;

    @Column(name = "name")
    private String name;

    @Column(name = "category")
    private String category;

    @Column(name = "is_active")
    private boolean isActive;

}

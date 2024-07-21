package com.chiniakin.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;
import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.util.UUID;

/**
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "deal_contractor")
@Accessors(chain = true)
public class DealContractor {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @ManyToOne
    @JoinColumn(name = "deal_id")
    private Deal deal;

    @Column(name = "contractor_id")
    private String contractorId;

    @Column(name = "name")
    private String name;

    @Column(name = "inn")
    private String inn;

    @Column(name = "main")
    private boolean main = false;

    @Column(name = "create_date")
    private LocalDate createDate = LocalDate.now();

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "modify_user_id")
    private String modifyUserId;

    @Column(name = "is_active")
    private boolean isActive = true;

}

package com.chiniakin.entity;

import jakarta.persistence.Id;
import jakarta.persistence.FetchType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.OneToMany;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

/**
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@Accessors(chain = true)
@NoArgsConstructor
@Table(name = "deal")
public class Deal {

    @Id
    @Column(name = "id")
    private UUID id;

    @Column(name = "description")
    private String description;

    @Column(name = "agreement_number")
    private String agreementNumber;

    @Column(name = "agreement_date")
    private LocalDate agreementDate;

    @Column(name = "agreement_start_dt")
    private LocalDateTime agreementStartDt;

    @Column(name = "availability_date")
    private LocalDate availabilityDate;

    @ManyToOne
    @JoinColumn(name = "type")
    private DealType type;

    @ManyToOne
    @JoinColumn(name = "status")
    private DealStatus status;

    @Column(name = "sum")
    private BigDecimal sum;

    @Column(name = "close_dt")
    private LocalDateTime closeDt;

    @Column(name = "create_date")
    private LocalDate createDate = LocalDate.now();

    @Column(name = "modify_date")
    private LocalDate modifyDate;

    @Column(name = "create_user_id")
    private String createUserId;

    @Column(name = "modify_user_id")
    private String modifyUserId;

    @Column(name = "is_active")
    private boolean isActive;

    @OneToMany(mappedBy = "deal", fetch = FetchType.LAZY)
    private List<DealContractor> dealContractor;

}

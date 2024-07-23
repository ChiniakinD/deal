package com.chiniakin.entity;

import com.chiniakin.enums.MessageStatus;

import jakarta.persistence.Id;
import jakarta.persistence.Column;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * Сущность для хранения сообщений.
 *
 * @author ChiniakinD
 */
@Getter
@Setter
@Entity
@NoArgsConstructor
@Table(name = "outbox_messages")
@Accessors(chain = true)
public class OutboxMessage {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(name = "contractor_id")
    private String contractorId;

    @Column(name = "sign")
    private boolean sign;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private MessageStatus status;

    @Column(name = "created_at")
    private LocalDateTime creationTime = LocalDateTime.now();

}

package com.chiniakin.repository;

import com.chiniakin.entity.OutboxMessage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с сообщениями.
 *
 * @author ChiniakinD
 */
@Repository
public interface OutboxRepository extends JpaRepository<OutboxMessage, UUID> {

    /**
     * Получает все сообщения со статусом отправки ERROR.
     *
     * @return список неотправленных сообщний.
     */
    @Query(value = "select * from outbox_messages o where o.status='ERROR'", nativeQuery = true)
    List<OutboxMessage> findAllErrorMessages();

    /**
     * Меняет статус сообщения на SUCCESS.
     *
     * @param uuid идентификатор сообщения.
     */
    @Modifying
    @Transactional
    @Query(value = "update outbox_messages set status='SUCCESS' where id=:uuid", nativeQuery = true)
    void updateStatus(@Param("uuid") UUID uuid);

}

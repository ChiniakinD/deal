package com.chiniakin.repository;

import com.chiniakin.entity.Deal;
import com.chiniakin.entity.DealStatus;
import com.chiniakin.exception.DealNotFoundException;
import jakarta.transaction.Transactional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;
import java.util.UUID;

/**
 * Репозиторий для работы с сделками.
 *
 * @author ChiniakinD
 */
public interface DealRepository extends JpaRepository<Deal, UUID> {

    /**
     * Получает сделку со статусом и типом по id.
     *
     * @param id идентификатор сделки.
     * @return сделка с дополнительной информацией.
     */
    @Query("select d from Deal d join fetch d.type join fetch d.status where d.id = :id")
    Optional<Deal> findByIdWithDetails(@Param("id") UUID id);

    /**
     * Получает сделку со статусом и типом по id или выбрасывает исключение.
     *
     * @param id идентификатор сделки.
     * @return сделка с дополнительной информацией.
     */
    default Deal findByIdWithDetailsOrThrow(UUID id) {
        return findByIdWithDetails(id)
                .orElseThrow(() -> new DealNotFoundException("Сделка с id " + id + "не найдена."));
    }

    /**
     * Устанвливает статус сделки.
     *
     * @param id         идентификатор сделки.
     * @param dealStatus новый статус.
     */
    @Transactional
    @Modifying
    @Query("update Deal d set d.status=:status where d.id=:id")
    void changeStatusDeal(@Param("id") UUID id, @Param("status") DealStatus dealStatus);

    /**
     * Находит все сделки с учетом спецификации и пагинации.
     *
     * @param spec     спецификация для фильтрации.
     * @param pageable параметры пагинации.
     * @return страница со сделками.
     */
    Page<Deal> findAll(Specification<Deal> spec, Pageable pageable);

}

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

public interface DealRepository extends JpaRepository<Deal, UUID> {

    @Query("select d from Deal d join fetch d.type join fetch d.status where d.id = :id")
    Optional<Deal> findByIdWithDetails(@Param("id") UUID id);

    default Deal findByIdWithDetailsOrThrow(UUID id) {
        return findByIdWithDetails(id)
                .orElseThrow(() -> new DealNotFoundException("Сделка с id " + id + "не найдена."));
    }

    @Transactional
    @Modifying
    @Query("update Deal d set d.status=:status where d.id=:id")
    void changeStatusDeal(@Param("id") UUID id, @Param("status") DealStatus dealStatus);

    Page<Deal> findAll(Specification<Deal> spec, Pageable pageable);

}

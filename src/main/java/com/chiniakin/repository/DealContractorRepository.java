package com.chiniakin.repository;

import com.chiniakin.entity.DealContractor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с контрагентами сделок.
 *
 * @author ChiniakinD
 */
@Repository
public interface DealContractorRepository extends JpaRepository<DealContractor, UUID> {

    /**
     * Получает всех контрагентов сделки.
     *
     * @param dealId идентификатор сделки.
     * @return контрагентов сделки.
     */
    @Query(value = "select dc from DealContractor dc " +
            "where dc.deal.id = :dealId and dc.isActive is true")
    List<DealContractor> findAllByDealId(@Param("dealId") UUID dealId);

    /**
     * Выполняет логическое удаление контрагента сделки.
     *
     * @param id идентификатор контрагента сделки.
     */
    @Transactional
    @Modifying
    @Query("update DealContractor dc set dc.isActive=false where dc.id=:id")
    void logicDelete(@Param("id") UUID id);

}

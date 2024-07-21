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

@Repository
public interface DealContractorRepository extends JpaRepository<DealContractor, UUID> {

    @Query(value = "select dc from DealContractor dc " +
            "where dc.deal.id = :dealId and dc.isActive is true")
    List<DealContractor> findAllByDealId(@Param("dealId") UUID dealId);

    @Transactional
    @Modifying
    @Query("update DealContractor dc set dc.isActive=false where dc.id=:id")
    void logicDelete(@Param("id") UUID id);

}

package com.chiniakin.repository;

import com.chiniakin.entity.ContractorToRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface ContractorToRoleRepository extends JpaRepository<ContractorToRole, UUID> {

    @Query("update ContractorToRole ctr set ctr.isActive=true where ctr.id = :contractorId")
    void logicDelete(@Param("contractorId") UUID contractorId);

}

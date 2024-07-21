package com.chiniakin.repository;

import com.chiniakin.entity.ContractorRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

@Repository
public interface ContractorRoleRepository extends JpaRepository<ContractorRole, String> {

    @Query(value = "select * from contractor_role cr " +
            "where cr.id = (select ctr.role_id from contractor_to_role ctr where ctr.contractor_id = :dealId)", nativeQuery = true)
    List<ContractorRole> findAllRolesByDealId(@Param("dealId") UUID dealId);

}

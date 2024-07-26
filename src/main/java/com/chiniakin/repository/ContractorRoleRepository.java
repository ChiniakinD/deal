package com.chiniakin.repository;

import com.chiniakin.entity.ContractorRole;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.UUID;

/**
 * Репозиторий для работы с ролями контрагентов.
 *
 * @author ChiniakinD
 */
@Repository
public interface ContractorRoleRepository extends JpaRepository<ContractorRole, String> {

    /**
     * Получает все роли, связанные с конкретным контрагентом сделки.
     *
     * @param contractorId идентификатор контрагента сделки.
     * @return список ролей контрагента сделки.
     */
    @Query(value = "select * from contractor_role cr " +
            "where cr.id in (select ctr.role_id from contractor_to_role ctr where ctr.contractor_id = :contractorId)", nativeQuery = true)
    List<ContractorRole> findAllRolesByDealContractorId(@Param("contractorId") UUID contractorId);

}

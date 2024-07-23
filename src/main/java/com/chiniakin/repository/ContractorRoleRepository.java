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
     * Получает все роли, связанные со сделкой.
     *
     * @param dealId идентификатор сделки.
     * @return список ролей контрагентов сделки.
     */
    @Query(value = "select * from contractor_role cr " +
            "where cr.id = (select ctr.role_id from contractor_to_role ctr where ctr.contractor_id = :dealId)", nativeQuery = true)
    List<ContractorRole> findAllRolesByDealContractorId(@Param("dealId") UUID dealId);

}

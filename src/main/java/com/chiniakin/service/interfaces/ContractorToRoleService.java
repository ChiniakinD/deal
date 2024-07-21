package com.chiniakin.service.interfaces;

import com.chiniakin.model.contractortorole.ContractorToRoleModel;

import java.util.UUID;

/**
 * Интерфейс сервиса для работы с ролями контрагентов сделки.
 *
 * @author ChiniakinD
 */
public interface ContractorToRoleService {

    /**
     * Добавляет роль контрагенту сделки.
     *
     * @param contractorToRoleModel модель роли контрагента сделки.
     */
    void addRole(ContractorToRoleModel contractorToRoleModel);

    /**
     * Удаляет роль контрагента сделки по id.
     *
     * @param contractorId идентификатор контрагента сделки.
     */
    void delete(UUID contractorId);

}

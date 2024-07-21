package com.chiniakin.service.interfaces;

import com.chiniakin.model.contractortorole.ContractorToRoleModel;

import java.util.UUID;

public interface ContractorToRoleService {

    void addRole(ContractorToRoleModel contractorToRoleModel);

    void delete(UUID contractorId);

}

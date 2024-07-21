package com.chiniakin.service;

import com.chiniakin.entity.ContractorToRole;
import com.chiniakin.model.contractortorole.ContractorToRoleModel;
import com.chiniakin.repository.ContractorToRoleRepository;
import com.chiniakin.service.interfaces.ContractorToRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class ContractorToRoleServiceImpl implements ContractorToRoleService {

    private final ContractorToRoleRepository contractorToRoleRepository;

    public void addRole(ContractorToRoleModel contractorToRoleModel) {
        ContractorToRole contractorToRole = new ContractorToRole()
                .setId(contractorToRoleModel.getId())
                .setRoleId(contractorToRoleModel.getRoleId())
                .setActive(true);
        contractorToRoleRepository.save(contractorToRole);
    }

    @Override
    public void delete(UUID contractorId) {
        contractorToRoleRepository.logicDelete(contractorId);
    }

}

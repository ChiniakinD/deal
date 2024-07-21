package com.chiniakin.controller;

import com.chiniakin.model.contractortorole.ContractorToRoleModel;
import com.chiniakin.service.interfaces.ContractorToRoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.UUID;

@RestController
@RequiredArgsConstructor
@RequestMapping("/contractor-to-role")
public class ContractorToRoleController {

    private final ContractorToRoleService contractorToRoleService;

    @PostMapping("/add")
    public void addContractorToRole(@RequestBody ContractorToRoleModel contractorToRoleModel) {
        contractorToRoleService.addRole(contractorToRoleModel);
    }

    @DeleteMapping("/delete")
    public void deleteContractorToRole(@RequestBody UUID contractorId) {
        contractorToRoleService.delete(contractorId);

    }

}

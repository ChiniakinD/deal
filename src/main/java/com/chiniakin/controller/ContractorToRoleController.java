package com.chiniakin.controller;

import com.chiniakin.model.contractortorole.ContractorToRoleModel;
import com.chiniakin.service.interfaces.ContractorToRoleService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ru.chiniakin.aspect.AuditLog;

import java.util.UUID;

/**
 * Контроллер для управления контрагентами сделок и их ролями.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/contractor-to-role")
@Tag(name = "ContractorToRoleController", description = "Контроллер для работы с контрагентами сделок и их ролями.")
public class ContractorToRoleController {

    private final ContractorToRoleService contractorToRoleService;

    /**
     * Добавляет роль существующего контрагента сделки
     *
     * @param contractorToRoleModel Модель контрагента сделки и его роли.
     */
    @Operation(summary = "Сохранение роли контрагента сделки", description = "Добавляет новую роль контрагенту сделки")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль контрагента сделки добавлена")
    })
    @AuditLog
    @PostMapping("/add")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER, T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public void addContractorToRole(@RequestBody ContractorToRoleModel contractorToRoleModel) {
        contractorToRoleService.addRole(contractorToRoleModel);
    }

    /**
     * Выполняет логическое удаление роли у существующего контрагента сделки.
     *
     * @param id идентификатор контрагента сделки.
     */
    @Operation(summary = "Логическое удаление роли контрагента сделки", description = "Выполняет удаление роли контрагента сделки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Роль контрагента сделки успешно удален")
    })
    @AuditLog
    @DeleteMapping("/delete/{id}")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER, T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public void deleteContractorToRole(@PathVariable UUID id) {
        contractorToRoleService.delete(id);

    }

}

package com.chiniakin.controller;

import com.chiniakin.model.deal.ChangeStatusModel;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.SaveDealModel;
import com.chiniakin.service.interfaces.DealService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ru.chiniakin.aspect.AuditLog;

import java.util.UUID;

/**
 * Контроллер для управления сделками.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/deal")
@Tag(name = "DealController", description = "Контроллер для работы со сделками")
public class DealController {

    private final DealService dealService;

    /**
     * Получает сделку по id.
     *
     * @param id идентификатор сделки.
     * @return модель сделки
     */
    @Operation(summary = "Получение сделки по id", description = "Получение информации о сделке по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DealModel.class)
                            )
                    }),
            @ApiResponse(responseCode = "404", description = "Сделка с таким id не найдена")
    })
    @AuditLog
    @GetMapping("/{id}")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).USER, T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER," +
            " T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public DealModel getDealById(@PathVariable UUID id) {
        return dealService.getDealById(id);
    }

    /**
     * Меняет статус сделки.
     *
     * @param changeStatusModel модель для изменения статуса сделки.
     */
    @Operation(summary = "Меняет статус сделки", description = "Меняет статус сделки согласно модели")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Статус сделки обновлен"),
            @ApiResponse(responseCode = "404", description = "Сделка с таким id не найдена")
    })
    @AuditLog
    @PatchMapping("/change/status")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER, T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public void changeDealStatus(@RequestBody ChangeStatusModel changeStatusModel) {
        dealService.changeStatus(changeStatusModel);
    }

    /**
     * Сохраняет новую или обновляет существующую сделку.
     *
     * @param saveDealModel модель для сохранения новой или обновления существующей сделки.
     */
    @Operation(summary = "Сохранение сделки", description = "Добавляет новую сделку или обновляет уже существующую")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Сделка добавлена или обновлена")
    })
    @AuditLog
    @PutMapping("/save")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER, T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public void saveDeal(@RequestBody SaveDealModel saveDealModel) {
        dealService.saveDeal(saveDealModel);
    }

    /**
     * Получает сделки согласно фильтрам.
     *
     * @param dealFilter модель фильтра для сделок.
     * @return страница с моделями сделок.
     */
    @Operation(summary = "Получение всех сделок с использованием фильтров", description = "Получение информации о всех сделках с использованием фильтров")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Ok",
                    content = {
                            @Content(
                                    schema = @Schema(implementation = DealModel.class)
                            )
                    })
    })
    @AuditLog
    @PostMapping("/search")
    @PreAuthorize("hasAnyAuthority(T(com.chiniakin.enums.auth.RoleEnum).CREDIT_USER, T(com.chiniakin.enums.auth.RoleEnum).OVERDRAFT_USER," +
            "T(com.chiniakin.enums.auth.RoleEnum).DEAL_SUPERUSER, T(com.chiniakin.enums.auth.RoleEnum).SUPERUSER)")
    public Page<DealModel> searchDeals(@RequestBody DealFilter dealFilter) {
        return dealService.getDealsByFilters(dealFilter);
    }

}

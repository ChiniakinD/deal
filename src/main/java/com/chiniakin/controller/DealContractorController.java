package com.chiniakin.controller;

import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import com.chiniakin.service.interfaces.DealContractorService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;
import ru.chiniakin.aspect.AuditLog;

import java.util.UUID;

/**
 * Контроллер для управления контрагентами сделок.
 *
 * @author ChiniakinD
 */
@RestController
@AllArgsConstructor
@RequestMapping("/deal-contractor")
@Tag(name = "DealContractorController", description = "Контроллер для работы со контрагентами сделок.")
public class DealContractorController {

    private DealContractorService dealContractorService;

    /**
     * Соххраняет нового/обновляет существующего контрагента сделки.
     *
     * @param saveDealContractorModel модель для сохранения/обновления контрагента сделки.
     */
    @Operation(summary = "Сохранение контрагента сделки", description = "Добавляет нового контрагента сделки или обновляет уже существующего")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контрагента сделки добавлен или обновлен"),
            @ApiResponse(responseCode = "404", description = "Невозможно добавить более 1 главного контрагента на 1 сделку.")

    })
    @AuditLog
    @PutMapping("/save")
    public void saveDealContractor(@RequestBody SaveDealContractorModel saveDealContractorModel) {
        dealContractorService.saveDealContractor(saveDealContractorModel);
    }

    /**
     * Выполняет логическое удаление контрагента сделки по id.
     *
     * @param id идентификатор котрагента сделки.
     */
    @Operation(summary = "Логическое удаление контрагента сделки", description = "Выполняет удаление контрагента сделки по id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Контрагента сделки успешно удален")
    })
    @AuditLog
    @DeleteMapping("/delete/{id}")
    public void deleteDealContractorById(@PathVariable UUID id) {
        dealContractorService.deleteDealContractorById(id);
    }

}

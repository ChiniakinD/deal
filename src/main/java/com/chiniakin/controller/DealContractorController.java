package com.chiniakin.controller;

import com.chiniakin.model.dealcontractor.SaveDealContractorModel;
import com.chiniakin.service.interfaces.DealContractorService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * Контроллер для управления контрагентами сделок.
 *
 * @author ChiniakinD
 */
@RestController
@AllArgsConstructor
@RequestMapping("/deal-contractor")
public class DealContractorController {

    private DealContractorService dealContractorService;

    /**
     * Соххраняет нового/обновляет существующего контрагента сделки.
     *
     * @param saveDealContractorModel модель для сохранения/обновления контрагента сделки.
     */
    @PutMapping("/save")
    public void saveDealContractor(@RequestBody SaveDealContractorModel saveDealContractorModel) {
        dealContractorService.saveDealContractor(saveDealContractorModel);
    }

    /**
     * Выполняет логическое удаление контрагента сделки по id.
     *
     * @param id идентификатор котрагента сделки.
     */
    @DeleteMapping("/delete/{id}")
    public void deleteDealContractorById(@PathVariable UUID id) {
        dealContractorService.deleteDealContractorById(id);
    }

}

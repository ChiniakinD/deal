package com.chiniakin.controller;

import com.chiniakin.model.deal.ChangeStatusModel;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.SaveDealModel;
import com.chiniakin.service.interfaces.DealService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.UUID;

/**
 * Контроллер для управления сделками.
 *
 * @author ChiniakinD
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/deal")
public class DealController {

    private final DealService dealService;

    /**
     * Получает сделку по id.
     *
     * @param id идентификатор сделки.
     * @return модель сделки
     */
    @GetMapping("/{id}")
    public DealModel getDealById(@PathVariable UUID id) {
        return dealService.getDealById(id);
    }

    /**
     * Меняет статус сделки.
     *
     * @param changeStatusModel модель для изменения статуса сделки.
     */
    @PatchMapping("/change/status")
    public void changeDealStatus(@RequestBody ChangeStatusModel changeStatusModel) {
        dealService.changeStatus(changeStatusModel);
    }

    /**
     * Сохраняет новую или обновляет существующую сделку.
     *
     * @param saveDealModel модель для сохранения новой или обновления существующей сделки.
     */
    @PutMapping("/save")
    public void saveDeal(@RequestBody SaveDealModel saveDealModel) {
        dealService.saveDeal(saveDealModel);
    }

    /**
     * Получает сделки согласно фильтрам.
     *
     * @param dealFilter модель фильтра для сделок.
     * @return страница с моделями сделок.
     */
    @PostMapping("/search")
    public Page<DealModel> searchDeals(@RequestBody DealFilter dealFilter) {
        return dealService.getDealsByFilters(dealFilter);
    }

}

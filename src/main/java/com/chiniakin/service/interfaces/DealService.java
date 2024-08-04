package com.chiniakin.service.interfaces;

import com.chiniakin.model.deal.ChangeStatusModel;
import com.chiniakin.model.deal.DealFilter;
import com.chiniakin.model.deal.DealModel;
import com.chiniakin.model.deal.SaveDealModel;
import org.springframework.data.domain.Page;
import java.util.UUID;

/**
 * Интерфейс сервиса для работы со сделками.
 *
 * @author ChiniakinD
 */
public interface DealService {

    /**
     * Получает сделку по id.
     *
     * @param id идентификатор сделки.
     * @return DealModel модель сделки.
     */
    DealModel getDealById(UUID id);

    /**
     * Изменяет статус сделки.
     *
     * @param changeStatusModel модель для изменения статусаа сделки.
     */
    void changeStatus(ChangeStatusModel changeStatusModel);

    /**
     * Добавляет новую сделку или обновляет существующую.
     *
     * @param saveDealModel модель сделки для добавления/обновления.
     */
    void saveDeal(SaveDealModel saveDealModel);

    /**
     * Получает сделки с использованием фильтров.
     *
     * @param dealFilter модель фильтра.
     * @return страница с моделями сделок.
     */
    Page<DealModel> getDealsByFilters(DealFilter dealFilter);

}

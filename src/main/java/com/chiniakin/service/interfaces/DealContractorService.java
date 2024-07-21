package com.chiniakin.service.interfaces;

import com.chiniakin.model.dealcontractor.SaveDealContractorModel;

import java.util.UUID;

/**
 * Интерфейс сервиса для работы с контрагентами сделки.
 *
 * @author ChiniakinD
 */
public interface DealContractorService {

    /**
     * Добавляет нового контрагента сделки или обновляет существующего.
     *
     * @param saveDealContractorModel модель сделки для добавления/обновления.
     */
    void saveDealContractor(SaveDealContractorModel saveDealContractorModel);

    /**
     * Выполняет логическое удаление контрагента сделки.
     *
     * @param dealContractorId идентификатор контрагента сделки.
     */
    void deleteDealContractorById(UUID dealContractorId);

}

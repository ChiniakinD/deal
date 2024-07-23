package com.chiniakin.service.interfaces;

import org.springframework.http.HttpStatusCode;

/**
 * Интерфейс для отправки запросов к contractor.
 *
 * @author ChiniakinD
 */
public interface HttpClientService {

    /**
     * Запрос на обновление active_main_borrower у контрагента.
     *
     * @param id   идентификатор контрагента.
     * @param sign наличие сделок.
     * @return код статуса ответа.
     */
    HttpStatusCode sendRequestToContractor(String id, Boolean sign);

}

package com.chiniakin.service;

import com.chiniakin.entity.OutboxMessage;
import com.chiniakin.enums.MessageStatus;
import com.chiniakin.repository.OutboxRepository;
import com.chiniakin.service.interfaces.HttpClientService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.time.LocalDateTime;

/**
 * Реализация сервиса для отправки запросов к contractors.
 *
 * @author ChiniakinD
 */
@Service
@Slf4j
@RequiredArgsConstructor
public class HttpClientServiceImpl implements HttpClientService {

    private final RestTemplate restTemplate;
    private final ObjectMapper objectMapper;
    private final OutboxRepository outboxRepository;

    @Value("${contractor.url}")
    private String url;

    /**
     * Отправляет запрос на обновление active_main_borrower у контрагента в contractor.
     *
     * @param id   идентификатор контрагента.
     * @param sign наличие сделок.
     * @return статус ответа.
     */
    public HttpStatusCode sendRequestToContractor(String id, Boolean sign) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String requestBody = createRequestBody(sign);
        HttpEntity<String> entity = new HttpEntity<>(requestBody, headers);
        try {
            ResponseEntity<String> exchange = restTemplate.exchange(
                    generateUrl(id),
                    HttpMethod.PATCH,
                    entity,
                    String.class
            );
            saveOutBoxMessage(id, sign, exchange.getStatusCode());
            return exchange.getStatusCode();
        } catch (RuntimeException e) {
            saveOutBoxMessage(id, sign, HttpStatus.INTERNAL_SERVER_ERROR);
            throw new RuntimeException(e.getMessage());
        }
    }

    /**
     * Сохраняет сообщение со статусом.
     *
     * @param id             идентификатор контрагента.
     * @param sign           наличие сделок.
     * @param httpStatusCode статус ответа.
     */
    private void saveOutBoxMessage(String id, Boolean sign, HttpStatusCode httpStatusCode) {
        OutboxMessage outboxMessage = new OutboxMessage()
                .setContractorId(id)
                .setStatus(httpStatusCode.equals(HttpStatus.OK) ? MessageStatus.SUCCESS : MessageStatus.ERROR)
                .setSign(sign)
                .setCreationTime(LocalDateTime.now());
        outboxRepository.save(outboxMessage);
    }

    /**
     * @param sign наличие сделок.
     * @return тело запроса.
     */
    private String createRequestBody(Boolean sign) {
        try {
            return objectMapper.writeValueAsString(sign);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Генерирует URL запроса.
     *
     * @param id идентификатор контрагента.
     * @return URL запроса.
     */
    private String generateUrl(String id) {
        return url + "main-borrower/" + id;
    }

}

package com.chiniakin.service;

import com.chiniakin.entity.OutboxMessage;
import com.chiniakin.enums.MessageStatus;
import com.chiniakin.repository.OutboxRepository;
import com.chiniakin.service.interfaces.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Сервис для выполнения задач по расписанию.
 *
 * @author ChiniakinD
 */
@Component
@RequiredArgsConstructor
public class ScheduleService {

    private final OutboxRepository outboxRepository;
    private final HttpClientService httpClientService;

    /**
     * Задача по расписанию для повторной отправки сообщений со status ERROR.
     */
    @Transactional
    @Scheduled(cron = "${contractor.cron}")
    public void task() {
        List<OutboxMessage> errorMessages = outboxRepository.findAllErrorMessages();
        for (OutboxMessage errorMessage : errorMessages) {
            List<OutboxMessage> successMessages = outboxRepository.findSuccessMessagesAfter(
                    errorMessage.getContractorId(),
                    errorMessage.getCreationTime()
            );
            if (successMessages.isEmpty()) {
                processMessage(errorMessage);
            }
        }
    }

    /**
     * Обрабатывает одно сообщение, проверяя его статус и отправляя запрос.
     * Если запрос успешен и статус сообщения все еще ERROR, обновляет статус сообщения на SUCCESS.
     *
     * @param outboxMessage сообщение для обработки.
     */
    private void processMessage(OutboxMessage outboxMessage) {
        if (outboxMessage.getStatus().equals(MessageStatus.ERROR)) {
            HttpStatusCode httpStatusCode = httpClientService.sendRequestToContractor(outboxMessage.getContractorId(), outboxMessage.isSign());
            if (httpStatusCode.equals(HttpStatus.OK)) {
                outboxRepository.updateStatus(outboxMessage.getId());
            }
        }
    }

}

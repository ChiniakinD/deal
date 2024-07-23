package com.chiniakin.service;

import com.chiniakin.entity.OutboxMessage;
import com.chiniakin.repository.OutboxRepository;
import com.chiniakin.service.interfaces.HttpClientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Сервис для выполнения задач по расписанию.
 *
 * @author ChiniakinD
 */
@Component
@RequiredArgsConstructor
public class ScheduledService {

    private final OutboxRepository outboxRepository;
    private final HttpClientService httpClientService;

    /**
     * Задача по расписанию для повторной отправки сообщений со status ERROR.
     */
    @Scheduled(cron = "${contractor.cron}")
    public void task() {
        List<OutboxMessage> errorTasks = getErrorTasks();
        for (OutboxMessage outboxMessage : errorTasks) {
            HttpStatusCode httpStatusCode = httpClientService.sendRequestToContractor(outboxMessage.getContractorId(), outboxMessage.isSign());
            if (httpStatusCode.equals(HttpStatus.OK)) {
                outboxRepository.updateStatus(outboxMessage.getId());
            }
        }
    }

    private List<OutboxMessage> getErrorTasks() {
        return outboxRepository.findAllErrorMessages();
    }

}

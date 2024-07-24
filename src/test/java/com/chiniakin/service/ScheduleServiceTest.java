package com.chiniakin.service;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.OutboxMessage;
import com.chiniakin.enums.MessageStatus;
import com.chiniakin.repository.OutboxRepository;
import com.chiniakin.service.interfaces.HttpClientService;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.UUID;

import static org.mockito.Mockito.*;

@Testcontainers
@SpringBootTest(classes = TestBeans.class)
public class ScheduleServiceTest {

    @Mock
    private OutboxRepository outboxRepository;

    @Mock
    private HttpClientService httpClientService;

    @InjectMocks
    private ScheduleService scheduleService;

    @Test
    void taskShouldResendMessagesWithErrorStatus() {
        OutboxMessage errorMessage = new OutboxMessage()
                .setId(UUID.randomUUID())
                .setContractorId("a0ef4aa9-0769-4c14-946f-214306eb7f39")
                .setSign(true)
                .setStatus(MessageStatus.ERROR)
                .setCreationTime(LocalDateTime.now());
        when(outboxRepository.findAllErrorMessages()).thenReturn(List.of(errorMessage));
        when(outboxRepository.findSuccessMessagesAfter(errorMessage.getContractorId(), errorMessage.getCreationTime()))
                .thenReturn(Collections.emptyList());
        when(httpClientService.sendRequestToContractor(errorMessage.getContractorId(), errorMessage.isSign()))
                .thenReturn(HttpStatus.OK);
        scheduleService.task();
        verify(outboxRepository, times(1)).updateStatus(errorMessage.getId());
    }
}


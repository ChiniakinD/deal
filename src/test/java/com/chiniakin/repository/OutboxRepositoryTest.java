package com.chiniakin.repository;

import com.chiniakin.TestBeans;
import com.chiniakin.entity.OutboxMessage;
import com.chiniakin.enums.MessageStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@Testcontainers
@SpringBootTest(classes = TestBeans.class)
public class OutboxRepositoryTest {

    @Autowired
    private OutboxRepository outboxRepository;

    @BeforeEach
    void setUp() {
        outboxRepository.deleteAll();
        OutboxMessage message1 = new OutboxMessage()
                .setContractorId("a0ef4aa9-0769-4c14-946f-214306eb7f39")
                .setStatus(MessageStatus.ERROR)
                .setSign(false)
                .setCreationTime(LocalDateTime.now().minusDays(2));

        OutboxMessage message2 = new OutboxMessage()
                .setContractorId("a0ef4aa9-0769-4c14-946f-214306eb7f39")
                .setStatus(MessageStatus.SUCCESS)
                .setSign(true)
                .setCreationTime(LocalDateTime.now().minusDays(1));

        OutboxMessage message3 = new OutboxMessage()
                .setContractorId("a0ef4aa9-0769-4c14-946f-214306eb7f39")
                .setStatus(MessageStatus.ERROR)
                .setSign(false)
                .setCreationTime(LocalDateTime.now());
        outboxRepository.save(message1);
        outboxRepository.save(message2);
        outboxRepository.save(message3);
    }

    @Test
    void shouldCorrectReturnAllErrorMessages() {
        List<OutboxMessage> errorMessages = outboxRepository.findAllErrorMessages();
        assertEquals(2, errorMessages.size());
        assertTrue(errorMessages.stream().allMatch(message -> message.getStatus() == MessageStatus.ERROR));
    }

    @Test
    void updateMessageStatusShouldBeSuccessfulUpdated() {
        UUID messageId = outboxRepository.findAllErrorMessages().get(0).getId();
        outboxRepository.updateStatus(messageId);
        OutboxMessage updatedMessage = outboxRepository.findById(messageId).orElseThrow();
        assertEquals(MessageStatus.SUCCESS, updatedMessage.getStatus());
    }

    @Test
    void shouldCorrectReturnSuccessMessagesAfterGivenTime() {
        LocalDateTime errorMessageTime = LocalDateTime.now().minusDays(2);
        List<OutboxMessage> successMessages = outboxRepository.findSuccessMessagesAfter(
                "a0ef4aa9-0769-4c14-946f-214306eb7f39", errorMessageTime);
        assertEquals(1, successMessages.size());
        assertTrue(successMessages.stream().allMatch(message -> message.getStatus() == MessageStatus.SUCCESS));
        assertTrue(successMessages.stream().allMatch(message -> message.getCreationTime().isAfter(errorMessageTime)));
    }
}

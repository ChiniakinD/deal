package com.chiniakin.service;

import com.chiniakin.model.rabbit.RabbitTask;
import com.chiniakin.service.interfaces.RabbitMqService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class RabbitMqServiceImpl implements RabbitMqService {

    private final RabbitTemplate rabbitTemplate;

    private final ObjectMapper objectMapper;

    @Override
    public void sendMessage(RabbitTask rabbitTask) {
        String message = createMessage(rabbitTask);
        rabbitTemplate.convertAndSend("deals_contractor_queue", message);
    }

    private String createMessage(RabbitTask rabbitTask) {
        try {
            return objectMapper.writeValueAsString(rabbitTask);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

}

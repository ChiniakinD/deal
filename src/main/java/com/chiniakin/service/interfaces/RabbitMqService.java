package com.chiniakin.service.interfaces;

import com.chiniakin.model.rabbit.RabbitTask;

public interface RabbitMqService {

    void sendMessage(RabbitTask rabbitTask);

}

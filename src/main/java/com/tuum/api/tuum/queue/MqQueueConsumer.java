package com.tuum.api.tuum.queue;

import com.tuum.api.tuum.config.RabbitMQConfig;
import com.tuum.api.tuum.entity.Account;
import com.tuum.api.tuum.entity.Transaction;
import com.tuum.api.tuum.service.MqListenerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import static com.tuum.api.tuum.config.RabbitMQConfig.*;

@Component
public class MqQueueConsumer {

    private static final Logger log = LoggerFactory.getLogger(MqQueueConsumer.class);

    private final MqListenerService listenerService;

    public MqQueueConsumer(MqListenerService listenerService) {
        this.listenerService = listenerService;
    }

//    @RabbitListener(queues = {"${tuum.rabbitmq.queue}"})
    public void receive(@Payload Account account) {
        log.info("ActionLog.receive: start.");

        listenerService.accept(account);

        log.info("ActionLog.receive: end.");
    }

    //    @RabbitListener(queues = {"${tuum.rabbitmq.queue}"})
    public void receiveTransaction(@Payload Account account) {
        log.info("ActionLog.receive: start.");

        listenerService.accept(account);

        log.info("ActionLog.receive: end.");
    }

    @RabbitListener(queues = {RabbitMQConfig.TOPIC_QUEUE_ACCOUNT})
    public void receiveMessageFromTopic1(@Payload Account message) {
        System.out.println("Received topic 1 (" + BINDING_PATTERN_IMPORTANT + ") message: " + message);
    }

    @RabbitListener(queues = {TOPIC_QUEUE_TRANSACTION})
    public void receiveMessageFromTopic2(Transaction message) {
        System.out.println("Received topic 2 (" + BINDING_PATTERN_ERROR + ") message: " + message);
    }
}
package com.tuum.api.tuum.service;

import com.tuum.api.tuum.config.RabbitMQConfig;
import com.tuum.api.tuum.entity.Account;
import com.tuum.api.tuum.entity.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.stereotype.Component;


@Component
public class MqQueueSender {
    private static final Logger LOG = LoggerFactory.getLogger(MqQueueSender.class);

    private static String ROUTING_KEY_USER_IMPORTANT_WARN = "user.important.warn";
    private static String ROUTING_KEY_USER_IMPORTANT_ERROR = "user.important.error";
    private final AmqpTemplate amqpTemplate;

    public MqQueueSender(AmqpTemplate amqpTemplate) {
        this.amqpTemplate = amqpTemplate;
    }


    public void sendAccountToQueue(Account item) {
        LOG.info("ActionLog.sendMessageToQueue: start.");
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME + ".account", ROUTING_KEY_USER_IMPORTANT_WARN, item);
        LOG.info("ActionLog.sendMessageToQueue: end.");
    }


    public void sendTransactionToQueue(Transaction item) {
        LOG.info("ActionLog.sendMessageToQueue: start.");
        amqpTemplate.convertAndSend(RabbitMQConfig.TOPIC_EXCHANGE_NAME + ".transaction", ROUTING_KEY_USER_IMPORTANT_ERROR, item);
        LOG.info("ActionLog.sendMessageToQueue: end.");
    }
}
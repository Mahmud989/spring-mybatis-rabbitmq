package com.tuum.api.tuum.config;

import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Declarables;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {


    public final static String TOPIC_QUEUE_ACCOUNT = "tuum.queue.accont";
    public final static String TOPIC_QUEUE_TRANSACTION = "tuum.queue.transaction";
    public final static String TOPIC_EXCHANGE_NAME = "tuum.exchange";
    public static final String BINDING_PATTERN_IMPORTANT = "*.important.*";
    public static final String BINDING_PATTERN_ERROR = "#.error";

    @Bean
    public MessageConverter jsonMessageConverter() {
        return new Jackson2JsonMessageConverter();
    }

    @Bean
    public Declarables topicBindings() {
        Queue topicQueue1 = new Queue(TOPIC_QUEUE_ACCOUNT, false);

        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME + ".account", false, false);
//        FanoutExchange fanoutExchange = new FanoutExchange(TOPIC_EXCHANGE_NAME, false, false);
//        DirectExchange directExchange = new DirectExchange(TOPIC_EXCHANGE_NAME, false, false);

        return new Declarables(topicQueue1, topicExchange, BindingBuilder
                .bind(topicQueue1)
                .to(topicExchange)
                .with(BINDING_PATTERN_IMPORTANT));
    }

    @Bean
    public Declarables topicBindings2() {
        Queue topicQueue2 = new Queue(TOPIC_QUEUE_TRANSACTION, false);

        TopicExchange topicExchange = new TopicExchange(TOPIC_EXCHANGE_NAME + ".transaction", false, false);
//        DirectExchange directExchange = new DirectExchange(TOPIC_EXCHANGE_NAME, false, false);


        return new Declarables(topicQueue2, topicExchange, BindingBuilder
                .bind(topicQueue2)
                .to(topicExchange)
                .with(BINDING_PATTERN_ERROR));
    }

}
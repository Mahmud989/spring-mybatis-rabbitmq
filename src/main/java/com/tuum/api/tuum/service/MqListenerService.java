package com.tuum.api.tuum.service;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.tuum.api.tuum.entity.Account;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
public class MqListenerService {

    private static final Logger LOG = LoggerFactory.getLogger(MqListenerService.class);

    private final ObjectMapper objectMapper;

    public MqListenerService() {
        this.objectMapper = new ObjectMapper()
                .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)
                .configure(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT, true)
                .enable(DeserializationFeature.ACCEPT_EMPTY_STRING_AS_NULL_OBJECT);
    }

    public void accept(Account acc) {
//        Account account = null;
//        try {
//            account = objectMapper.convertValue(acc, Account.class);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }

        LOG.info("{} | {}", acc, acc.toString());
    }
}

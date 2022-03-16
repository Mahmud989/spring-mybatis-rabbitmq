package com.tuum.api.tuum.controller;


import com.tuum.api.tuum.entity.Account;
import com.tuum.api.tuum.entity.Transaction;
import com.tuum.api.tuum.service.MqQueueSender;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/tuum-rabbitmq/")
public class RabbitMQWebController {

    @Autowired
    MqQueueSender rabbitMQSender;

    @GetMapping(value = "/producer")
    public String producer(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

        Account account = new Account();
        account.setId(empId);
        account.setCustomerId(empName);
        rabbitMQSender.sendAccountToQueue(account);

        return account.toString();
    }

    @GetMapping(value = "/transaction")
    public String producerTransaction(@RequestParam("empName") String empName, @RequestParam("empId") String empId) {

        Transaction account = new Transaction();
        account.setTransactionId(empId);
        account.setDescription(empName);
        rabbitMQSender.sendTransactionToQueue(account);

        return account.toString();
    }

}

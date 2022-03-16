package com.tuum.api.tuum.entity;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {

    private String transactionId;
    private String accountId;
    private String currency;
    private String direction;
    private String description;
    private String balanceAfter;

    //Errors:
    //• Invalid currency
    //• Invalid direction
    //• Invalid amount (if negative amount for example)
    //• Insufficient funds
    //• Account missing
    //• Description missing
}

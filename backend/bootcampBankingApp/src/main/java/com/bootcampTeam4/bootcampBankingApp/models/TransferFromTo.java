package com.bootcampTeam4.bootcampBankingApp.models;

public class TransferFromTo {
    public double amount;
    public String accountNumberFrom;
    public String accountNumberTo;
    public String type;

    public TransferFromTo(double amount, String accountNumberFrom, String accountNumberTo) {
        this.amount = amount;
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
    }

    public TransferFromTo(double amount, String accountNumberTo) {
        this.amount = amount;
        this.accountNumberTo = accountNumberTo;
    }

    public TransferFromTo(String accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public TransferFromTo(double amount, String accountNumberFrom, String accountNumberTo, String type) {
        this.amount = amount;
        this.accountNumberFrom = accountNumberFrom;
        this.accountNumberTo = accountNumberTo;
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getAccountNumberFrom() {
        return accountNumberFrom;
    }

    public void setAccountNumberFrom(String accountNumberFrom) {
        this.accountNumberFrom = accountNumberFrom;
    }

    public String getAccountNumberTo() {
        return accountNumberTo;
    }

    public void setAccountNumberTo(String accountNumberTo) {
        this.accountNumberTo = accountNumberTo;
    }


    public TransferFromTo() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }


}
package com.bootcampTeam4.bootcampBankingApp.models;

public class TransferFromTo {
    public double amount;
    public Long idFrom;
    public Long idTo;

    public TransferFromTo(double amount, Long idFrom, Long idTo) {
        this.amount = amount;
        this.idFrom = idFrom;
        this.idTo = idTo;
    }

    public TransferFromTo() {
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public Long getIdFrom() {
        return idFrom;
    }

    public void setIdFrom(Long idFrom) {
        this.idFrom = idFrom;
    }

    public Long getIdTo() {
        return idTo;
    }

    public void setIdTo(Long idTo) {
        this.idTo = idTo;
    }
}

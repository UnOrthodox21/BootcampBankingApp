package com.bootcampTeam4.bootcampBankingApp.models;

public class TransferFromTo {
    public double amount;
    public String nameFrom;
    public String nameTo;



    public TransferFromTo(double amount, String nameFrom, String nameTo) {
        this.amount = amount;
        this.nameFrom = nameFrom;
        this.nameTo = nameTo;
    }

    public TransferFromTo(String nameFrom) {
        this.nameFrom = nameFrom;
    }


    public String getNameFrom() {
        return nameFrom;
    }

    public void setNameFrom(String nameFrom) {
        this.nameFrom = nameFrom;
    }

    public String getNameTo() {
        return nameTo;
    }

    public void setNameTo(String nameTo) {
        this.nameTo = nameTo;
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
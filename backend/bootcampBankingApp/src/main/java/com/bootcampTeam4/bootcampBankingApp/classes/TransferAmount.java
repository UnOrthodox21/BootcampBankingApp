package com.bootcampTeam4.bootcampBankingApp.classes;

public class TransferAmount {
    public double amount;

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }
    public double changePrice(double priceChange){
        return amount=amount + priceChange;
    }
}

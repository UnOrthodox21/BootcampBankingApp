package com.bootcampTeam4.bootcampBankingApp.models;

import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@Table(name = "transactions")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(name = "type")
    private String type;

    @Column(name = "date")
    @CreationTimestamp
    private LocalDateTime date;


    @Column(name = "user_from")
    private String userFrom;

    @Column(name = "user_to")
    private String userTo;

    @Column(name = "amount")
    private double amount;

    public Transaction() {}

    public Transaction(long id, String type, LocalDateTime date, String userFrom, String userTo, double amount) {
        this.id = id;
        this.type = type;
        this.date = date;
        this.userFrom = userFrom;
        this.userTo = userTo;
        this.amount = amount;
    }


    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public String getUserFrom() {
        return userFrom;
    }

    public void setUserFrom(String userFrom) {
        this.userFrom = userFrom;
    }

    public String getUserTo() {
        return userTo;
    }

    public void setUserTo(String userTo) {
        this.userTo = userTo;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Transaction{" +
                "id=" + id +
                ", type='" + type + '\'' +
                ", date=" + date +
                ", userFrom=" + userFrom +
                ", userTo=" + userTo +
                ", amount=" + amount +
                '}';
    }
}

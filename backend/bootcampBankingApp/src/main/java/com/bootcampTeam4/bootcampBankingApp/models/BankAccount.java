package com.bootcampTeam4.bootcampBankingApp.models;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "bank_accounts")
public class BankAccount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
  
    @Column(name="number", unique = true)
    private String number;

    @Column(name = "type")
    private String type;

    @Column(name="balance")
    private double balance;

    @JoinColumn
    @ManyToOne
    @JsonIgnore // lai response nesatur info par useri
    private UserDetails user;

    public UserDetails getUser() {
        return user;
    }

    public void setUser(UserDetails user) {
        this.user = user;
    }

    public BankAccount() {};

    public BankAccount(long id, String number, String type, double balance) {
        this.id = id;
        this.number = number;
        this.type = type;
        this.balance = balance;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}

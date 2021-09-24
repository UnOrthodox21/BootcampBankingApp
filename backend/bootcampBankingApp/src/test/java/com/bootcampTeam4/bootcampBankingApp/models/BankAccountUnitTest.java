package com.bootcampTeam4.bootcampBankingApp.models;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class BankAccountUnitTest {

    @Test
    public void testConstructorAndGetters(){
        //Given
        BankAccount bankAccount = new BankAccount("bankAccountNumber","bankAccountType",123.56,123456L);
        //When
        String actualNumber = bankAccount.getNumber();
        String actualType = bankAccount.getType();
        double actualBalance = bankAccount.getBalance();
        Long actualId = bankAccount.getUserId();
        //Then
        Assertions.assertEquals("bankAccountNumber", actualNumber);
        Assertions.assertEquals("bankAccountType", actualType);
        Assertions.assertEquals(123.56, actualBalance);
        Assertions.assertEquals(123456L, actualId);
    }
}

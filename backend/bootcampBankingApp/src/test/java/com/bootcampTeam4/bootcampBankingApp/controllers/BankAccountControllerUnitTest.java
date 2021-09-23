package com.bootcampTeam4.bootcampBankingApp.controllers;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.services.BankAccountService;
import com.bootcampTeam4.bootcampBankingApp.services.TransactionService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.*;

public class BankAccountControllerUnitTest {

    @Mock
    private BankAccountService mockBankAccountService;

    @Mock
    private TransactionService mockTransactionService;

    private BankAccountController controller;

    @BeforeEach
    public void setup(){
        openMocks(this);
        controller = new BankAccountController(mockBankAccountService, mockTransactionService);
    }

    @Test
    public void testGetAllBankAccounts(){
        //Given
        List<BankAccount> bankAccountList = new ArrayList<>();
        when(mockBankAccountService.getAllBankAccounts()).thenReturn(bankAccountList);

        //When
        List<BankAccount> actualList = controller.getAllBankAccounts();

        //Then
        verify(mockBankAccountService,times(1)).getAllBankAccounts();
        Assertions.assertSame(bankAccountList,actualList);
    }

    @Test
    public void testGetByAccountNumber(){
        //Given
        BankAccount expectedAccount = new BankAccount();
        when(mockBankAccountService.getBankAccountByAccountNumber("account1")).thenReturn(expectedAccount);

        //When
        BankAccount actual = controller.getBankAccountByAccountNumber("account1");

        //Then
        verify(mockBankAccountService,times(1)).getBankAccountByAccountNumber("account1");
        Assertions.assertSame(expectedAccount,actual);
    }

    @Test
    public void testTransferFundsPositive(){
        //Given
        TransferFromTo transferFromTo = new TransferFromTo();

        when(mockBankAccountService.sendFunds(transferFromTo)).thenReturn(true);

        //When
        controller.sendFunds(transferFromTo);

        //Then
        verify(mockTransactionService, times(1)).addNewTransferTransaction(transferFromTo);
        verify(mockTransactionService, times(0)).addNewFailedTransaction(transferFromTo);
    }

    @Test
    public void testTransferFundsNegative(){
        //Given
        TransferFromTo transferFromTo = new TransferFromTo();

        when(mockBankAccountService.sendFunds(transferFromTo)).thenReturn(false);

        //When
        controller.sendFunds(transferFromTo);

        //Then
        verify(mockTransactionService, times(0)).addNewTransferTransaction(transferFromTo);
        verify(mockTransactionService, times(1)).addNewFailedTransaction(transferFromTo);
    }

    @Test
    public void testCreateNewBankAccount(){
        //Given
        Long userId = 123456L;
        ArgumentCaptor<BankAccount> bankAccountArgumentCaptor = ArgumentCaptor.forClass(BankAccount.class);

        doNothing().when(mockBankAccountService).addNewBankAccount(bankAccountArgumentCaptor.capture()); // because addNewBankAccount returns void (to capture argument)

        //When
        controller.createNewBankAccountForUser(userId);

        //Then
        BankAccount bankAccount = bankAccountArgumentCaptor.getValue();
        //^RKBNK\d+$
        Assertions.assertEquals(123456L, bankAccount.getUserId());
        Assertions.assertEquals("Secondary", bankAccount.getType());
        Assertions.assertEquals(0, bankAccount.getBalance());
        Assertions.assertTrue(bankAccount.getNumber().matches("^RKBNK\\d+$"));
    }



}

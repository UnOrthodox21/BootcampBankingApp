package com.bootcampTeam4.bootcampBankingApp.services;

import com.bootcampTeam4.bootcampBankingApp.models.BankAccount;
import com.bootcampTeam4.bootcampBankingApp.models.TransferFromTo;
import com.bootcampTeam4.bootcampBankingApp.repositories.BankAccountRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.mockito.Mockito.*;

public class BankAccountServiceUnitTests {

    @Mock
    private BankAccountRepository mockRepository;

    private BankAccountService bankAccountService;

    @BeforeEach
    public void setup(){
        MockitoAnnotations.openMocks(this);
        bankAccountService = new BankAccountService(mockRepository,null);
    }

    @Test
    public void testGetBankAccountByIdIfPresent(){
        //Given
        BankAccount bankAccount = new BankAccount();
        Optional<BankAccount> optionalBankAccount = Optional.of(bankAccount);

        when(mockRepository.findBankAccountById(123L)).thenReturn(optionalBankAccount);

        //When
        Optional<BankAccount> actualBankAccountOpt = bankAccountService.getBankAccountById(123L);
        //Then
        Assertions.assertTrue(actualBankAccountOpt.isPresent());
        BankAccount actualBankAccount = actualBankAccountOpt.get();

        Assertions.assertSame(bankAccount, actualBankAccount);
    }

    @Test
    public void testAddNewBankAccount(){
        //Given
        BankAccount bankAccount = new BankAccount();
        //When
        bankAccountService.addNewBankAccount(bankAccount);
        //Then
        verify(mockRepository,times(1)).save(bankAccount);
    }

    @Test
    public void testShouldEditBankAccountBalance(){
        //Given
        BankAccount storedAccount = new BankAccount();
        BankAccount updatedAccount = new BankAccount();
        String accountNumber = "123456";

        storedAccount.setBalance(12.56);
        storedAccount.setType("storedType");
        storedAccount.setNumber("storedNumber");

        updatedAccount.setBalance(9.76);
        updatedAccount.setType("");
        updatedAccount.setNumber("");

        when(bankAccountService.getBankAccountByAccountNumber(accountNumber)).thenReturn(storedAccount);

        //When
        bankAccountService.editBankAccountByAccountNumber(updatedAccount,accountNumber);

        //Then
        verify(mockRepository,times(1)).save(any());

        Assertions.assertEquals(9.76, storedAccount.getBalance());
        Assertions.assertEquals("storedType", storedAccount.getType());
        Assertions.assertEquals("storedNumber", storedAccount.getNumber());
    }

    @Test
    public void testShouldEditBankAccountType(){
        //Given
        BankAccount storedAccount = new BankAccount();
        BankAccount updatedAccount = new BankAccount();
        String accountNumber = "123456";

        storedAccount.setBalance(12.56);
        storedAccount.setType("storedType");
        storedAccount.setNumber("storedNumber");

        updatedAccount.setBalance(0);
        updatedAccount.setType("updatedType");
        updatedAccount.setNumber("");

        when(bankAccountService.getBankAccountByAccountNumber(accountNumber)).thenReturn(storedAccount);

        //When
        bankAccountService.editBankAccountByAccountNumber(updatedAccount,accountNumber);

        //Then
        verify(mockRepository,times(1)).save(any());

        Assertions.assertEquals(12.56, storedAccount.getBalance());
        Assertions.assertEquals("updatedType", storedAccount.getType());
        Assertions.assertEquals("storedNumber", storedAccount.getNumber());
    }

    @Test
    public void testShouldEditBankAccountNumber(){
        //Given
        BankAccount storedAccount = new BankAccount();
        BankAccount updatedAccount = new BankAccount();
        String accountNumber = "123456";

        storedAccount.setBalance(12.56);
        storedAccount.setType("storedType");
        storedAccount.setNumber("storedNumber");

        updatedAccount.setBalance(0);
        updatedAccount.setType("");
        updatedAccount.setNumber("updatedNumber");

        when(bankAccountService.getBankAccountByAccountNumber(accountNumber)).thenReturn(storedAccount);

        //When
        bankAccountService.editBankAccountByAccountNumber(updatedAccount,accountNumber);

        //Then
        verify(mockRepository,times(1)).save(any());

        Assertions.assertEquals(12.56, storedAccount.getBalance());
        Assertions.assertEquals("storedType", storedAccount.getType());
        Assertions.assertEquals("updatedNumber", storedAccount.getNumber());
    }

    @Test
    public void testSendFundsPositive(){
        //Given
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount1.setBalance(223.69);
        bankAccount2.setBalance(333.33);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberFrom("account1");
        transferFromTo.setAccountNumberTo("account2");
        transferFromTo.setAmount(125.53);

        when(mockRepository.getBankAccountByNumber("account1")).thenReturn(bankAccount1);
        when(mockRepository.getBankAccountByNumber("account2")).thenReturn(bankAccount2);

        //When

        boolean result = bankAccountService.sendFunds(transferFromTo);

        //Then
        Assertions.assertTrue(result);

        Assertions.assertEquals(98.16,bankAccount1.getBalance());
        Assertions.assertEquals(458.86,bankAccount2.getBalance());
    }

    @Test
    public void testSendFundsNegative(){
        //Given
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount1.setBalance(223.69);
        bankAccount2.setBalance(333.33);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberFrom("account1");
        transferFromTo.setAccountNumberTo("account2");
        transferFromTo.setAmount(224.00);

        when(mockRepository.getBankAccountByNumber("account1")).thenReturn(bankAccount1);
        when(mockRepository.getBankAccountByNumber("account2")).thenReturn(bankAccount2);

        //When
        boolean result = bankAccountService.sendFunds(transferFromTo);

        //Then
        Assertions.assertFalse(result);

        Assertions.assertEquals(223.69,bankAccount1.getBalance());
        Assertions.assertEquals(333.33,bankAccount2.getBalance());
    }

    @Test
    public void testWithdrawFundsPositive(){
        //Given
        BankAccount bankAccount1 = new BankAccount();

        bankAccount1.setBalance(343.23);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberFrom("account1");
        transferFromTo.setAmount(69.75);

        when(mockRepository.getBankAccountByNumber("account1")).thenReturn(bankAccount1);

        //When
        boolean result = bankAccountService.withdrawFunds(transferFromTo);

        //Then
        Assertions.assertTrue(result);

        Assertions.assertEquals(273.48,bankAccount1.getBalance());
    }

    @Test
    public void testWithdrawFundsNegative(){
        //Given
        BankAccount bankAccount1 = new BankAccount();

        bankAccount1.setBalance(343.23);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberFrom("account1");
        transferFromTo.setAmount(666.66);

        when(mockRepository.getBankAccountByNumber("account1")).thenReturn(bankAccount1);

        //When
        boolean result = bankAccountService.withdrawFunds(transferFromTo);

        //Then
        Assertions.assertFalse(result);

        Assertions.assertEquals(343.23,bankAccount1.getBalance());
    }

    @Test
    public void testDepositFundsPositive(){
        //Given
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount1.setBalance(223.69);
        bankAccount2.setBalance(333.33);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberTo("account2");
        transferFromTo.setAmount(125.53);

        when(mockRepository.getBankAccountByNumber("BANK1337")).thenReturn(bankAccount1);
        when(mockRepository.getBankAccountByNumber("account2")).thenReturn(bankAccount2);

        //When

        boolean result = bankAccountService.depositFunds(transferFromTo);

        //Then
        Assertions.assertTrue(result);

        Assertions.assertEquals(98.16,bankAccount1.getBalance());
        Assertions.assertEquals(458.86,bankAccount2.getBalance());
    }

    @Test
    public void testDepositFundsNegative(){
        //Given
        BankAccount bankAccount1 = new BankAccount();
        BankAccount bankAccount2 = new BankAccount();

        bankAccount1.setBalance(223.69);
        bankAccount2.setBalance(333.33);

        TransferFromTo transferFromTo = new TransferFromTo();
        transferFromTo.setAccountNumberTo("account2");
        transferFromTo.setAmount(224.00);

        when(mockRepository.getBankAccountByNumber("BANK1337")).thenReturn(bankAccount1);
        when(mockRepository.getBankAccountByNumber("account2")).thenReturn(bankAccount2);

        //When
        boolean result = bankAccountService.depositFunds(transferFromTo);

        //Then
        Assertions.assertFalse(result);

        Assertions.assertEquals(223.69,bankAccount1.getBalance());
        Assertions.assertEquals(333.33,bankAccount2.getBalance());
    }


}

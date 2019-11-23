package com.StevanOstojic;

/*This class is designed only to hold the values of the account, and it only has one method to return the number of the
 account, data is encapsulated*/
public class BankAccounts {

    private int accountNumber;
    private String nameOfAccountHolder;
    private double accountBalance;


    BankAccounts() {

    }

    BankAccounts(int accountNumber, String nameOfAccountHolder, double accountBalance) {
        this.accountNumber = accountNumber;
        this.nameOfAccountHolder = nameOfAccountHolder;
        this.accountBalance = accountBalance;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getNameOfAccountHolder() {
        return nameOfAccountHolder;
    }
}



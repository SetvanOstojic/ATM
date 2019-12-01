package com.StevanOstojic;

import java.util.ArrayList;

public class BankOfSonny extends ATM {


    public static void main(String[] args) {

        //The ArrayList holds the data of all account created in the Bank.

        ArrayList<BankAccounts> accountsDataBase = new ArrayList<BankAccounts>();


        System.out.println("\t\tWelcome to  Bank of Sonny");
        //This variable lets the user choose the options in the Main Menu.
        int choice;
        do {
            System.out.println("Thank you for choosing our Bank!");
            System.out.println(" 1. Create an Account. \n 2. Transfer money. \n 3. Listing of accounts.");
            choice = in.nextInt();
            switch (choice) {
                //Choice 1. Lets the user create and account in the bank.
                case 1:
                    System.out.println("Thank you for choosing our Bank! \n Please input the Account Number & your Name");
                    BankAccounts addingAccount = new BankAccounts(in.nextInt(), in.next(), 10.00);
                    // As th name implies, the account is created in the method and then added to the accountDataBase.
                    accountsDataBase = accountCreationMethod(addingAccount, accountsDataBase);
                    break;
                case 2:
                    //Choice 2. lets the user Transfer the money to another account or withdraw the money from the bank.
                    BankAccounts acc = new BankAccounts();
                    System.out.println("Thank you for you'r trust in our Bank. \n Please select to: \n 1.Deposit Money \n 2.Withdraw Money \n 3. Transfer Money.");
                    if (in.nextInt() == 1) {
                        accountsDataBase = depositeMoney(accountsDataBase);
                    } else if (in.nextInt() == 2) {
                        accountsDataBase = withdrawMoney(accountsDataBase);
                    } else if (in.nextInt() == 3)
                        accountsDataBase = transferMoney(accountsDataBase);
                    break;
                case 3:
                    //Choice 3. lets the user see all the account in the bank.
                    System.out.println("The list of all the account in the Bank. Access granted only to Admin's Account. \n Please input the Admin account Name and Password.");
                    AdminAccount admin = new AdminAccount();
                    adminDataInputCheck(admin, accountsDataBase);

                    break;
            }
        } while (in.nextInt() != 0);
    }

}

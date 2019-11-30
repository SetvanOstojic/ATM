package com.StevanOstojic;

import java.io.*;
import java.util.*;

public class ATM {
    public static Scanner in = new Scanner(System.in);

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

    private static void adminDataInputCheck(AdminAccount admin, ArrayList<BankAccounts> accountsDataBase) {
        boolean inputData = true;
        do {
            try {
                do {
                    String adminName = in.next();
                    int adminPin = in.nextInt();
                    if (!adminName.equals(admin.getAdminName()) || adminPin != admin.getAdminPINNumber()) {
                        System.out.println("Admin account name and/or number are not valid input, please try again!");
                    } else if (adminName.equals(admin.getAdminName()) && adminPin == admin.getAdminPINNumber()) {
                        printingListOfTheCurrentAccounts(accountsDataBase);
                        inputData = false;
                    }
                } while (inputData);
            } catch (InputMismatchException ex) {
                System.out.println("Data not correct, please try again!");
                in.nextLine();
            }

        } while (inputData);
    }

    // The account creation method.
    static ArrayList<BankAccounts> accountCreationMethod(BankAccounts singleAccount, ArrayList<BankAccounts> arrayListOfAccounts) {
        if (arrayListOfAccounts.size() > 0) {
            //The loop checks weather there is an account with the same number, in the Bank.
            for (int i = 0; i < arrayListOfAccounts.size(); i++) {
                if (arrayListOfAccounts.get(i).getAccountNumber() == singleAccount.getAccountNumber()) {
                    System.out.println("Please choose a different account number, the account number you'r trying to add already exists. Thank you.");
                    break;
                } else
                    arrayListOfAccounts.add(singleAccount);
                successfulAccoutnCreationMessage();
            }
        } else {
            arrayListOfAccounts.add(singleAccount);
            successfulAccoutnCreationMessage();
        }
        return arrayListOfAccounts;
    }

    // This method is create due too duplicate code.
    static void successfulAccoutnCreationMessage() {
        System.out.println("Yous successfully crated an account in our Bank, thank you for your trust in us.!");
        System.out.println("The starting account balance is 10.00$, this is a gift for you as a sign of gratitude.");
    }

    static ArrayList<BankAccounts> depositeMoney(ArrayList<BankAccounts> accountsArrayList) {
        for (int j = 0; j < accountsArrayList.size(); j++) {
            System.out.println("Please enter the account number!");
            int accountNum = in.nextInt();
            if (accountsArrayList.get(j).getAccountNumber() == accountNum) {
                System.out.println("Please set the amount you would like to deposite.");
                accountsArrayList.get(j).setAccountBalance(in.nextDouble() + accountsArrayList.get(j).getAccountBalance());
                System.out.println("You balance on the account is: " + accountsArrayList.get(j).getAccountBalance());
            }
        }
        return accountsArrayList;
    }

    static ArrayList<BankAccounts> withdrawMoney(ArrayList<BankAccounts> accountsArrayList) {
        for (int k = 0; k < accountsArrayList.size(); k++) {
            System.out.println("Please enter the account number!");
            int accountNum = in.nextInt();
            if (accountsArrayList.get(k).getAccountNumber() == accountNum) {
                System.out.println("Welcome " + accountsArrayList.get(k).getNameOfAccountHolder());
                if (accountsArrayList.get(k).getAccountBalance() > 0) {
                    System.out.println("Please set the amount you would like to Withdraw. \n The current Balance on your account is: " + accountsArrayList.get(k).getAccountBalance());
                    accountsArrayList.get(k).setAccountBalance(in.nextDouble() - accountsArrayList.get(k).getAccountBalance());
                    System.out.println("You balance on the account is: " + accountsArrayList.get(k).getAccountBalance());
                } else
                    System.out.println("You do not have the sufficient amount to withdraw money!");
            }
        }
        return accountsArrayList;
    }


    static ArrayList<BankAccounts> transferMoney(ArrayList<BankAccounts> accountsArrayList) {
        System.out.println("Please enter your account number.");
        int accountThatIsTransfering = in.nextInt();
        for (int m = 0; m < accountsArrayList.size(); m++) {
            if (accountsArrayList.get(m).getAccountNumber() == accountThatIsTransfering) {
                System.out.println("Please enter the amount you would like to transfer. Thank you.");
                double ammountToTransfer = in.nextInt();
                System.out.println("Please enter the number of the account that you'r transfering to.");
                int accountToTransferTo = in.nextInt();
                double reducedBalancefromTheFirstAccount = accountsArrayList.get(m).getAccountBalance() - ammountToTransfer;
                double addedToTheSecondAccount = transferToBalance(accountsArrayList, accountToTransferTo) + ammountToTransfer;
                accountsArrayList.get(m).setAccountBalance(reducedBalancefromTheFirstAccount);
                addToSecondAccountBalance(accountsArrayList, addedToTheSecondAccount, accountToTransferTo);
            }
        }
        return accountsArrayList;

    }

    static void addToSecondAccountBalance(ArrayList<BankAccounts> accountsArrayList, double ammountTransferd, int accountReceving) {
        for (int l = 0; l < accountsArrayList.size(); l++) {
            if (accountsArrayList.get(l).getAccountNumber() == accountReceving) {
                accountsArrayList.get(l).setAccountBalance(ammountTransferd);
            }
        }
    }

    static double transferToBalance(ArrayList<BankAccounts> accountsArrayList, int secondAccount) {
        for (int l = 0; l < accountsArrayList.size(); l++) {
            if (accountsArrayList.get(l).getAccountNumber() == secondAccount) {
                return accountsArrayList.get(l).getAccountBalance();
            }
        }
        return 0;
    }

    static void printingListOfTheCurrentAccounts(ArrayList<BankAccounts> accountsArrayList) {
        for (BankAccounts list : accountsArrayList) {
            System.out.println("Account Number: " + list.getAccountNumber() + " Account owner name: " + list.getNameOfAccountHolder() + " Current Balance: " + list.getAccountBalance());
        }
    }
}

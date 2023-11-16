package com.ilp.service;

import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.SavingsMaxAccount;

public class ManageAccountConfiguration {

	public static void accountManagement(Customer customer) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.println("Enter the customer code:-");
		String customerCode = scanner.nextLine();
		System.out.println(customer.getCustomerName() + " has the following accounts:");
		int i=1;
		for(Account account : customer.getAccountList())
		{
			System.out.println(i + "." + account.getProduct().getProductName());
			i++;
		}
		System.out.print("Enter your choice:-");
		int userChoice = scanner.nextInt();
		Account selectedAccount = customer.getAccountList().get(userChoice-1);
		char goToAccountManagement;
		do
		{
			System.out.println("\n-----Account Management Service-----");
			System.out.println("1.Deposit     2.Withdraw     3.Display");
			System.out.print("Enter your choice:-");
			int serviceChoice = scanner.nextInt();
			switch(serviceChoice) 
			{
			case 1:
				ManageAccountConfiguration.depositMoney(selectedAccount);
				break;
			case 2:
				ManageAccountConfiguration.withdrawMoney(selectedAccount);
				break;
			case 3:
				AccountConfiguration.displayCustomer(customer);
				break;
			}
			System.out.print("Do you want to continue to account management(y/n):");
			goToAccountManagement = scanner.next().charAt(0);
		}
		while(goToAccountManagement == 'y');
	}

	private static void withdrawMoney(Account selectedAccount) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the amount to be withdraw:-");
		double withdrawAmount = scanner.nextDouble();
		if(selectedAccount.getProduct() instanceof SavingsMaxAccount)
		{
			SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount) selectedAccount.getProduct();
			double minBalance = savingsMaxAccount.getMinBalance();
			if(selectedAccount.getAccountBalance() >= withdrawAmount + minBalance)
			{
				selectedAccount.setAccountBalance(selectedAccount.getAccountBalance()-withdrawAmount);
			}
			else
			{
				System.out.println("This amount can't be withdraw...!\nMinimum balance must be " + minBalance +".");
	
			}
		}
		else
		{
			if(selectedAccount.getAccountBalance() >= withdrawAmount)
			{
				selectedAccount.setAccountBalance(selectedAccount.getAccountBalance() - withdrawAmount);
			}
			else
			{
				System.out.println("Insufficient Balance...!");
			}
		}
	}

	private static void depositMoney(Account selectedAccount) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter the amount to be deposited:-");
		double depositAmount = scanner.nextDouble();
		if(selectedAccount.getProduct() instanceof LoanAccount)
		{
			System.out.println("Which way you want to deposit money?");
			System.out.println("1.Over the Counter Deposit \n2.Cheque Deposit");
			int depositChoice = scanner.nextInt();
			if(depositChoice == 1)
			{
				double updatedBalance = selectedAccount.getAccountBalance() + depositAmount;
				selectedAccount.setAccountBalance(updatedBalance);	
			}
			else if(depositChoice == 2)
			{
				LoanAccount loanAccount = (LoanAccount)selectedAccount.getProduct();
				double chequeDepositRate = loanAccount.getChequeDepositRate();
				depositAmount -= depositAmount * chequeDepositRate;
				double updatedBalance = selectedAccount.getAccountBalance() + depositAmount;
				selectedAccount.setAccountBalance(updatedBalance);	
			}
		}
		else 
		{
			double updateBalance = selectedAccount.getAccountBalance() + depositAmount;
			selectedAccount.setAccountBalance(updateBalance);
		}
		
	}

}

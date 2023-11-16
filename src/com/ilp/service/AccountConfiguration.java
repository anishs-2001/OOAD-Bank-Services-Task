package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class AccountConfiguration {

	public static ArrayList<Account> createAccount(ArrayList<Product> productList, ArrayList<Account> accountList) {
		
		Product product = null;
		Scanner scanner = new Scanner(System.in);
		char accountChoice;
		do {

			System.out.println("\n--------Accounts Available--------");
			int i = 1;
			for (Product productElement : productList) {
				System.out.println(i + "." + productElement.getProductName());
				i++;
			}
			System.out.print("Enter your choice:-");
			int productId = scanner.nextInt();
			scanner.nextLine();
			product = productList.get(productId - 1);
			if(product instanceof SavingsMaxAccount)
			{
				SavingsMaxAccount savingsMaxAccount = (SavingsMaxAccount)product;
				System.out.print("Enter account number:-");
				String accountNo = scanner.nextLine();
				System.out.print("Enter the opening balance to be deposited:-");
				double accountBalance = scanner.nextDouble();
				scanner.nextLine();
				while(accountBalance < savingsMaxAccount.getMinBalance())
				{
					System.out.print("Error: Minimum balance of 1000 is required! : add " + (savingsMaxAccount.getMinBalance()-accountBalance));
					accountBalance += scanner.nextDouble();
				}
				Account account = new Account(accountNo, accountBalance, savingsMaxAccount);
				accountList.add(account);
			}
			else
			{
				System.out.print("Enter account number:-");
				String accountNo = scanner.nextLine();
				System.out.print("Enter the opening balance to be deposited:-");
				double accountBalance = scanner.nextDouble();
				scanner.nextLine();
				Account account = new Account(accountNo, accountBalance, product);
				accountList.add(account);
			}
			System.out.print("Do you want to add more account(y/n):");
			accountChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (accountChoice == 'y');
		return accountList;
	}
	
	public static Customer createCustomer(Customer customer, ArrayList<Product> productList, ArrayList<Account> accountList) {
		
		Scanner scanner = new Scanner(System.in);
		System.out.print("Enter customer code:-");
		String customerCode = scanner.nextLine();
		if(customer == null)
		{
			System.out.println("Customer Id not available. Create a new Account");
		}
		accountList = createAccount(productList,accountList);
		System.out.print("Enter customer name:-");
		String customerName = scanner.nextLine();
		return new Customer(customerCode, customerName, accountList);
	}


	public static void displayCustomer(Customer customer) {
		System.out.println("\n****************************Customer-Account Details****************************");
		System.out.println("CustomerId" + "		" + "CustomerName" + "		" + "AccountType" + "		" + "Balance");
		System.out.println("********************************************************************************");
		for (Account account : customer.getAccountList()) {
			Product product = account.getProduct();
			System.out.println(customer.getCustomerCode() + "			" + customer.getCustomerName() + "			"
					+ product.getProductName() + "		" + account.getAccountBalance());

		}

	}

	public static void getTransactionCost(Customer customer) {

		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		Product userProductChoice = null;
		double transactionCost;
		System.out.println("\n*************Transaction Rate*************");
		System.out.println("--------Available Products-------");
		int i = 1;
		for (Account account : customer.getAccountList()) {
			Product product = account.getProduct();
			System.out.println(i + "." + product.getProductName());
			productList.add(product);
			i++;
		}
		System.out.println("Choose the Product Account for which trasaction is to be calculated:-");
		int userChoice = scanner.nextInt();
		userProductChoice = productList.get(userChoice - 1);
		System.out.println("\n--------Available Services-------");
		int j = 1;
		for (Service service : userProductChoice.getServiceList()) {
			System.out.println(j + "." + service.getServiceName());
			serviceList.add(service);
			j++;
		}
		System.out.println("Choose the Service for which trasaction is to be calculated:-");
		userChoice = scanner.nextInt();
		scanner.nextLine();
		double serviceRate = (serviceList.get(userChoice - 1)).getServiceRate();
		System.out.println("Enter the number of transaction:-");
		int transactionNo = scanner.nextInt();
		transactionCost = transactionNo * serviceRate;
		System.out.println("Transaction Cost:- " + transactionCost);
	}

}

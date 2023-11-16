package com.ilp.utility;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.Account;
import com.ilp.entity.Customer;
import com.ilp.entity.Product;
import com.ilp.entity.Service;
import com.ilp.service.AccountConfiguration;
import com.ilp.service.ManageAccountConfiguration;
import com.ilp.service.ProductConfiguration;

public class AccountUtility {

	public static void main(String[] args) {

		Scanner scanner = new Scanner(System.in);
		char goToMenu;
		char goToMainMenu;
		ArrayList<Service> serviceList = new ArrayList<Service>();
		ArrayList<Product> productList = new ArrayList<Product>();
		ArrayList<Account> accountList = new ArrayList<Account>();
		Customer customer = null;
		
		do {
			System.out.println("\n--------Menu Creation--------");
			System.out.println("1.Create Service \n2.Display Service \n3.Create Product \n4.Display product \n5.Exit");
			System.out.print("Enter your choice:");
			int menuChoice = scanner.nextInt();
			switch(menuChoice) {
			case 1:
				serviceList = ProductConfiguration.createService(serviceList);
				break;
			case 2:
				ProductConfiguration.displayService(serviceList);
				break;
			case 3:
				productList = ProductConfiguration.createProduct(serviceList);
				break;
			case 4:
				ProductConfiguration.displayproduct(productList);
				break;
			case 5:
				System.exit(0);
				break;
			}
			System.out.print("Do you want to go back to menu creation(y/n):");
			goToMenu = scanner.next().charAt(0);
		}
		while (goToMenu == 'y');
		
		do {
			System.out.println("\n********Welcome To Bank********");
			System.out.println("1.Create Customer \n2.Manage Account \n3.Display Customer \n4.TransactionBill \n5.Exit");
			System.out.print("Enter your choice:");
			int userMenuChoice = scanner.nextInt();
			switch(userMenuChoice) {
			case 1:
				customer = AccountConfiguration.createCustomer(customer, productList, accountList);
				for (Account account : customer.getAccountList()) {
					System.out.println("\n" + account.getProduct().getProductName()+ " created for "
							+ customer.getCustomerName() + " with the following services:");
					for (Service service : account.getProduct().getServiceList()) {
						System.out.println(service.getServiceName());
					}
					System.out.println("\nAccount is active.!!!!!!");
				}
				break;
			case 2:
				ManageAccountConfiguration.accountManagement(customer);
				break;
			case 3:
				AccountConfiguration.displayCustomer(customer);
				break;
			case 4:
				AccountConfiguration.getTransactionCost(customer);
				break;
			case 5:
				System.exit(0);
				break;
			}
			System.out.print("Do you want to go back to main menu(y/n):");
			goToMainMenu = scanner.next().charAt(0);
		}
		while (goToMainMenu == 'y');
	}

}

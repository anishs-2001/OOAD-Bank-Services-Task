package com.ilp.service;

import java.util.ArrayList;
import java.util.Scanner;

import com.ilp.entity.CurrentAccount;
import com.ilp.entity.LoanAccount;
import com.ilp.entity.Product;
import com.ilp.entity.SavingsMaxAccount;
import com.ilp.entity.Service;

public class ProductConfiguration {

	public static ArrayList<Service> createService(ArrayList<Service> serviceList) {

		Scanner scanner = new Scanner(System.in);
		char continueChoice;
		do {
			System.out.print("Enter the service code:-");
			String serviceCode = scanner.nextLine();
			System.out.print("Enter the service name:-");
			String serviceName = scanner.nextLine();
			System.out.print("Enter the service rate:-");
			double serviceRate = scanner.nextDouble();
			Service service = new Service(serviceCode, serviceName, serviceRate);
			serviceList.add(service);
			System.out.print("Do you want to add more service(y/n):");
			continueChoice = scanner.next().charAt(0);
			scanner.nextLine();
		} while (continueChoice == 'y');
		return serviceList;
	}

	public static void displayService(ArrayList<Service> serviceList) {

		System.out.println("\n-----Services Available-----");
		int i = 1;
		for (Service service : serviceList) {
			System.out.println(i + "." + service.getServiceName());
			i++;
		}

	}

	public static ArrayList<Product> createProduct(ArrayList<Service> serviceList) {
		
		ArrayList<Product> productList = new ArrayList<Product>();
		char productChoice;
		Scanner scanner = new Scanner(System.in);
		do {
			System.out.println("\n----------Products----------");
			System.out.println("1.Savings Max Account \n2.Current Account \n3.Loan Account");
			System.out.print("Select the product to be created:-");
			int userProductChoice = scanner.nextInt();
			switch(userProductChoice) {
			case 1: 
				productList.add(createSavingsMaxAccount(serviceList));
				break;
			case 2:
				productList.add(createCurrentAccount(serviceList));
				break;
			case 3:
				productList.add(createLoanAccount(serviceList));
				break;
			}
			System.out.print("Do you want to add more product(y/n):");
			productChoice = scanner.next().charAt(0);
		}
		while(productChoice == 'y');
		return productList;
	}
	
	private static Product createLoanAccount(ArrayList<Service> serviceList) {
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		System.out.print("Enter the product code:-");
		String productCode = scanner.nextLine();
		System.out.print("Enter the product name:-");
		String productName= scanner.nextLine();
		System.out.println("\n-------Available Services-------");
		int i =1;
		for(Service service : serviceList) {
			System.out.println(i + "." + service.getServiceName());
			i++;
		}
		char serviceChoice;
		do {
			System.out.print("Choose the service you want to add:-");
			int userServiceChoice = scanner.nextInt();
			productServiceList.add(serviceList.get(userServiceChoice-1));
			System.out.print("Do you want to add more service(y/n):");
			serviceChoice = scanner.next().charAt(0);
			}
		while(serviceChoice == 'y');
		Product product = new LoanAccount(productCode, productName, productServiceList);
		return product;
		}
	
	private static Product createCurrentAccount(ArrayList<Service> serviceList) {
			
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		System.out.print("Enter the product code:-");
		String productCode = scanner.nextLine();
		System.out.print("Enter the product name:-");
		String productName= scanner.nextLine();
		System.out.println("\n-------Available Services-------");
		int i =1;
		for(Service service : serviceList) {
			System.out.println(i + "." + service.getServiceName());
			i++;
		}
		char serviceChoice;
		do {
			System.out.print("Choose the service you want to add:-");
			int userServiceChoice = scanner.nextInt();
			productServiceList.add(serviceList.get(userServiceChoice-1));
			System.out.print("Do you want to add more service(y/n):");
			serviceChoice = scanner.next().charAt(0);
			}
		while(serviceChoice == 'y');
		Product product = new CurrentAccount(productCode, productName, productServiceList);
		return product;
		}
	
	
	private static Product createSavingsMaxAccount(ArrayList<Service> serviceList) {
		
		Scanner scanner = new Scanner(System.in);
		ArrayList<Service> productServiceList = new ArrayList<Service>();
		System.out.print("Enter the product code:-");
		String productCode = scanner.nextLine();
		System.out.print("Enter the product name:-");
		String productName= scanner.nextLine();
		System.out.println("\n-------Available Services-------");
		int i =1;
		for(Service service : serviceList) {
			System.out.println(i + "." + service.getServiceName());
			i++;
		}
		char serviceChoice;
		do {
			System.out.print("Choose the service you want to add:-");
			int userServiceChoice = scanner.nextInt();
			productServiceList.add(serviceList.get(userServiceChoice-1));
			System.out.print("Do you want to add more service(y/n):");
			serviceChoice = scanner.next().charAt(0);
			}
		while(serviceChoice == 'y');
		Product product = new SavingsMaxAccount(productCode, productName, productServiceList);
		return product;
		}
	

	public static void displayproduct(ArrayList<Product> productList) {
		
		System.out.println("\n-----Available Products-----");
		int i= 1;
		for(Product product : productList) {
			System.out.println(i + "." +product.getProductName());
			System.out.println(product.getProductName() + " services");
			int j = 1;
			for(Service service : product.getServiceList() ) {
				System.out.println(j + "." + service.getServiceName());
				j++;
			}
			i++;
			System.out.print("\n");
		}
		
	}
}

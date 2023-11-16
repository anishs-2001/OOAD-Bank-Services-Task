package com.ilp.entity;

import java.util.ArrayList;

public class LoanAccount extends Product {

	private double chequeDepositRate = 0.03;

	public LoanAccount(String productCode, String productName, ArrayList<Service> serviceList) {
		super(productCode, productName, serviceList);
	}

	public double getChequeDepositRate() {
		return chequeDepositRate;
	}

	public void setChequeDepositRate(double chequeDepositRate) {
		this.chequeDepositRate = chequeDepositRate;
	}

}
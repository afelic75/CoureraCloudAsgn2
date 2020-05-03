package Accounts;

import java.util.Date;

public class CheckingAccount extends BankAccount{
	CheckingAccount() {
		super();
	}
	static final double INTEREST_RATE = .0001; 
	
	// constructor
	public CheckingAccount (double openingBalance) {
		super(openingBalance, INTEREST_RATE);
	}
	
	public CheckingAccount (BankAccount bankAccount) {
		super(bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getOpenedOn(), bankAccount.getAccountNumber());
	}
	
	public CheckingAccount(double balance, double interestRate, Date openedOn, long accountNumber) {
		super(balance, interestRate, openedOn, accountNumber);
	}
	
	
}
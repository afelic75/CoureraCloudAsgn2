package Accounts;



import java.util.Date;



public class SavingsAccount extends BankAccount{
	
	static final double INTEREST_RATE =.01;
	
	//constructor
	public SavingsAccount (double openingBalance) {
		super(openingBalance, INTEREST_RATE);
	}
	
	public SavingsAccount (BankAccount bankAccount) {
		super(bankAccount.getBalance(), bankAccount.getInterestRate(), bankAccount.getOpenedOn(), bankAccount.getAccountNumber());
	}
	
	public SavingsAccount(double balance, double interestRate, Date openedOn, long accountNumber) {
		super(balance, interestRate, openedOn, accountNumber);
	}
	

}

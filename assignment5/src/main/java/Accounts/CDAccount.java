package Accounts;


import java.util.Date;

import com.meritamerica.assignment5.CDOffering;
import com.meritamerica.assignment5.MeritBank;



public class CDAccount extends BankAccount {
	
	private int term;
	private Date startDate;
	
	public CDAccount(double balance, double interestRate, int term) {
		super(balance, interestRate, new Date(), MeritBank.getNextAccountNumber());
		this.term = term;
	}
	
	public CDAccount(double balance, double interestRate, int term, long accountNumber) {
		super(balance, interestRate, new Date(), accountNumber);
		this.term = term;
	}
	
	public CDAccount(double balance, double interestRate, Date date, long accountNumber, int term) {
		super(balance, interestRate, date, accountNumber);
		this.term = term;
	}
	
	public CDAccount(CDOffering offering, double balance) {
		super(balance, offering.getInterestRate(), new Date(), MeritBank.getNextAccountNumber());
		this.term = offering.getTerm();
	}
	
	public CDAccount(CDOffering offering, double balance, long accountNumber, Date date) {
		super(balance, offering.getInterestRate(), date, accountNumber);
		this.term = offering.getTerm();
	}
	
	public int getTerm() {
		return this.term;
	}
	
	public Date getStartDate() {
		return this.startDate;
	}
	
	
	@Override
	public boolean withdraw(double amount) { 
		
		
		return false;
	}
	
	
	@Override
	public boolean deposit(double amount) { 
			
		return false;
		
	}
	

}
package com.meritamerica.assignment5;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.NotBlank;
import org.hibernate.validator.constraints.NotEmpty;

import Accounts.CDAccount;
import Accounts.CheckingAccount;
import Accounts.SavingsAccount;
import Exceptions.ExceedsCombinedBalanceLimitException;

public class AccountHolder implements Comparable <AccountHolder>{
	
	private String firstName; // user's name info
	private String middleName;
	private String lastName;
	@NotBlank 
	@NotEmpty
	private String ssn; // used as customer's unique identifier
	private int id;
	
	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	private List <CheckingAccount> checkingAccounts;
	private List <SavingsAccount> savingsAccounts;
	private List <CDAccount> cdAccounts;
	
	
	AccountHolder() { 
		this.checkingAccounts = new ArrayList <>();
		this.savingsAccounts = new ArrayList <>();
		this.cdAccounts = new ArrayList <>();
		this.id = MeritBank.getNextAID();
		MeritBank.setNextAID(this.id + 1);
		
		
	}
	
	
	// constructs acccountHolder with no accounts
	AccountHolder(String firstName, String middleName, String lastName, String ssn) {
		this.firstName = firstName;
		this.middleName = middleName;
		this.lastName = lastName;
		this.ssn = ssn;
		this.checkingAccounts = new ArrayList <>();
		this.savingsAccounts = new ArrayList <>();
		this.cdAccounts = new ArrayList <>();
	}
	
	
	public CheckingAccount addCheckingAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		CheckingAccount tempAccount = new CheckingAccount(openingBalance);
		return addCheckingAccount(tempAccount);
	}
	
	
	public CheckingAccount addCheckingAccount(CheckingAccount checkingAccount) throws ExceedsCombinedBalanceLimitException {
		if(getCheckingBalance() + getSavingsBalance() + checkingAccount.getBalance() >= 250000) 
		{
			throw new ExceedsCombinedBalanceLimitException();
		
		}
			checkingAccounts.add(checkingAccount);
			return checkingAccount;
	}
		
		
	public SavingsAccount addSavingsAccount(double openingBalance) throws ExceedsCombinedBalanceLimitException {
		SavingsAccount tempAccount = new SavingsAccount(openingBalance);
		return addSavingsAccount(tempAccount);
	}
	
	/**
	 * Associated a savings account with the AccountHolder that
	 * calls this method.
	 *  
	 * @param savingsAccount A SavingsAccount to attach to the accountHolder
	 */
	public SavingsAccount addSavingsAccount(SavingsAccount savingsAccount) throws ExceedsCombinedBalanceLimitException {
		if(getCheckingBalance() + getSavingsBalance() + savingsAccount.getBalance()>= 250000) {
			throw new ExceedsCombinedBalanceLimitException();
			//System.out.println("Unable to create new account, balance too high.");
			//return null;
		}
		
		savingsAccounts.add(savingsAccount);
		return savingsAccount;
	}
	
	/**
	 * Create a new CD Account associated with the AccountHolder that
	 * calls this method.
	 *  
	 * @param offering A CDOffering that defines the interest and term
	 * @param openingBalance A double of how much money the account is opened with
	 */
	public CDAccount addCDAccount(CDOffering offering, double openingBalance) {
		if(offering == null) {
			System.out.println("Unable to find a CD offer.");
			return null;
		}
		CDAccount tempAccount = new CDAccount(openingBalance, offering.getInterestRate(), offering.getTerm());
		return addCDAccount(tempAccount);
	}
	
	/**
	 * Associated a CD account with the AccountHolder that
	 * calls this method.
	 *  
	 * @param cdAccount A SavingsAccount to attach to the accountHolder
	 */
	public CDAccount addCDAccount(CDAccount cdAccount) {
		if(cdAccount == null) {
			System.out.println("Unable to find a CD offer.");
			return null;
		}
		
		cdAccounts.add(cdAccount);
		return cdAccount;
	}
	//end of account creation code
	
	
	
	//begin getters and setters
	public String getFirstName() {
		return this.firstName;
	}
	public void setFirstName(String string) {
		this.firstName = string;
	}
	
	public String getMiddleName() {
		return this.middleName;
	}
	public void setMiddleName(String string) {
		this.middleName = string;
	}
	
	public String getLastName() {
		return this.lastName;
	}
	public void setLastName(String string) {
		this.lastName = string;
	}
	
	public String getSSN() {
		return this.ssn;
	}
	public void setSSN(String string) {
		this.ssn = string;
	}
	
	public List<CheckingAccount> getCheckingAccounts() {
		return this.checkingAccounts;
	}
	
	public List<SavingsAccount> getSavingsAccounts() {
		return this.savingsAccounts;
	}
	public List<CDAccount> getCDAccounts() {
		return this.cdAccounts;
	}

	
	
	public double getCheckingBalance() {
		double sum = 0;
		for(CheckingAccount c: checkingAccounts) {
			sum += c.getBalance();
		}
		return sum;
	}
	
	
	public double getSavingsBalance() {
		double sum = 0;
		for(SavingsAccount s: savingsAccounts) {
			sum += s.getBalance();
		}
		return sum;
	}
	
	public double getCDAccountBalance() {
		double sum = 0;
		for(CDAccount cd: cdAccounts) {
			sum += cd.getBalance();
		}
		return sum;
	}
	
	
	public double getCombinedBalance() {
		double sum = getSavingsBalance();
		sum += getCheckingBalance();
		sum += getCDAccountBalance();
		return sum;
	}

	
	@Override
	public int compareTo(AccountHolder other) {
		int mySum = (int) getCombinedBalance();
		int otherSum = (int) other.getCombinedBalance();
		return mySum - otherSum;
	}
	

}

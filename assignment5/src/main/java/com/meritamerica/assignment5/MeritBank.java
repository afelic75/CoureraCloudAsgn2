package com.meritamerica.assignment5;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Accounts.BankAccount;
import Accounts.CDAccount;
import Accounts.CheckingAccount;
import Accounts.SavingsAccount;
import Exceptions.NotFoundException;



public class MeritBank {
	private static List <AccountHolder> accountHolders = new ArrayList<>();
	private static CDOffering[] cdOfferings = new CDOffering[0];
	private static long nextAccountNumber = 1234567;
	private static int nextAID = 1;
	
	public static AccountHolder getAH (int id) throws NotFoundException{
		for (AccountHolder accountHolder: accountHolders) {
			if (accountHolder.getId()==id) {
				return accountHolder;
			}
		
		}
		throw new NotFoundException();
	}
	

	public static void setNextAID(int na) {
		nextAID = na;
	}

	public static void addAccountHolder(AccountHolder x) {
		accountHolders.add(x);
		
		
	}
	
	// begin getters
	public static List<AccountHolder> getAccountHolders() {
		return accountHolders;
	}
	
	public static CDOffering[] getCDOfferings() {
		return cdOfferings;
	}
	
	

	public static BankAccount getBankAccount(long id) {
		for (AccountHolder ac: accountHolders) {
			for (CheckingAccount ca: ac.getCheckingAccounts()) {
				if(ca.getAccountNumber()== id) {
					return ca;
				}
				
			}
			for(SavingsAccount sa: ac.getSavingsAccounts()) {
				if(sa.getAccountNumber()== id) {
					return sa;
				}
			}
			for(CDAccount cda: ac.getCDAccounts()) {
				if(cda.getAccountNumber()== id) {
					return cda;
				}
			}
				
		}
		return null;
	}
			

	static CDOffering getBestCDOffering(double depositAmmount) {
		if(cdOfferings == null) {return null;}
		double bestValue = 0;
		int bestIndex = -1;
		for(int i=0; i<cdOfferings.length; i++) {
			if(cdOfferings[i].getInterestRate() > bestValue) {
				bestValue = cdOfferings[i].getInterestRate();
				bestIndex = i;
			}
		}
		
		return cdOfferings[bestIndex];
	}
	
	
	public static CDOffering getSecondBestCDOffering(double depositAmount) {
		if(cdOfferings == null) {return null;}
		CDOffering best = getBestCDOffering(depositAmount);
		
		double secondBestValue = 0;
		int secondBestIndex = -1;
		for(int i=0; i<cdOfferings.length; i++) {
			if(cdOfferings[i].getInterestRate() > secondBestValue 
					&& !best.equals(cdOfferings[i])) {
				secondBestValue = cdOfferings[i].getInterestRate();
				secondBestIndex = i;
			}
		}
		if(secondBestIndex == -1) { return null; }
		return cdOfferings[secondBestIndex];
	}
	
	public static void clearCDOfferings() {
		cdOfferings = null;
	}
	
	
	public static void setCDOfferings(CDOffering[] offerings) {
		//determine the size to make the offerings array
		int arraySize = 0;
		for(int i=0; i<offerings.length; i++) {
			if(offerings[i] == null) {
				break;
			}
			arraySize ++;
		}
		
		cdOfferings = new CDOffering[arraySize];
		for(int i=0; i<arraySize; i++) {
			cdOfferings[i] = offerings[i];
		}
	}
	
	public static long getNextAccountNumber() {
		return nextAccountNumber;
	}

	public static void setNextAccountNumber(long accountNumber) {
		nextAccountNumber = accountNumber;
	}
	
	
	static double totalBalances() {
		double sum = 0;
		for(AccountHolder ah : accountHolders) {
			if(ah == null) {break;}
			
			for(CheckingAccount account: ah.getCheckingAccounts()) {
					sum += account.getBalance();
					
				
			}
			for(SavingsAccount account: ah.getSavingsAccounts()) {
					sum += account.getBalance();
			}
			for(CDAccount account: ah.getCDAccounts()) {
					sum += account.getBalance();
				
			}
		}
		
		
		return sum;
	}

	
	static List<AccountHolder> sortAccountHolders() {
		Collections.sort(accountHolders);
		return accountHolders;
	}

	public static int getNextAID() {
		return nextAID;
	}

}

package Accounts;

import java.util.Date;

import com.meritamerica.assignment5.MeritBank;

public abstract class BankAccount {
		
		private double balance;
		private double interestRate;
		private long accountNumber;
		private Date accountOpenedOn;
		
		BankAccount() {
			this.accountOpenedOn = new Date();
		}
		
		
		BankAccount(double balance, double interestRate){
			this.balance = balance;
			this.interestRate = interestRate;
			this.accountOpenedOn = new Date();
			this.accountNumber = MeritBank.getNextAccountNumber();
			
		}
		
		
		BankAccount(double balance, double interestRate, Date accountOpenedOn, long accountNumber){
			this.balance = balance;
			this.interestRate = interestRate;
			this.accountOpenedOn = accountOpenedOn;
			this.accountNumber = accountNumber;
			
		}
		
		/**
		 * Withdraw funds from the account
		 * Rejects negative numbers and overdrafts
		 * 
		 * @param amount double, the amount to withdraw
		 * @return true if the transaction took place
		 */
		public boolean withdraw(double amount) { 
			// throws NegativeAmountException, 
				//ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
				if (amount > this.balance) {
					return false;
					//throw new ExceedsAvailableBalanceException();
				}
				if (amount < 0) {
					return false;
					//throw new NegativeAmountException();
				}
				if(amount > 1000) {
					return false;
					//MeritBank.getFraudQueue().addTransaction(t);
					//throw new ExceedsFraudSuspicionLimitException();
				}
				this.balance = this.balance - amount; 
				return true; 

		}
		
		/**
		 * Add funds to the account
		 * Rejects negative numbers
		 * 
		 * @param amount double, the amount to deposit
		 * @returns true if the transaction took place
		 */
		public boolean deposit(double amount)  { 
				// throws NegativeAmountException, 
				//ExceedsAvailableBalanceException, ExceedsFraudSuspicionLimitException {
			if (amount < 0) {
				//throw new NegativeAmountException();
				return false;
			}
			this.balance = this.balance + amount;
			return true;
		}
		
		/**
		 * Calculates the total value that will be in the account after interest
		 * as accrued for a number of years
		 * 
		 * due to concerns with the Math.pow bug, it is avoided in this method
		 * 
		 * @param years int, the time the account collects interest for
		 * @return double, the projected total value of the account 
		 */
		public double futureValue(int years) {
			if(years == 0) { return this.balance; } 
			return (futureValue(years - 1) * (1 + this.interestRate));
		}
			
			
		
		
		
		
	
		
		
		
		
		public long getAccountNumber() {
			return this.accountNumber;
		}
		
		public Date getAccountOpenedOn() {
			return accountOpenedOn;
		}


		public void setAccountOpenedOn(Date accountOpenedOn) {
			this.accountOpenedOn = accountOpenedOn;
		}


		public void setBalance(double balance) {
			this.balance = balance;
		}


		public void setInterestRate(double interestRate) {
			this.interestRate = interestRate;
		}


		public void setAccountNumber(long accountNumber) {
			this.accountNumber = accountNumber;
		}


		public double getBalance() {
			return this.balance;
		}
		
		public double getInterestRate() {
			return this.interestRate;
		}
		
		public Date getOpenedOn() {
			return this.accountOpenedOn;
		}
		

}

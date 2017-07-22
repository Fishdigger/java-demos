package edu.mga.itec.loans;

public class LoanCalculator {
	private double loanAmount;
	private double interestRate;
	private int numYears;
	
	public LoanCalculator() {}
	
	public LoanCalculator(double loanAmount, double interestRate, int numYears) {
		this.loanAmount = loanAmount;
		this.interestRate = interestRate;
		this.numYears = numYears;
	}
	
	public double getLoanAmount() {
		return this.loanAmount;
	}
	public void setLoanAmount(double amount) {
		this.loanAmount = amount;
	}
	
	public double getInterestRate(){
		return this.interestRate;
	}
	public void setInterestRate(double rate) {
		this.interestRate = rate;
	}
	
	public int getNumYears() {
		return this.numYears;
	}
	public void setNumYears(int numYears) {
		this.numYears = numYears;
	}
	
	public double getMonthlyPayment(){
		double monthlyInterest = this.interestRate / 1200;
		double monthlyPayment = this.loanAmount * monthlyInterest / ( 1- 1 /Math.pow(1 + monthlyInterest, this.numYears * 12));
		return monthlyPayment;
	}
	
	public double getTotalPayment(){
		return getMonthlyPayment() * this.numYears * 12;
	}
}

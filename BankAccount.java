import java.util.Random;
public class BankAccount {

	// Instance Variables
	
	private static int lastAccountNumber = 1000;
	
	private int accountNumber;
	private String history;
	private double balance;
	Random rand = new Random();
	
	// Constructors

	public BankAccount() {
		balance = 0;
		history = "Account created with Balance: $0" + "\r\n";
		accountNumber = rand.nextInt(999999);
	}
	
	public BankAccount(double amount) {
		balance = amount;
		history = "Account created with Balance: $" + amount + "\r\n";
		accountNumber = rand.nextInt(999999);		
	}
	
	// Methods
	
	public int getAccountNumber() {
		return accountNumber;
	}
	
	public String getHistory() {
		return history;
	}
	
	public String getBalance() {
		return String.valueOf(balance);
	}
	
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance+=amount;
			history += "Deposit: $" + amount + " ($" + balance + ")\r\n";
			return true;
		}
		history += "Deposit failed: $" + amount + "\r\n";
		return false;
	}
	
	public void reset() {
		balance = 0;
		accountNumber = rand.nextInt(999999);
		history = "";
	}
	
	public boolean withdraw(double amount) {
		if (amount <= balance && amount > 0) {
			balance-=amount;
			history += "Withdrawal: $" + amount + " ($" + balance + ")\r\n";
			return true;
		}
		history += "Withdrawal failed: $" + amount + "\r\n";
		return false;
	}	
	
}

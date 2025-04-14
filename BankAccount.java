import java.util.Random;
public class BankAccount {

	// Instance Variables
	
	private static int lastAccountNumber = 1000;
	
	private int accountNumber;
	//not going to lie, i see what you are doing but history would work better as a string list.
	//then having a function that displays the list of strings.
	private String history;
	private double balance;
	//not sure about java, but you usually need to seed the random function cause random isnt random.
	Random rand = new Random();
	
	// Constructors

	public BankAccount() {
		balance = 0;
		history = "Account created with Balance: $0" + "\r\n";
		//tip: there's a possibility for making duplicates doing this.
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
	// you dont need to return anything if you aren't using it.
	//tip: you could return the current balance instead and display that if you were dead set on returning information. generally though you' have a function for this as to not repeat code.
	public boolean deposit(double amount) {
		if (amount > 0) {
			balance+=amount;
			history += "Deposit: $" + amount + " ($" + balance + ")\r\n";
			return true;
		}
		//tip: learn about exception handling at some point. not something you'd need to change.
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

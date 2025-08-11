import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Abstract base class for all types of bank accounts
 */
public abstract class BankAccount {
    protected String accountNumber;
    protected String accountHolderName;
    protected double balance;
    protected LocalDateTime dateCreated;
    protected static int accountCounter = 1000;
    
    // Constructor
    public BankAccount(String accountHolderName, double initialDeposit) {
        this.accountNumber = generateAccountNumber();
        this.accountHolderName = accountHolderName;
        this.balance = initialDeposit;
        this.dateCreated = LocalDateTime.now();
    }
    
    // Generate unique account number
    private String generateAccountNumber() {
        return "ACC" + (++accountCounter);
    }
    
    // Abstract methods to be implemented by subclasses
    public abstract boolean withdraw(double amount);
    public abstract double getMinimumBalance();
    public abstract String getAccountType();
    
    // Common methods for all account types
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }
    
    public double getBalance() {
        return balance;
    }
    
    public String getAccountNumber() {
        return accountNumber;
    }
    
    public String getAccountHolderName() {
        return accountHolderName;
    }
    
    public LocalDateTime getDateCreated() {
        return dateCreated;
    }
    
    public void displayAccountInfo() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println("\n=== Account Information ===");
        System.out.println("Account Number: " + accountNumber);
        System.out.println("Account Holder: " + accountHolderName);
        System.out.println("Account Type: " + getAccountType());
        System.out.println("Current Balance: $" + balance);
        System.out.println("Minimum Balance: $" + getMinimumBalance());
        System.out.println("Date Created: " + dateCreated.format(formatter));
        System.out.println("============================\n");
    }
    
    // Transfer money to another account
    public boolean transfer(BankAccount targetAccount, double amount) {
        if (this.withdraw(amount)) {
            targetAccount.deposit(amount);
            System.out.println("Successfully transferred $" + amount + 
                             " from " + this.accountNumber + 
                             " to " + targetAccount.getAccountNumber());
            return true;
        }
        return false;
    }
}

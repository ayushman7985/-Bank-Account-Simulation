/**
 * Checking Account class - extends BankAccount
 * Features: Overdraft protection, transaction fees
 */
public class CheckingAccount extends BankAccount {
    private double overdraftLimit;
    private double transactionFee;
    private int freeTransactions;
    private int transactionCount;
    private static final double MINIMUM_BALANCE = 0.0;
    
    public CheckingAccount(String accountHolderName, double initialDeposit, 
                          double overdraftLimit, double transactionFee, int freeTransactions) {
        super(accountHolderName, initialDeposit);
        this.overdraftLimit = overdraftLimit;
        this.transactionFee = transactionFee;
        this.freeTransactions = freeTransactions;
        this.transactionCount = 0;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
            return false;
        }
        
        // Check if withdrawal exceeds balance + overdraft limit
        if (amount > balance + overdraftLimit) {
            System.out.println("Withdrawal failed. Insufficient funds including overdraft limit of $" + overdraftLimit);
            return false;
        }
        
        balance -= amount;
        transactionCount++;
        
        // Apply transaction fee if free transactions exceeded
        if (transactionCount > freeTransactions) {
            balance -= transactionFee;
            System.out.println("Transaction fee of $" + transactionFee + " applied.");
        }
        
        System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
        
        if (balance < 0) {
            System.out.println("Warning: Account is overdrawn by $" + Math.abs(balance));
        }
        
        return true;
    }
    
    @Override
    public void deposit(double amount) {
        if (amount > 0) {
            balance += amount;
            transactionCount++;
            
            // Apply transaction fee if free transactions exceeded
            if (transactionCount > freeTransactions) {
                balance -= transactionFee;
                System.out.println("Transaction fee of $" + transactionFee + " applied.");
            }
            
            System.out.println("Deposited $" + amount + ". New balance: $" + balance);
        } else {
            System.out.println("Invalid deposit amount. Amount must be positive.");
        }
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    @Override
    public String getAccountType() {
        return "Checking Account";
    }
    
    public double getOverdraftLimit() {
        return overdraftLimit;
    }
    
    public void setOverdraftLimit(double overdraftLimit) {
        this.overdraftLimit = overdraftLimit;
        System.out.println("Overdraft limit updated to $" + overdraftLimit);
    }
    
    public int getTransactionCount() {
        return transactionCount;
    }
    
    public int getRemainingFreeTransactions() {
        return Math.max(0, freeTransactions - transactionCount);
    }
    
    public void resetMonthlyTransactions() {
        transactionCount = 0;
        System.out.println("Monthly transaction count reset.");
    }
}

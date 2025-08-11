/**
 * Savings Account class - extends BankAccount
 * Features: Interest calculation, minimum balance requirement
 */
public class SavingsAccount extends BankAccount {
    private double interestRate;
    private static final double MINIMUM_BALANCE = 100.0;
    
    public SavingsAccount(String accountHolderName, double initialDeposit, double interestRate) {
        super(accountHolderName, initialDeposit);
        this.interestRate = interestRate;
    }
    
    @Override
    public boolean withdraw(double amount) {
        if (amount <= 0) {
            System.out.println("Invalid withdrawal amount. Amount must be positive.");
            return false;
        }
        
        if (balance - amount < MINIMUM_BALANCE) {
            System.out.println("Withdrawal failed. Minimum balance of $" + MINIMUM_BALANCE + " must be maintained.");
            return false;
        }
        
        balance -= amount;
        System.out.println("Withdrawn $" + amount + ". New balance: $" + balance);
        return true;
    }
    
    @Override
    public double getMinimumBalance() {
        return MINIMUM_BALANCE;
    }
    
    @Override
    public String getAccountType() {
        return "Savings Account";
    }
    
    public void calculateInterest() {
        double interest = balance * (interestRate / 100);
        balance += interest;
        System.out.println("Interest of $" + String.format("%.2f", interest) + 
                          " added. New balance: $" + String.format("%.2f", balance));
    }
    
    public double getInterestRate() {
        return interestRate;
    }
    
    public void setInterestRate(double interestRate) {
        this.interestRate = interestRate;
        System.out.println("Interest rate updated to " + interestRate + "%");
    }
}

import java.util.ArrayList;
import java.util.List;

/**
 * Customer class to represent bank customers
 * Can hold multiple accounts
 */
public class Customer {
    private String customerId;
    private String name;
    private String email;
    private String phoneNumber;
    private String address;
    private List<BankAccount> accounts;
    private static int customerCounter = 1000;
    
    public Customer(String name, String email, String phoneNumber, String address) {
        this.customerId = generateCustomerId();
        this.name = name;
        this.email = email;
        this.phoneNumber = phoneNumber;
        this.address = address;
        this.accounts = new ArrayList<>();
    }
    
    private String generateCustomerId() {
        return "CUST" + (++customerCounter);
    }
    
    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account " + account.getAccountNumber() + " added for customer " + name);
    }
    
    public boolean removeAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                accounts.remove(account);
                System.out.println("Account " + accountNumber + " removed for customer " + name);
                return true;
            }
        }
        System.out.println("Account " + accountNumber + " not found for customer " + name);
        return false;
    }
    
    public BankAccount getAccount(String accountNumber) {
        for (BankAccount account : accounts) {
            if (account.getAccountNumber().equals(accountNumber)) {
                return account;
            }
        }
        return null;
    }
    
    public double getTotalBalance() {
        double total = 0;
        for (BankAccount account : accounts) {
            total += account.getBalance();
        }
        return total;
    }
    
    public void displayCustomerInfo() {
        System.out.println("\n=== Customer Information ===");
        System.out.println("Customer ID: " + customerId);
        System.out.println("Name: " + name);
        System.out.println("Email: " + email);
        System.out.println("Phone: " + phoneNumber);
        System.out.println("Address: " + address);
        System.out.println("Total Accounts: " + accounts.size());
        System.out.println("Total Balance: $" + String.format("%.2f", getTotalBalance()));
        System.out.println("=============================\n");
        
        if (!accounts.isEmpty()) {
            System.out.println("Account Details:");
            for (BankAccount account : accounts) {
                System.out.println("- " + account.getAccountType() + " (" + 
                                 account.getAccountNumber() + "): $" + 
                                 String.format("%.2f", account.getBalance()));
            }
            System.out.println();
        }
    }
    
    // Getters and Setters
    public String getCustomerId() { return customerId; }
    public String getName() { return name; }
    public void setName(String name) { this.name = name; }
    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }
    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }
    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
    public List<BankAccount> getAccounts() { return new ArrayList<>(accounts); }
}

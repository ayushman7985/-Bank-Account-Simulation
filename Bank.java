import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Bank class to manage all customers and their accounts
 * Central management system for the bank
 */
public class Bank {
    private String bankName;
    private List<Customer> customers;
    private Scanner scanner;
    
    public Bank(String bankName) {
        this.bankName = bankName;
        this.customers = new ArrayList<>();
        this.scanner = new Scanner(System.in);
    }
    
    public Customer createCustomer(String name, String email, String phoneNumber, String address) {
        Customer customer = new Customer(name, email, phoneNumber, address);
        customers.add(customer);
        System.out.println("Customer " + name + " created successfully with ID: " + customer.getCustomerId());
        return customer;
    }
    
    public Customer findCustomer(String customerId) {
        for (Customer customer : customers) {
            if (customer.getCustomerId().equals(customerId)) {
                return customer;
            }
        }
        return null;
    }
    
    public BankAccount findAccount(String accountNumber) {
        for (Customer customer : customers) {
            BankAccount account = customer.getAccount(accountNumber);
            if (account != null) {
                return account;
            }
        }
        return null;
    }
    
    public void displayAllCustomers() {
        if (customers.isEmpty()) {
            System.out.println("No customers found.");
            return;
        }
        
        System.out.println("\n=== All Customers ===");
        for (Customer customer : customers) {
            System.out.println("ID: " + customer.getCustomerId() + 
                             ", Name: " + customer.getName() + 
                             ", Accounts: " + customer.getAccounts().size() + 
                             ", Total Balance: $" + String.format("%.2f", customer.getTotalBalance()));
        }
        System.out.println("=====================\n");
    }
    
    public void displayBankSummary() {
        double totalBankBalance = 0;
        int totalAccounts = 0;
        
        for (Customer customer : customers) {
            totalBankBalance += customer.getTotalBalance();
            totalAccounts += customer.getAccounts().size();
        }
        
        System.out.println("\n=== " + bankName + " Summary ===");
        System.out.println("Total Customers: " + customers.size());
        System.out.println("Total Accounts: " + totalAccounts);
        System.out.println("Total Bank Balance: $" + String.format("%.2f", totalBankBalance));
        System.out.println("===============================\n");
    }
    
    public void performTransaction() {
        System.out.println("\n=== Transaction Menu ===");
        System.out.println("1. Deposit");
        System.out.println("2. Withdraw");
        System.out.println("3. Transfer");
        System.out.println("4. Check Balance");
        System.out.print("Choose transaction type: ");
        
        int choice = scanner.nextInt();
        scanner.nextLine(); // consume newline
        
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        BankAccount account = findAccount(accountNumber);
        if (account == null) {
            System.out.println("Account not found!");
            return;
        }
        
        switch (choice) {
            case 1: // Deposit
                System.out.print("Enter deposit amount: $");
                double depositAmount = scanner.nextDouble();
                account.deposit(depositAmount);
                break;
                
            case 2: // Withdraw
                System.out.print("Enter withdrawal amount: $");
                double withdrawAmount = scanner.nextDouble();
                account.withdraw(withdrawAmount);
                break;
                
            case 3: // Transfer
                scanner.nextLine(); // consume newline
                System.out.print("Enter target account number: ");
                String targetAccountNumber = scanner.nextLine();
                BankAccount targetAccount = findAccount(targetAccountNumber);
                
                if (targetAccount == null) {
                    System.out.println("Target account not found!");
                    return;
                }
                
                System.out.print("Enter transfer amount: $");
                double transferAmount = scanner.nextDouble();
                account.transfer(targetAccount, transferAmount);
                break;
                
            case 4: // Check Balance
                System.out.println("Current balance: $" + String.format("%.2f", account.getBalance()));
                break;
                
            default:
                System.out.println("Invalid choice!");
        }
    }
    
    // Getters
    public String getBankName() { return bankName; }
    public List<Customer> getCustomers() { return new ArrayList<>(customers); }
}

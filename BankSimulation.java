import java.util.Scanner;

/**
 * Main class for Bank Account Simulation
 * Demonstrates the complete banking system with interactive menu
 */
public class BankSimulation {
    private static Bank bank;
    private static Scanner scanner;
    
    public static void main(String[] args) {
        bank = new Bank("First National Bank");
        scanner = new Scanner(System.in);
        
        System.out.println("=== Welcome to " + bank.getBankName() + " ===");
        System.out.println("Bank Account Simulation System");
        
        // Create sample data for demonstration
        createSampleData();
        
        // Main menu loop
        boolean running = true;
        while (running) {
            displayMainMenu();
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline
            
            switch (choice) {
                case 1:
                    createNewCustomer();
                    break;
                case 2:
                    createNewAccount();
                    break;
                case 3:
                    bank.performTransaction();
                    break;
                case 4:
                    viewAccountDetails();
                    break;
                case 5:
                    viewCustomerDetails();
                    break;
                case 6:
                    bank.displayAllCustomers();
                    break;
                case 7:
                    bank.displayBankSummary();
                    break;
                case 8:
                    calculateInterest();
                    break;
                case 9:
                    System.out.println("Thank you for using " + bank.getBankName() + "!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid choice! Please try again.");
            }
        }
        
        scanner.close();
    }
    
    private static void displayMainMenu() {
        System.out.println("\n=== Main Menu ===");
        System.out.println("1. Create New Customer");
        System.out.println("2. Create New Account");
        System.out.println("3. Perform Transaction");
        System.out.println("4. View Account Details");
        System.out.println("5. View Customer Details");
        System.out.println("6. View All Customers");
        System.out.println("7. Bank Summary");
        System.out.println("8. Calculate Interest (Savings Accounts)");
        System.out.println("9. Exit");
        System.out.print("Enter your choice: ");
    }
    
    private static void createNewCustomer() {
        System.out.println("\n=== Create New Customer ===");
        System.out.print("Enter customer name: ");
        String name = scanner.nextLine();
        System.out.print("Enter email: ");
        String email = scanner.nextLine();
        System.out.print("Enter phone number: ");
        String phone = scanner.nextLine();
        System.out.print("Enter address: ");
        String address = scanner.nextLine();
        
        Customer customer = bank.createCustomer(name, email, phone, address);
        customer.displayCustomerInfo();
    }
    
    private static void createNewAccount() {
        System.out.println("\n=== Create New Account ===");
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = bank.findCustomer(customerId);
        if (customer == null) {
            System.out.println("Customer not found!");
            return;
        }
        
        System.out.println("Select account type:");
        System.out.println("1. Savings Account");
        System.out.println("2. Checking Account");
        System.out.print("Enter choice: ");
        int accountType = scanner.nextInt();
        
        System.out.print("Enter initial deposit amount: $");
        double initialDeposit = scanner.nextDouble();
        
        BankAccount account = null;
        
        switch (accountType) {
            case 1: // Savings Account
                System.out.print("Enter interest rate (%): ");
                double interestRate = scanner.nextDouble();
                account = new SavingsAccount(customer.getName(), initialDeposit, interestRate);
                break;
                
            case 2: // Checking Account
                System.out.print("Enter overdraft limit: $");
                double overdraftLimit = scanner.nextDouble();
                System.out.print("Enter transaction fee: $");
                double transactionFee = scanner.nextDouble();
                System.out.print("Enter number of free transactions per month: ");
                int freeTransactions = scanner.nextInt();
                account = new CheckingAccount(customer.getName(), initialDeposit, 
                                            overdraftLimit, transactionFee, freeTransactions);
                break;
                
            default:
                System.out.println("Invalid account type!");
                return;
        }
        
        customer.addAccount(account);
        account.displayAccountInfo();
    }
    
    private static void viewAccountDetails() {
        System.out.print("Enter account number: ");
        String accountNumber = scanner.nextLine();
        
        BankAccount account = bank.findAccount(accountNumber);
        if (account != null) {
            account.displayAccountInfo();
            
            // Show additional details for specific account types
            if (account instanceof CheckingAccount) {
                CheckingAccount checkingAccount = (CheckingAccount) account;
                System.out.println("Overdraft Limit: $" + checkingAccount.getOverdraftLimit());
                System.out.println("Transaction Count: " + checkingAccount.getTransactionCount());
                System.out.println("Remaining Free Transactions: " + checkingAccount.getRemainingFreeTransactions());
            } else if (account instanceof SavingsAccount) {
                SavingsAccount savingsAccount = (SavingsAccount) account;
                System.out.println("Interest Rate: " + savingsAccount.getInterestRate() + "%");
            }
        } else {
            System.out.println("Account not found!");
        }
    }
    
    private static void viewCustomerDetails() {
        System.out.print("Enter customer ID: ");
        String customerId = scanner.nextLine();
        
        Customer customer = bank.findCustomer(customerId);
        if (customer != null) {
            customer.displayCustomerInfo();
        } else {
            System.out.println("Customer not found!");
        }
    }
    
    private static void calculateInterest() {
        System.out.println("\n=== Calculate Interest for Savings Accounts ===");
        boolean foundSavingsAccounts = false;
        
        for (Customer customer : bank.getCustomers()) {
            for (BankAccount account : customer.getAccounts()) {
                if (account instanceof SavingsAccount) {
                    foundSavingsAccounts = true;
                    SavingsAccount savingsAccount = (SavingsAccount) account;
                    System.out.println("Processing account: " + account.getAccountNumber() + 
                                     " (Owner: " + customer.getName() + ")");
                    savingsAccount.calculateInterest();
                    System.out.println();
                }
            }
        }
        
        if (!foundSavingsAccounts) {
            System.out.println("No savings accounts found in the system.");
        }
    }
    
    private static void createSampleData() {
        System.out.println("Creating sample data for demonstration...\n");
        
        // Create sample customers
        Customer customer1 = bank.createCustomer("John Doe", "john.doe@email.com", "123-456-7890", "123 Main St");
        Customer customer2 = bank.createCustomer("Jane Smith", "jane.smith@email.com", "098-765-4321", "456 Oak Ave");
        
        // Create sample accounts
        SavingsAccount savings1 = new SavingsAccount("John Doe", 1000.0, 2.5);
        CheckingAccount checking1 = new CheckingAccount("John Doe", 500.0, 200.0, 1.50, 5);
        SavingsAccount savings2 = new SavingsAccount("Jane Smith", 2500.0, 3.0);
        
        customer1.addAccount(savings1);
        customer1.addAccount(checking1);
        customer2.addAccount(savings2);
        
        System.out.println("Sample data created successfully!");
        System.out.println("You can use the following for testing:");
        System.out.println("- Customer IDs: " + customer1.getCustomerId() + ", " + customer2.getCustomerId());
        System.out.println("- Account Numbers: " + savings1.getAccountNumber() + ", " + 
                          checking1.getAccountNumber() + ", " + savings2.getAccountNumber());
    }
}

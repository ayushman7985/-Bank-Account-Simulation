# Bank Account Simulation System

A comprehensive Java-based banking system simulation that demonstrates Object-Oriented Programming (OOP) principles including inheritance, polymorphism, encapsulation, and abstraction.

## System Overview

This banking simulation system provides a complete framework for managing bank customers, accounts, and transactions. It supports multiple account types with different features and business rules.

## Class Structure

### Core Classes

#### 1. `BankAccount` (Abstract Base Class)
- **Purpose**: Abstract base class for all account types
- **Key Features**:
  - Account number generation
  - Basic deposit/withdrawal operations
  - Account information display
  - Money transfer between accounts
- **Abstract Methods**: `withdraw()`, `getMinimumBalance()`, `getAccountType()`

#### 2. `SavingsAccount` (extends BankAccount)
- **Purpose**: Savings account with interest calculation
- **Key Features**:
  - Minimum balance requirement ($100)
  - Interest rate management
  - Interest calculation and application
  - Withdrawal restrictions based on minimum balance

#### 3. `CheckingAccount` (extends BankAccount)
- **Purpose**: Checking account with overdraft protection
- **Key Features**:
  - Overdraft limit support
  - Transaction fee system
  - Free transaction allowance
  - Monthly transaction tracking

#### 4. `Customer`
- **Purpose**: Represents bank customers
- **Key Features**:
  - Multiple account management
  - Customer information storage
  - Total balance calculation across all accounts
  - Account addition/removal

#### 5. `Bank`
- **Purpose**: Central management system
- **Key Features**:
  - Customer management
  - Account lookup and management
  - Transaction processing
  - Bank-wide reporting and statistics

#### 6. `BankSimulation`
- **Purpose**: Main application with interactive menu
- **Key Features**:
  - User-friendly interface
  - Complete banking operations
  - Sample data generation
  - Interactive transaction processing

## Key Features

### Account Management
- **Multiple Account Types**: Savings and Checking accounts with different rules
- **Unique Account Numbers**: Automatically generated unique identifiers
- **Account Information**: Complete account details with creation timestamps

### Transaction Processing
- **Deposits**: Add money to accounts with validation
- **Withdrawals**: Remove money with business rule enforcement
- **Transfers**: Move money between any accounts in the system
- **Balance Inquiries**: Real-time balance checking

### Business Rules
- **Savings Accounts**:
  - Minimum balance: $100
  - Interest calculation and application
  - Withdrawal restrictions to maintain minimum balance

- **Checking Accounts**:
  - Overdraft protection with configurable limits
  - Transaction fee system after free transaction limit
  - Monthly transaction tracking and reset

### Customer Management
- **Customer Profiles**: Complete customer information storage
- **Multiple Accounts**: Customers can have multiple accounts of different types
- **Portfolio View**: Total balance across all customer accounts

## Sample Usage

### Running the Application
```bash
javac *.java
java BankSimulation
```

### Sample Operations
1. **Create Customer**: Add new customers with contact information
2. **Create Accounts**: Open savings or checking accounts for customers
3. **Perform Transactions**: Deposit, withdraw, transfer money
4. **View Details**: Check account and customer information
5. **Calculate Interest**: Apply interest to all savings accounts
6. **Generate Reports**: View bank-wide statistics

## Technical Implementation

### OOP Principles Demonstrated

#### 1. **Inheritance**
- `SavingsAccount` and `CheckingAccount` inherit from `BankAccount`
- Common functionality in base class, specific features in derived classes

#### 2. **Polymorphism**
- Different account types implement `withdraw()` method differently
- Accounts can be treated uniformly through base class reference

#### 3. **Encapsulation**
- Private fields with public getter/setter methods
- Protected access for derived classes
- Data validation in setter methods

#### 4. **Abstraction**
- Abstract `BankAccount` class defines common interface
- Concrete implementations provide specific behavior
- Client code works with abstractions, not implementations

### Design Patterns Used
- **Template Method**: Base class defines algorithm structure
- **Factory Method**: Account number generation
- **Composite**: Customer contains multiple accounts

## System Architecture

```
BankSimulation (Main Application)
    ↓
Bank (Management Layer)
    ↓
Customer (Customer Management)
    ↓
BankAccount (Abstract Account Layer)
    ↓
SavingsAccount / CheckingAccount (Concrete Implementations)
```

## Sample Data

The system automatically creates sample data for testing:
- **Customers**: John Doe, Jane Smith
- **Accounts**: Savings and checking accounts with different configurations
- **Balances**: Various initial deposits for testing transactions

## Error Handling

- **Input Validation**: All user inputs are validated
- **Business Rule Enforcement**: Account-specific rules are enforced
- **Transaction Verification**: Insufficient funds and limit checks
- **User Feedback**: Clear error messages and success confirmations

## Future Enhancements

- **Additional Account Types**: Money market, CD accounts
- **Transaction History**: Detailed transaction logging
- **Interest Compounding**: More sophisticated interest calculations
- **Database Integration**: Persistent data storage
- **Web Interface**: Browser-based user interface
- **Security Features**: Authentication and authorization

## Learning Objectives

This simulation demonstrates:
- **Object-Oriented Design**: Proper class hierarchy and relationships
- **Java Programming**: Advanced Java features and best practices
- **Business Logic Implementation**: Real-world banking rules and constraints
- **User Interface Design**: Interactive console-based application
- **Error Handling**: Robust input validation and error management

## Testing the System

1. **Run the application**: `java BankSimulation`
2. **Use sample data**: Pre-created customers and accounts for immediate testing
3. **Try different operations**: Test deposits, withdrawals, transfers
4. **Test business rules**: Try to violate minimum balance, overdraft limits
5. **Generate reports**: View customer and bank summaries

This banking simulation provides a comprehensive example of how to build a real-world application using object-oriented programming principles in Java.

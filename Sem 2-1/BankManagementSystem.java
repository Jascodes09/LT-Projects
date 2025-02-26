import java.util.*;
class BankAccount {
private String accountNumber;
private String customerName;
private double balance;
private String accountType;
private List<String> miniStatement;
public BankAccount(String accountNumber, String customerName, double initialDeposit) {
this.accountNumber = accountNumber;
this.customerName = customerName;
this.balance = initialDeposit;
this.miniStatement = new ArrayList<>();
this.miniStatement.add("Initial deposit: $" + initialDeposit);
}
public String getAccountNumber() {
return accountNumber;
}
public String getCustomerName() {
return customerName;
}
public double getBalance() {
return balance;
}
public void deposit(double amount) {
if (amount > 0) {
balance += amount;
miniStatement.add("Deposit: $" + amount);
System.out.println("Deposit successful. New balance: $" + balance);
} else {
System.out.println("Invalid deposit amount. Please enter a positive value.");
}
}
public void transfer(BankAccount recipient, double amount) {
if (amount > 0 && balance >= amount) {
balance -= amount;
recipient.deposit(amount);
miniStatement.add("Transfer to " + recipient.getCustomerName() + ": $" + amount);
System.out.println("Transfer successful. New balance: $" + balance);
} else {
System.out.println("Invalid transfer amount or insufficient balance.");
}
}
public void displayMiniStatement() {
System.out.println("Mini Statement for Account Number " + accountNumber);
for (String transaction : miniStatement) {
System.out.println(transaction);
}
}
public void displayAccountInfo() {
System.out.println("\nAccount Information:");
System.out.println("Account Number: " + accountNumber);
System.out.println("Customer Name: " + customerName);
System.out.println("Account Type: " + accountType);
System.out.println("Balance: $" + balance);
}
public void checkBalance() {
System.out.println("Account Balance for Account Number " + accountNumber + ": $" +
balance);
}
public boolean login(String inputAccNumber) {
return accountNumber.equals(inputAccNumber);
}
}
public class BankManagementSystem {
public static void main(String[] args) {
Scanner scanner = new Scanner(System.in);
Map<String, BankAccount> accounts = new HashMap<>();
while (true) {
System.out.println("\nBank Management System");
System.out.println("1. Create Account");
System.out.println("2. Deposit Amount");
System.out.println("3. Transfer Amount");
System.out.println("4. Account Info");
System.out.println("5. Mini Statement");
System.out.println("6. Check Balance");
System.out.println("7. Exit");
System.out.print("Enter your choice: ");
int choice = scanner.nextInt();
scanner.nextLine(); // Consume newline
switch (choice) {
case 1:
System.out.print("Enter account number: ");
String accNumber = scanner.nextLine();
System.out.print("Enter customer name: ");
String customerName = scanner.nextLine();
System.out.print("Enter initial deposit amount: $");
double initialDeposit = scanner.nextDouble();
scanner.nextLine(); // Consume newline
BankAccount newAccount = new BankAccount(accNumber, customerName,
initialDeposit);
accounts.put(accNumber, newAccount);
System.out.println("Account created successfully!");
break;
case 2:
System.out.print("Enter account number: ");
String depositAccNumber = scanner.nextLine();
System.out.print("Enter deposit amount: $");
double depositAmount = scanner.nextDouble();
scanner.nextLine(); // Consume newline
BankAccount depositAccount = accounts.get(depositAccNumber);
if (depositAccount != null) {
depositAccount.deposit(depositAmount);
} else {
System.out.println("Account not found.");
}
break;
case 3:
System.out.print("Enter your account number: ");
String senderAccNumber = scanner.nextLine();
System.out.print("Enter recipient's account number: ");
String recipientAccNumber = scanner.nextLine();
System.out.print("Enter transfer amount: $");
double transferAmount = scanner.nextDouble();
scanner.nextLine(); // Consume newline
BankAccount senderAccount = accounts.get(senderAccNumber);
BankAccount recipientAccount = accounts.get(recipientAccNumber);
if (senderAccount != null && recipientAccount != null) {
senderAccount.transfer(recipientAccount, transferAmount);
} else {
System.out.println("Invalid account numbers. Please check and try again.");
}
break;
case 4:
System.out.print("Enter account number: ");
String accountNumberToDisplay = scanner.nextLine();
BankAccount accountToDisplay = accounts.get(accountNumberToDisplay);
if (accountToDisplay != null) {
accountToDisplay.displayAccountInfo();
} else {
System.out.println("Account not found.");
}
break;
case 5:
System.out.print("Enter account number: ");
String statementAccNumber = scanner.nextLine();
BankAccount statementAccount = accounts.get(statementAccNumber);
if (statementAccount != null) {
statementAccount.displayMiniStatement();
} else {
System.out.println("Account not found.");
}
break;
case 6:
System.out.print("Enter account number: ");
String accountNumberToCheck = scanner.nextLine();
BankAccount accountToCheck = accounts.get(accountNumberToCheck);
if (accountToCheck != null) {
accountToCheck.checkBalance();
} else {
System.out.println("Account not found.");
}
break;
case 7:
System.out.println("Exiting Bank Management System. Have a great day!");
System.exit(0);
default:
System.out.println("Invalid choice. Please select a valid option.");
}
}
}
}
package com.controller;

import java.sql.SQLException;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

import com.dto.AccountDetails;
import com.exception.InsufficientFundException;
import com.exception.InvalidAccountException;
import com.model.Account;
import com.model.Customer;
import com.service.AccountService;
import com.service.CustomerService;

public class BankController {
	public static void main(String[] args) {
		CustomerService customerService = new CustomerService();
		AccountService accountService = new AccountService();
		Scanner sc = new Scanner(System.in);
		while (true) {
			System.out.println();
			System.out.println("-------------Banking System-------------");
			System.out.println();
			System.out.println("Press 1. Create Account");
			System.out.println("Press 2. Show Account Balance");
			System.out.println("Press 3. Deposit Amount");
			System.out.println("Press 4. Withdraw Amount");
			System.out.println("Press 5. Transfer Amount");
			System.out.println("Press 6. List All Accounts");
			System.out.println("Press 7. Calculate Interest");
			System.out.println("Press 8. Show Account Details");
			System.out.println("Press 0. To Exit");
			System.out.print("Choose an option: ");
			int input = sc.nextInt();
			if (input == 0) {
				System.out.println("Exiting from Banking System...");
				System.out.println("Thank you for using banking system application");
				break;
			}
			switch (input) {
			case 1:
				System.out.println();
				System.out.println("-------------Create Account-------------");
				Random random = new Random();
				int randomNumber = random.nextInt();
				int customerId = randomNumber < 0 ? randomNumber * -1 : randomNumber;
				sc.nextLine();
				System.out.print("Enter First name : ");
				String firstName = sc.nextLine().trim();
				System.out.print("Enter Last name : ");
				String lastName = sc.nextLine().trim();
				System.out.print("Enter email : ");
				String email = sc.nextLine().trim();
				System.out.print("Enter phone number : ");
				String phoneNumber = sc.nextLine().trim();
				System.out.print("Enter address : ");
				String address = sc.nextLine().trim();

				Customer customer = new Customer(customerId, firstName, lastName, email, phoneNumber, address);

				random = new Random();
				randomNumber = random.nextInt();
				int accountId = randomNumber < 0 ? randomNumber * -1 : randomNumber;

				random = new Random();
				randomNumber = random.nextInt();
				int accountNumber = randomNumber < 0 ? randomNumber * -1 : randomNumber;
				System.out.print("Enter account type : ");
				String accountType = sc.nextLine().trim();

				Account account = new Account(accountId, accountNumber, accountType, 0.0, customerId);

				try {
					int customerStatus = customerService.save(customer);
					int accountStatus = accountService.save(account);
					if (customerStatus == 1 && accountStatus == 1)
						System.out.println("Account created successfully!!!");
					else
						System.out.println("Account creation failed...");
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 2:
				System.out.println();
				System.out.println("-------------Check Account Balance-------------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				try {
					double accountBalance = accountService.getAccountBalance(accountNumber);
					System.out.println("Your account balance is " + accountBalance + " Rs");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 3:
				System.out.println();
				System.out.println("-------------Deposit Amount-------------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				System.out.print("Enter deposit amount: ");
				double depositAmount = sc.nextDouble();
				try {
					int depositStatus = accountService.deposit(accountNumber, depositAmount);
					if (depositStatus == 1)
						System.out.println("Amount Deposited successfully!!!");
					else
						System.out.println("Amount Deposition failed...");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 4:
				System.out.println();
				System.out.println("-------------Withdraw Amount-------------");
				System.out.print("Enter account number : ");
				accountNumber = sc.nextInt();
				System.out.print("Enter withdrawal amount: ");
				double withdrawalAmount = sc.nextDouble();
				try {
					withdrawalAmount = accountService.withdraw(accountNumber, withdrawalAmount);
					if (withdrawalAmount > -1)
						System.out.println("Rs. " + withdrawalAmount + " Withdrawn successfully!!!");
					else
						System.out.println("Amount Withdrawn failed...");
				} catch (InvalidAccountException e) {
					System.out.println(e.getMessage());

				} catch (InsufficientFundException e) {
					System.out.println(e.getMessage());
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 5:
				System.out.println();
				System.out.println("-------------Transfer Amount-------------");
				System.out.print("Enter Sender's account number : ");
				int senderAccountNumber = sc.nextInt();
				System.out.print("Enter Receiver's account number : ");
				int receiverAccountNumber = sc.nextInt();
				System.out.print("Enter withdrawal amount: ");
				double transferAmount = sc.nextDouble();
				try {
					transferAmount = accountService.transfer(senderAccountNumber, receiverAccountNumber,
							transferAmount);
					if (transferAmount > -1)
						System.out.println("Rs. " + transferAmount + " transfered successfully!!!");
					else
						System.out.println("Amount transition failed...");
				} catch (InsufficientFundException | InvalidAccountException | SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 6:
				System.out.println();
				System.out.println("-------------List of all Accounts-------------");
				try {
					List<Account> list = accountService.getAllAccounts();
					for (Account a : list)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			case 7:
				System.out.println();
				System.out.println("-------------Calculate Interest-------------");
				System.out.print("Enter Principal Amount : ");
				double principalAmount = sc.nextDouble();
				System.out.print("Enter Time Period : ");
				double timePeriod = sc.nextDouble();
				double interestRate = 4.5;// fixed interest rate
				double interest = principalAmount * timePeriod * interestRate;
				System.out.printf("The simple interest is %,.2f", interest);
				break;

			case 8:
				System.out.println();
				System.out.println("-------------All Accounts Details-------------");
				try {
					List<AccountDetails> list1 = accountService.getAccountsDetails();
					for (AccountDetails a : list1)
						System.out.println(a);
				} catch (SQLException e) {
					System.out.println(e.getMessage());
				}
				break;

			default:
				System.out.println();
				System.out.println("Invalid option, Please select a valid option");
			}
		}
		sc.close();
	}
}

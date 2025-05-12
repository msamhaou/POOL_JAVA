package ex04;

import java.util.Scanner;

public class Program {
	private static void Test(){
		assert 1 == 1;
	}

	public static void main(String[] args) {
		User firstUser = new User("taha");
		firstUser.setBalance(500);

		User secondUser = new User("yas");
		secondUser.setBalance(900);

		TransactionsService transactionsService = new TransactionsService();
		transactionsService.addUserToService(firstUser);
		transactionsService.addUserToService(secondUser);

		transactionsService.performTransfer(firstUser.getId(), secondUser.getId(), 200);
		System.out.println(transactionsService.getUserBalance(firstUser.getId()));

		transactionsService.performTransfer(firstUser.getId(), secondUser.getId(), 200);
		System.out.println(transactionsService.getUserBalance(firstUser.getId()));

		transactionsService.removeUserIdTransaction(firstUser.getId(), firstUser.getTransactionArray()[0].getUuid());
		System.out.println(transactionsService.checkValidity().length);
	}
}

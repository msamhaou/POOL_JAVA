package ex04;

import java.util.Scanner;

public class Program {
	static public TransactionsLinkedList initTransactionLinkedList(){
		TransactionsLinkedList list = new TransactionsLinkedList();
		for (int i = 0; i < 20; i++){
			Transaction transaction = new Transaction();
			transaction.setSender(new User("S"+i));
			transaction.setRecipient(new User("R"+i));
			transaction.setAmount(500 + i);
			list.addTransaction(transaction);
		}
		return list;
	}

	public static void transactionsLinkedListIterator(TransactionsLinkedList transactionsLinkedList){
		for
		(TransactionsLinkedList.Node node = transactionsLinkedList.head;
				node != null;
				node = node.next
		)
		{
			System.out.println(node.data);
		}
	}

	public static void main(String[] args) {
		TransactionsLinkedList transactionsLinkedList = initTransactionLinkedList();
		transactionsLinkedListIterator(transactionsLinkedList);
		String uuid = transactionsLinkedList.toArray()[9].getId();
		transactionsLinkedList.removeTransactionByUUID(uuid);
		transactionsLinkedListIterator(transactionsLinkedList);
	}
}

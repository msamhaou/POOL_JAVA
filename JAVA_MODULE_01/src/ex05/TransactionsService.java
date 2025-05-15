package ex05;

public class TransactionsService {
	private UsersArrayList users;

	TransactionsService(){
		users = new UsersArrayList();
	}

	TransactionsService(UsersArrayList users){
		this.users = users;
	}

	public void addUserToService(User user){
		users.addUser(user);
	}

	public int getUserBalance(int id ){
		return users.getUserById(id).getBalance();
	}

	public void checkSenderBalance(User sender, int amount){
		if (sender.getBalance() < amount) {
			throw new IllegalTransactionException(
					"Illegal Transaction: Invalid Balance\n"+
							"\tUser with ID: " + sender.getId() + "\n"+
							"\tSender balance: "+ sender.getBalance()
			);
		}
	}

	public void performTransfer(int senderId, int recipientId, int amount){
		User sender = users.getUserById(senderId);
		User recipient = users.getUserById(recipientId);

		checkSenderBalance(sender, amount);

		sender.setBalance(sender.getBalance() - amount);
		recipient.setBalance(recipient.getBalance() + amount);
		Transaction transaction = new Transaction(sender, recipient, Transaction.Category.OUTCOME);
		transaction.setAmount(-amount);
		sender.addTransaction(transaction);

		Transaction sencondTransaction = new Transaction(recipient, sender, Transaction.Category.INCOME);
		sencondTransaction.setAmount(amount);
		sencondTransaction.setUuid(transaction.getUuid());
		recipient.addTransaction(sencondTransaction);
	}

	public Transaction removeUserIdTransaction(int id, String uuid){
		User user = users.getUserById(id);
		Transaction removedTransaction = user.removeTransaction(uuid);
		return removedTransaction;
	}

	private User getParticipant(User user, Transaction transaction){
		if (transaction.getSender() == user){
			return transaction.getRecipient();
		}else if (transaction.getRecipient() == user){
			return transaction.getSender();
		}
		throw new RuntimeException("Error At Getting Participant");
	}

	private boolean isTransactionPair(Transaction transaction, Transaction[] transactionArray){
		String uuid = transaction.getUuid();
		for (int k=0; k < transactionArray.length; k++){
			if (transactionArray[k].getUuid().equals(uuid))
				return true;
		}
		return false;
	}

	public UsersArrayList getUsers() {
		return users;
	}

	public Transaction[] checkValidity(){
		TransactionsLinkedList UnpairedTransactionsLinkedList = new TransactionsLinkedList();
		for (int i = 0; i < users.UsersNumber(); i++){
			User user = users.getUserByIndex(i);
			Transaction[] transactionArray = user.getTransactionArray();
			for (int j =0 ; j < transactionArray.length; j++){
				Transaction transaction = transactionArray[j];
				String uuid = transactionArray[j].getUuid();
				User participant = getParticipant(user, transaction);
				Transaction[] secondTransactionArray = participant.getTransactionArray();
				if (!isTransactionPair(transaction, secondTransactionArray))
					UnpairedTransactionsLinkedList.addTransaction(transaction);
			}
		}
		return UnpairedTransactionsLinkedList.toArray();
	}
}

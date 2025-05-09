package ex04;

public class TransactionsService {
	private UsersArrayList users;

	public void addUserToService(User user){
		users.addUser(user);
	}

	public int getUserBalance(int id ){
		return users.getUserById(id).getBalance();
	}

	public Transaction flipTransactionCopy(Transaction transaction){
		Transaction flipedTransaction = new Transaction();
		flipedTransaction.setId(transaction.getId());
		flipedTransaction.setRecipient(transaction.getSender());
		flipedTransaction.setSender(transaction.getRecipient());
		flipedTransaction.setCategory(Transaction.Category.INCOME);
		flipedTransaction.setAmount(transaction.getAmount());
		return flipedTransaction;
	}
	public void performTransaction(int senderId, int recipientId, int amount){
		User sender = users.getUserById(senderId);
		User recipient = users.getUserById(recipientId);
		Transaction transaction = new Transaction();
		transaction.setSender(sender);
		transaction.setRecipient(recipient);
		transaction.setCategory(Transaction.Category.OUTCOME);
		transaction.setAmount(-amount);

	}

}

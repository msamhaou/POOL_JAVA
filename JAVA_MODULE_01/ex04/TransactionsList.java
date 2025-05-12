package ex04;

public interface TransactionsList {
	public void addTransaction(Transaction transaction);
	public Transaction removeTransactionByUUID(String uuid);
	public Transaction[] toArray();

}

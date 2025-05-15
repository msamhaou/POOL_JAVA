package ex05;

public interface TransactionsList {
	public void addTransaction(Transaction transaction);
	public Transaction removeTransactionByUUID(String uuid);
	public Transaction[] toArray();

}

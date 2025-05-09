package ex03;

public interface TransactionsList {
	public void addTransaction(Transaction transaction);
	public void removeTransactionByUUID(String uuid);
	public Transaction[] toArray();

}

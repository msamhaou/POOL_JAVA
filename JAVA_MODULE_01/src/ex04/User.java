package ex04;

public class User {
	private final int		id;
	private String	name;
	private int		balance;
	private TransactionsLinkedList transactionsLinkedList;

	public User(){
		id = UserIdsGenerator.getInstance().generateId();
		this.transactionsLinkedList = new TransactionsLinkedList();
	}

	public User(String name) {
		this.name = name;
		id = UserIdsGenerator.getInstance().generateId();
		this.transactionsLinkedList = new TransactionsLinkedList();
	}
	private void printErr(String err){
		System.err.println(err);
		System.exit(-1);
	}

	public int getBalance(){
		return this.balance;
	}

	public void setBalance(int balance){
		this.balance = balance;
	}

	public void setName(String name){
		 this.name = name;
	}

	public String getName(){
		return this.name;
	}

	public int getId(){
		return this.id;
	}

	public void addTransaction(Transaction transaction){
		transactionsLinkedList.addTransaction(transaction);
	}

	public Transaction removeTransaction(String uuid){
		return transactionsLinkedList.removeTransactionByUUID(uuid);
	}

	public Transaction[] getTransactionArray(){
		return transactionsLinkedList.toArray();
	}

	@Override
	public String toString(){
		return "User{" +
				"id=" + id +
				", name='" + name + '\'' +
				", balance=" + balance +
				'}';
	}
}

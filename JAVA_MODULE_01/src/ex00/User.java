package ex00;

public class User {
	private int		id;
	private String	name;
	private int		balance;

	private void printErr(String err){
		System.err.println(err);
		System.exit(-1);
	}

	public User(int id, String name, int balance){
		if (balance< 0)
			this.printErr("No Enough Balance to Execute Transactions");
		this.name = name;
		this.id = id;
		this.balance = balance;
	}

	public int getBalance(){
		return this.balance;
	}

	public void setBalance(int balance){
		this.balance += balance;
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

	public void setId(int id){
		this.id = id;
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

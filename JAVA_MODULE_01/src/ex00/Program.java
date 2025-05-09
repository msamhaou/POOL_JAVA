package ex00;

public class Program {
	static public void main(String[] args) {
		User taha = new User(1,"taha", 0);
		User yas = new User(2, "yassine", 5000);
		System.out.println(taha);
		System.out.println(yas);
		//Transaction
		Transaction trans = new Transaction();
		trans.setSender(taha);
		trans.setRecipient(yas);
		trans.setCategory(Transaction.Category.OUTCOME);
		trans.setAmount(-500);
		//Execute
		taha.setBalance(-500);
		yas.setBalance(500);
		System.out.println(taha);
		System.out.println(yas);
	}
}

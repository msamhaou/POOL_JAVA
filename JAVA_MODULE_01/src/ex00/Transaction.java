package ex00;

import java.util.UUID;

public class Transaction {
	public enum Category {
		OUTCOME,
		INCOME
	}
	private String id = UUID.randomUUID().toString();
	private User Recipient;
	private User Sender;
	private Category category;
	private int amount;

	public void setSender(User sender){
		this.Sender = sender;
	}
	public User getSender(){
		return this.Sender;
	}

	public User getRecipient() {
		return Recipient;
	}
	public void setRecipient(User recipient){
		Recipient = recipient;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Category getType(){
		return this.category;
	}

	public void setAmount(int amount) {
		if (this.category == Category.OUTCOME && amount >= 0)
			System.exit(-1);
		else if (this.category == Category.INCOME && amount < 0)
			System.exit(-1);
		this.amount = amount;
	}

	public int getAmount(){
		return this.amount;
	}

	public String getId(){
		return id;
	}

	@Override
	public String toString(){
		return "Transaction{" +
				"id='" + id + '\'' +
				", sender=" + Sender.getName() +
				", recipient=" + Recipient.getName() +
				", category=" + category +
				", amount=" + amount +
				'}';
	}
}

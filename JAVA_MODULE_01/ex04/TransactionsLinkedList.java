package ex04;

public class TransactionsLinkedList implements TransactionsList {
	Node head;
	Node tail;
	int	listSize;
	 public class Node {
		Transaction data;
		Node next;
		Node(Transaction transaction){
			data = transaction;
			next = null;
		}
	}

	TransactionsLinkedList(){
		head = null;
		tail = null;
		listSize = 0;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		listSize++;
		if (head == null){
			head = new Node(transaction);
			tail = head;
			return;
		}
		tail.next = new Node(transaction);
		tail = tail.next;
	}

	private void removeNode(Node back, Node node){
		if (back == null){
			head = node.next;
		}else
			back.next = node.next;
		listSize--;
	}

	@Override
	public Transaction removeTransactionByUUID(String uuid) {
		if (head == null)
			throw new TransactionNotFoundException("Transaction ID:"+uuid +" Not found");
		Node node = head;
		Node back = null;

		while (node!= null){
			if (node.data.getUuid().equals(uuid)){
				removeNode(back, node);
				return node.data;
			}
			back = node;
			node = node.next;
		}
		throw new TransactionNotFoundException("Transaction ID:"+uuid+" Not Found");
	}

	@Override
	public Transaction[] toArray() {
		Transaction[] transactions = new Transaction[listSize];
		int i = 0;
		for (Node node = head; node != null; node = node.next){
			transactions[i++] = node.data;
		}
		return transactions;
	}
}

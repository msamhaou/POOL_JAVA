package ex05;

public class UsersArrayList implements UsersList{
	private User[] users ;
	private int size;
	public UsersArrayList(){
		users = new User[10];
		size = 0;
	}
	private User[] reallocate(){
		System.out.println("Debug: reallocating");
		int c = users.length + size / 2;
		User[] newCapacity = new User[c];
		for (int i = 0; i < users.length; i++){
			newCapacity[i] = users[i];
		}
		return newCapacity;
	}

	@Override
	public void addUser(User user) {
		if (size >= users.length)
			users = reallocate();
		users[size++] = user;
	}

	@Override
	public User getUserById(int id) {
		for (int i =0; i < size; i++){
			if (users[i].getId() == id)
				return users[i];
		}
		throw new UserNotFoundException("No Such A User Holding This ID");
	}

	@Override
	public User getUserByIndex(int index) {
		if (index < 0 || index >= size)
			throw new IndexOutOfBoundsException("out of range");
		return users[index];
	}

	@Override
	public int UsersNumber() {
		return size;
	}
}

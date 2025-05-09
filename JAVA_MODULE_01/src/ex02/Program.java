package ex02;

public class Program {
	public static void main(String[] args) {
		UsersArrayList usersList = new UsersArrayList();
		User taha = new User();
		taha.setName("taha");
		usersList.addUser(taha);
		System.out.println(usersList.UsersNumber());
		for(int i =0; i < 20; i++){
			User user = new User();
			user.setName("tt"+i);
			System.out.println(user);
			usersList.addUser(user);
		}
		System.out.println(usersList.UsersNumber());

		System.out.println(usersList.getUserById(2));
		System.out.println(usersList.getUserById(4));
		//System.out.println(usersList.getUserById(21));

	}
}

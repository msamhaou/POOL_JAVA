package ex01;

public class Program {
	static public void main(String[] args){
		System.out.println(UserIdsGenerator.getInstance().generateId());
		System.out.println(UserIdsGenerator.getInstance().generateId());
		User u = new User();
		System.out.println(u);
	}
}

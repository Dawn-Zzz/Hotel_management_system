package View;

public class Main {
	public static void main(String[] args) {
		Login a = new Login();
		Controller c = new Controller(a);
		new Signup();
	}
}

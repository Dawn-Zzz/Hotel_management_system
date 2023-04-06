package View;

//import java.awt.event.ActionListener;

public class Controller {
	private Login view;

	public Controller(Login view) {
		super();
		this.view = view;
		this.view.addLoginAction(new LoginActionListener(view));
//		this.view.switchSignUpWD(new LoginActionListener(view));
	}
}

package test;

import java.util.ArrayList;

import DAO.UserDAO;
import model.UserModel;

public class TestDAO {
	public static void main(String[] args) {
		ArrayList<UserModel> arr = UserDAO.getInstance().selectAll();
		for (UserModel userModel : arr) {
			System.out.println(userModel.getUserName());
			System.out.println(userModel.getPassword());
		}
	}
}

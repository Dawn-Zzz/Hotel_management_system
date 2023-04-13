package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDatabase;
import model.UserModel;

public class UserDAO {
	public static UserDAO getInstance () {
		return new UserDAO();
	}
	
	public int insert(String userName, String userPassword) {
		int result = 0;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO `user`(userName,userPassWord) VALUES (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPassword);
			result = preparedStatement.executeUpdate();
			ConnectDatabase.Disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<UserModel> selectAll () {
		ArrayList<UserModel> arrResult = new ArrayList<>();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * FROM `user`";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userName = resultSet.getString("userName");
				String userPassword = resultSet.getString("userPassword");
				UserModel userModel = new UserModel(userName, userPassword);
				arrResult.add(userModel);
			}
			ConnectDatabase.Disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrResult;
	}
}

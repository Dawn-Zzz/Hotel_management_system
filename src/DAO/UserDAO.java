package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDatabase;
import model.User;

public class UserDAO {
	public static UserDAO getInstance () {
		return new UserDAO();
	}
	
	public int insert(String userName, String userPassword) {
		int result = 0;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO `user`(ID,Pass) VALUES (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, userName);
			preparedStatement.setString(2, userPassword);
			result = preparedStatement.executeUpdate();
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<User> selectAll () {
		ArrayList<User> arrResult = new ArrayList<>();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * FROM `user`";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userName = resultSet.getString("ID");
				String userPassword = resultSet.getString("Pass");
				User userModel = new User(userName, userPassword);
				arrResult.add(userModel);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrResult;
	}
}

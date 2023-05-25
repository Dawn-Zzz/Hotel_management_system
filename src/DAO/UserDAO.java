package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDatabase;
import model.Staff;
import model.User;

public class UserDAO {
	public static UserDAO getInstance () {
		return new UserDAO();
	}
	
	public int insert(String userName, String userPassword) {
		int result = 0;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO NguoiDung(TaiKhoan,MatKhau) VALUES (?,?)";
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
			String sql = "SELECT * FROM NguoiDung";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String userName = resultSet.getString("TaiKhoan");
				String userPassword = resultSet.getString("MatKhau");
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
	
	public Staff getUserByID (String id) {
		Staff staff = null;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * "
					+ "FROM NhanVien nv "
					+ "INNER JOIN NguoiDung u ON nv.TaiKhoan = u.TaiKhoan "
					+ "WHERE u.TaiKhoan LIKE ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String idStaff = resultSet.getString("MaNhanVien");
				String nameStaff = resultSet.getString("TenNhanVien");
				String role = resultSet.getString("Quyen");
				staff = new Staff(nameStaff,idStaff,null,null,role);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return staff;
	}
}

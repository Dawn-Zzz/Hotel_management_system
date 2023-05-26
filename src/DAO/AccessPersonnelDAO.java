package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Date;

import database.ConnectDatabase;
import model.AccessPersonnel;
import model.Staff;
import model.User;

public class AccessPersonnelDAO {
	public static AccessPersonnelDAO getInstance () {
		return new AccessPersonnelDAO();
	}
	public void setAccessPersonnelByAccount (String acc) {
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * "
					+ "FROM NhanVien nv "
					+ "INNER JOIN NguoiDung u ON nv.TaiKhoan = u.TaiKhoan "
					+ "WHERE u.TaiKhoan LIKE ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, acc);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String idStaff = resultSet.getString("CCCD");
				String nameStaff = resultSet.getString("TenNhanVien");
				String phoneStaff = resultSet.getString("SoDienThoai");
				Date birth = resultSet.getDate("NgaySinh");
				String title = resultSet.getString("ChucDanh");
				String pass = resultSet.getString("MatKhau");
				AccessPersonnel.getInstance().setStaff(new Staff(nameStaff,idStaff,phoneStaff,birth,title));
				AccessPersonnel.getInstance().setUser(new User(acc, pass));
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public int selectLevel(String taikhoan, String matkhau) {
		int level = 0;
		try {
		    Connection connection = ConnectDatabase.connection();
		    String sql = "SELECT Quyen FROM hotel_management.nguoidung WHERE TaiKhoan = ? AND MatKhau = ?";
		    PreparedStatement preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setString(1, taikhoan);
		    preparedStatement.setString(2, matkhau);
		    ResultSet resultSet = preparedStatement.executeQuery();
		    if (resultSet.next()) {
		        level = resultSet.getInt("Quyen");
		    }
		    ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return level;
	}
}

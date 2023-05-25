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
}

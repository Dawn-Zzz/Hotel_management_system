package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;
import model.User;

public class GuestDAO {
	public static GuestDAO getInstance () {
		return new GuestDAO();
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT kh.CCCD, kh.LoaiKhachHang, kh.TenKhachHang, kh.SoDienThoai, ptp.ThoiGianNhanPhong  FROM KhachHang kh "
					+ "INNER JOIN PhieuThuePhong ptp ON kh.CCCD = ptp.CCCD";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idGuest = resultSet.getString("CCCD");
				String typeGuest = resultSet.getString("LoaiKhachHang");
				String nameGuest = resultSet.getString("TenKhachHang");
				String phoneGuest = resultSet.getString("SoDienThoai");
				Timestamp timestamp = resultSet.getTimestamp("ThoiGianNhanPhong");

				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
				String timeCheckIn = timestamp.toLocalDateTime().format(formatter);
				Object[] object = {nameGuest,idGuest,typeGuest,timeCheckIn,phoneGuest};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
}

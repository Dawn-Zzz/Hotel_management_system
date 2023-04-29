package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;

public class BillDAO {
	public static BillDAO getInstance () {
		return new BillDAO();
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT kh.MaKhachHang, kh.LoaiKhachHang, kh.TenKhachHang, kh.SoDienThoai, ptp.ThoiGianNhanPhong  FROM KhachHang kh "
					+ "INNER JOIN PhieuThuePhong ptp ON kh.MaKhachHang = ptp.MaKhachHang";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idGuest = resultSet.getString("MaKhachHang");
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

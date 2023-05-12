package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;
import model.User;

public class ReservationDAO {
	public static ReservationDAO getInstance () {
		return new ReservationDAO();
	}
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT kh.TenKhachHang, ptp.MaPhong, ptp.HinhThucThue, ptp.ThoiGianNhanPhong, ptp.ThoiGianTraPhong, ptp.SoNguoiO "
					+ "FROM phieuthuephong ptp "
					+ "INNER JOIN khachhang kh ON ptp.CCCD = kh.CCCD;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String guestName = resultSet.getString("TenKhachHang");
				String room = resultSet.getString("MaPhong");
				String rentalType = resultSet.getString("HinhThucThue");
				String checkIn = resultSet.getString("ThoiGianNhanPhong");
				String checkOut = resultSet.getString("ThoiGianTraPhong");
				String roomOccupancy = resultSet.getString("SoNguoiO");
				Object[] object = {guestName, room,rentalType,checkIn,checkOut,roomOccupancy};
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

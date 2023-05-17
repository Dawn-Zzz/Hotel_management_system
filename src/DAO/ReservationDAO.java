package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;
import model.Reservation;
import model.Room;

public class ReservationDAO {
	public static ReservationDAO getInstance () {
		return new ReservationDAO();
	}
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT kh.TenKhachHang, ptp.MaPhong, ptp.HinhThucThue, ptp.ThoiGianNhanPhong, ptp.ThoiGianTraPhong, ptp.SoNguoiO, ptp.HienTrang "
					+ "FROM phieuthuephong ptp "
					+ "INNER JOIN khachhang kh ON ptp.MaKhachHang = kh.MaKhachHang;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String guestName = resultSet.getString("TenKhachHang");
				String room = resultSet.getString("MaPhong");
				String rentalType = resultSet.getString("HinhThucThue");
				String checkIn = resultSet.getString("ThoiGianNhanPhong");
				String checkOut = resultSet.getString("ThoiGianTraPhong");
				String roomOccupancy = resultSet.getString("SoNguoiO");
				String status = resultSet.getString("HienTrang");
				Object[] object = {guestName, room,rentalType,checkIn,checkOut,roomOccupancy,status};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public ArrayList<Reservation> getReservationNotCheckInByIDRoom (String id) {
		ArrayList<Reservation> arrResult = new ArrayList<>();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT *"
					+ "FROM khachhang kh "
					+ "INNER JOIN phieuthuephong ptp ON kh.MaKhachHang = ptp.MaKhachHang "
					+ "INNER JOIN phong p ON ptp.MaPhong = p.MaPhong "
					+ "WHERE p.MaPhong LIKE ? AND (ptp.HienTrang LIKE N'Đã nhận phòng' OR ptp.HienTrang LIKE N'Chưa nhận phòng') ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String statusRoom = resultSet.getString("p.HienTrang");
				Timestamp checkIn = resultSet.getTimestamp("ThoiGianNhanPhong");
				Timestamp checkOut = resultSet.getTimestamp("ThoiGianTraPhong");
				Reservation reservation = new Reservation(new Room(id,statusRoom),checkIn,checkOut);
				arrResult.add(reservation);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrResult;
	}
}

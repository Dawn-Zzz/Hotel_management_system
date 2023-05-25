package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDate;
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
	
	public int insert(String idGuest, String rentalType, String idRoom, Timestamp checkin, Timestamp checkout, int guestQuantity, Double deposit) {
		int result = 0;
		try {
			String id = GuestDAO.getInstance().getMaKhachHangByCCCD(idGuest);
			Connection connection = ConnectDatabase.connection();

			String sql = "INSERT INTO phieuthue (MaKhachHang,HinhThucThue,MaPhong,ThoiGianNhanPhong,ThoiGianTraPhong,SoNguoiO,NgayLap,HienTrang,MaNhanVien) VALUES (?,?,?,?,?,?,?,?,?)";

			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, rentalType);
			preparedStatement.setString(3, idRoom);
			preparedStatement.setTimestamp(4, checkin);
			preparedStatement.setTimestamp(5, checkout);
			preparedStatement.setInt(6, guestQuantity);
			preparedStatement.setDate(7, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setString(8, "Chưa nhận phòng");
			preparedStatement.setInt(9, 1);
			result = preparedStatement.executeUpdate();
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT ptp.MaPhieu, kh.TenKhachHang, ptp.MaPhong, ptp.HinhThucThue, ptp.ThoiGianNhanPhong, ptp.ThoiGianTraPhong, ptp.SoNguoiO, ptp.HienTrang "

					+ "FROM phieuthue ptp "

					+ "INNER JOIN khachhang kh ON ptp.MaKhachHang = kh.MaKhachHang;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idReservation = resultSet.getString("MaPhieu");
				String guestName = resultSet.getString("TenKhachHang");
				String room = resultSet.getString("MaPhong");
				String rentalType = resultSet.getString("HinhThucThue");
				String checkIn = resultSet.getString("ThoiGianNhanPhong");
				String checkOut = resultSet.getString("ThoiGianTraPhong");
				String roomOccupancy = resultSet.getString("SoNguoiO");
				String status = resultSet.getString("HienTrang");
				Object[] object = {idReservation,guestName, room,rentalType,checkIn,checkOut,roomOccupancy,status};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public String selectName () {
		String name = null;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT TenKhachHang  FROM KhachHang kh ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				name = resultSet.getString("TenKhachHang");
//				Timestamp timestamp = resultSet.getTimestamp("NgaySinh");
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				String birth = timestamp.toLocalDateTime().format(formatter);
//				Object[] object = {nameGuest};
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return name;
	}
	
	public ArrayList<Reservation> getReservationNotCheckInByIDRoom (String id) {
		ArrayList<Reservation> arrResult = new ArrayList<>();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * "
					+ "FROM phieuthue ptp "
					+ "INNER JOIN phong p ON ptp.MaPhong = p.MaPhong "
					+ "WHERE p.MaPhong = ? "
					+ "    AND (p.HienTrang = '2' OR (ptp.HienTrang LIKE N'Chưa nhận phòng' OR ptp.HienTrang LIKE N'Đã nhận phòng')) ";
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
	
	public int updateStatusReservation (String id, String status) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();

	        String sql = "UPDATE phieuthue SET HienTrang = ? WHERE MaPhieu = ?";

	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, status);
	        preparedStatement.setString(2, id);
	        result = preparedStatement.executeUpdate();
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}

package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.sql.Types;
import java.time.LocalDate;
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
	
	public int insert(String idGuest, String rentalType, String idRoom, Timestamp checkin, Timestamp checkout, int guestQuantity, Double deposit) {
		int result = 0;
		try {
			String id = getMaKhachHangByCCCD(idGuest);
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO phieuthuephong (MaKhachHang,HinhThucThue,MaPhong,ThoiGianNhanPhong,ThoiGianTraPhong,SoNguoiO,TienCoc,NgayLap,HienTrang,MaNhanVien) VALUES (?,?,?,?,?,?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			preparedStatement.setString(2, rentalType);
			preparedStatement.setString(3, idRoom);
			preparedStatement.setTimestamp(4, checkin);
			preparedStatement.setTimestamp(5, checkout);
			preparedStatement.setInt(6, guestQuantity);
			if (deposit != null) {
	            preparedStatement.setDouble(7, deposit);
	        } else {
	            preparedStatement.setNull(7, Types.DOUBLE);
	        }
			preparedStatement.setDate(8, java.sql.Date.valueOf(LocalDate.now()));
			preparedStatement.setString(9, "Chưa nhận phòng");
			preparedStatement.setInt(10, 1);
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
					+ "FROM phieuthuephong ptp "
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
	
	public int updateStatusReservation (String id, String status) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "UPDATE PhieuThuePhong SET HienTrang = ? WHERE MaPhieu = ?";
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
	
	public static String getMaKhachHangByCCCD(String cccd) {
	    String id = "";
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT MaKhachHang FROM KhachHang WHERE CCCD = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, cccd);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            id = resultSet.getString("MaKhachHang");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}
}

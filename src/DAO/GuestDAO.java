package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
//import java.sql.Timestamp;
//import java.time.LocalDateTime;
//import java.time.format.DateTimeFormatter;
import model.Guest;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;

public class GuestDAO {
	public static GuestDAO getInstance () {
		return new GuestDAO();
	}
	
	public int insert(String ID, String name, Date birth, String phoneNumber, String type) {
		int result = 0;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO KhachHang (CCCD,TenKhachHang,NgaySinh,SoDienThoai,LoaiKhachHang) VALUES (?,?,?,?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, ID);
			preparedStatement.setString(2, name);
			preparedStatement.setDate(3, birth);
			preparedStatement.setString(4, phoneNumber);
			preparedStatement.setString(5, type);
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
			String sql = "SELECT *  FROM KhachHang kh ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idGuest = resultSet.getString("CCCD");
				String typeGuest = resultSet.getString("LoaiKhachHang");
				String nameGuest = resultSet.getString("TenKhachHang");
				String phoneGuest = resultSet.getString("SoDienThoai");
				Date birth = resultSet.getDate("NgaySinh");
//				Timestamp timestamp = resultSet.getTimestamp("NgaySinh");
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				String birth = timestamp.toLocalDateTime().format(formatter);
				Object[] object = {nameGuest,idGuest,typeGuest,birth,phoneGuest};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public Guest getGuestById(String id) {
	    Guest guest = null;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT * FROM KhachHang WHERE CCCD = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            String name = resultSet.getString("TenKhachHang");
	            Date birth = resultSet.getDate("NgaySinh");
	            String phoneNumber = resultSet.getString("SoDienThoai");
	            String type = resultSet.getString("LoaiKhachHang");
	            guest = new Guest(id, name, phoneNumber, birth, type);
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return guest;
	}
	
	public int updateGuest (String id, String name, Date birth, String phoneNumber, String type) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "UPDATE KhachHang SET TenKhachHang = ?, NgaySinh = ?, SoDienThoai = ?, LoaiKhachHang = ? WHERE CCCD = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, name);
	        preparedStatement.setDate(2, birth);
	        preparedStatement.setString(3, phoneNumber);
	        preparedStatement.setString(4, type);
	        preparedStatement.setString(5, id);
	        result = preparedStatement.executeUpdate();
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}

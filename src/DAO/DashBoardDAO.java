package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DecimalFormat;
import java.time.LocalDate;

import database.ConnectDatabase;
import model.Staff;

public class DashBoardDAO {
	public static DashBoardDAO getInstance () {
		return new DashBoardDAO();
	}
	
//	public int insert(String name, String staffID, String phoneNumber, String role, Date birth) {
//		int result = 0;
//		try {
//			Connection connection = ConnectDatabase.connection();
//			String sql = "INSERT INTO NhanVien (TenNhanVien,MaNhanVien,SoDienThoai,MaChucDanh,NgaySinh) VALUES (?,?,?,?,?)";
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			preparedStatement.setString(1, name);
//			preparedStatement.setString(2, staffID);
//			preparedStatement.setString(3, phoneNumber);
//			preparedStatement.setString(4, role);
//			preparedStatement.setDate(5, birth);
//			result = preparedStatement.executeUpdate();
//			ConnectDatabase.disconnection(connection);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Loi1");
//		}
//		return result;
//	}
	
//	public JTable selectAll (JTable table) {
//		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
//		try {
//			Connection connection = ConnectDatabase.connection();
//			String sql = "SELECT nv.TenNhanVien, nv.CCCD, nv.SoDienThoai, cd.TenChucDanh "
//					+ "FROM NhanVien nv INNER JOIN ChucDanh cd "
//					+ "ON cd.MaChucDanh = nv.MaChucDanh" ;
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet resultSet = preparedStatement.executeQuery();
//			while (resultSet.next()) {
//				String nameStaff = resultSet.getString("TenNhanVien");
//				String idStaff = resultSet.getString("CCCD");
//				String phoneStaff = resultSet.getString("SoDienThoai");
//				String roleStaff = resultSet.getString("TenChucDanh");
////				Timestamp timestamp = resultSet.getTimestamp("NgaySinh");
////				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
////				String birth = timestamp.toLocalDateTime().format(formatter);
//				Object[] object = {nameStaff,idStaff,phoneStaff,roleStaff};
//				defaultTableModel.addRow(object);
//			}
//			ConnectDatabase.disconnection(connection);
//		} catch (SQLException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//			System.out.println("Loi");
//		}
//		return table;
//	}
	
	public String selectAll() {
		String selectTotal = new String();
//		list = new ArrayList<String>();
		try {
			Connection connection = ConnectDatabase.connection();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT SUM(hd.TienPhong + IFNULL(hd.TienDichVu, 0)) AS Tong FROM hotel_management.hoadon hd;" );
			while (resultSet.next()) {
				DecimalFormat df = new DecimalFormat("#,###");
				selectTotal = df.format(resultSet.getInt("Tong"));
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loi");
		}
		return selectTotal;
	}
	
	public String selectWeek() {
		String selectTotal = new String();
//		list = new ArrayList<String>();
		try {
			Connection connection = ConnectDatabase.connection();
			Statement statement = connection.createStatement();
			String sql = "SELECT SUM(hd.TienPhong + IFNULL(hd.TienDichVu, 0)) AS Tong FROM hotel_management.hoadon hd "
					+ "WHERE NgayLapHoaDon >= DATE_SUB(CURDATE(), INTERVAL 1 WEEK) "
					+ "AND NgayLapHoaDon <= CURDATE()";
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				DecimalFormat df = new DecimalFormat("#,###");
				selectTotal = df.format(resultSet.getInt("Tong"));
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loiweek");
		}
		return selectTotal;
	}
	

	
	public int selectCheckIn(LocalDate today) {
	    int checkIn = 0;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT COUNT(*) AS checkIn FROM PhieuThue WHERE DATE(ThoiGianNhanPhong) = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setDate(1, java.sql.Date.valueOf(today));
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            checkIn = resultSet.getInt("checkIn");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Lỗi checkIn");
	    }
	    return checkIn;
	}
	public int selectCheckOut(LocalDate today) {
	    int checkIn = 0;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT COUNT(*) AS checkOut FROM PhieuThue WHERE DATE(ThoiGianTraPhong) = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setDate(1, java.sql.Date.valueOf(today));
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            checkIn = resultSet.getInt("checkOut");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Lỗi checkOut");
	    }
	    return checkIn;
	}
	public int selectBookRoom(LocalDate today) {
	    int checkIn = 0;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT COUNT(*) AS bookRoom FROM PhieuThue WHERE DATE(NgayLap) = ?";
	        PreparedStatement statement = connection.prepareStatement(sql);
	        statement.setDate(1, java.sql.Date.valueOf(today));
	        ResultSet resultSet = statement.executeQuery();
	        while (resultSet.next()) {
	            checkIn = resultSet.getInt("bookRoom");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("Lỗi bookRoom");
	    }
	    return checkIn;
	}

	
	public Staff getStaffById(String id) {
	    Staff staff = null;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT * FROM NhanVien WHERE MaNhanVien = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            String name = resultSet.getString("TenNhanVien");
	            Date birth = resultSet.getDate("NgaySinh");
	            String idStaff = resultSet.getString("CCCD");
	            String phoneNumber = resultSet.getString("SoDienThoai");
	            String role = resultSet.getString("MaChucDanh");
	            staff = new Staff(idStaff, name, phoneNumber, birth, role);
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return staff;
	}
	
	public int updateStaff (String name, String staffID, String phoneNumber, String role, Date birth) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "UPDATE NhanVien SET TenNhanVien = ?, NgaySinh = ?, SoDienThoai = ?, MaChucDanh = ? WHERE CCCD = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, name);
			preparedStatement.setString(2, staffID);
			preparedStatement.setString(3, phoneNumber);
			preparedStatement.setString(4, role);
			preparedStatement.setDate(5, birth);
	        result = preparedStatement.executeUpdate();
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}

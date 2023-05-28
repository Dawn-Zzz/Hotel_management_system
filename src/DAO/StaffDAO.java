package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

//import com.mysql.cj.xdevapi.Statement;

import database.ConnectDatabase;
import model.Staff;

public class StaffDAO {
	public static StaffDAO getInstance () {
		return new StaffDAO();
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT nv.TenNhanVien, nv.CCCD, nv.SoDienThoai, nv.MaNhanVien, nv.ChucDanh, nv.NgaySinh "
					+ "FROM NhanVien nv;";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nameStaff = resultSet.getString("TenNhanVien");
				String cccdStaff = resultSet.getString("CCCD");
				String phoneStaff = resultSet.getString("SoDienThoai");
				String roleStaff = resultSet.getString("ChucDanh");
				int idStaff = resultSet.getInt("MaNhanVien");
				Date birthStaff = resultSet.getDate("NgaySinh");
//				Timestamp timestamp = resultSet.getTimestamp("NgaySinh");
//				DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
//				String birth = timestamp.toLocalDateTime().format(formatter);
				Object[] object = {idStaff,nameStaff,cccdStaff,phoneStaff,birthStaff,roleStaff};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loi");
		}
		return table;
	}
	
	public List<String> selectName(List<String> list) {
		list = new ArrayList<String>();
		try {
			Connection connection = ConnectDatabase.connection();
//			String sql = "SELECT TenNhanVien FROM hotel_management.NhanVien"
//					+ "WHERE MaNhanVien > 1" ;
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet resultSet = preparedStatement.executeQuery();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT TenNhanVien FROM hotel_management.NhanVien" );
			while (resultSet.next()) {
				String nameStaff = resultSet.getString("TenNhanVien");
				list.add(nameStaff);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loi");
		}
		return list;
	}
	
	public int selectIndexRole(int a) {
		try {
			Connection connection = ConnectDatabase.connection();
//			String sql = "SELECT TenNhanVien FROM hotel_management.NhanVien"
//					+ "WHERE MaNhanVien > 1" ;
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet resultSet = preparedStatement.executeQuery();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT MaChucDanh FROM hotel_management.ChucDanh " );
//			while (resultSet.next()) {
				int indexStaff = resultSet.getInt("MaChucDanh");
				a = indexStaff;
//			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loi");
		}
		return a;
	}
	
	public List<String> selectIndex(List<String> list) {
		list = new ArrayList<String>();
		try {
			Connection connection = ConnectDatabase.connection();
//			String sql = "SELECT TenNhanVien FROM hotel_management.NhanVien"
//					+ "WHERE MaNhanVien > 1" ;
//			PreparedStatement preparedStatement = connection.prepareStatement(sql);
//			ResultSet resultSet = preparedStatement.executeQuery();
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery("SELECT TenChucDanh FROM hotel_management.NhanVien WHERE MaChucDanh != 'CD001'" );
			while (resultSet.next()) {
				String roleStaff = resultSet.getString("ChucDanh");
				list.add(roleStaff);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			System.out.println("Loi");
		}
		return list;
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
	            String role = resultSet.getString("TenChucDanh");
	            staff = new Staff(idStaff, name, phoneNumber, birth, role);
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return staff;
	}
	
	public int updateStaff (String name, String phoneNumber, String role, Date birth, String cccd) {
		int result = 0;
		try { 
		    Connection connection = ConnectDatabase.connection();
		    String sql = "UPDATE NhanVien nv "
		        + "SET TenNhanVien = ?, SoDienThoai = ?, NgaySinh = ?, "
		        + "nv.ChucDanh = ? "
		        + "WHERE nv.CCCD = ?";
		    PreparedStatement preparedStatement = connection.prepareStatement(sql);
		    preparedStatement.setString(1, name);
		    preparedStatement.setString(2, phoneNumber);
		    preparedStatement.setDate(3, birth);
		    if (role != null) {
		        preparedStatement.setString(4, role);
		    } else {
		        System.out.println("Lỗi");
		    }
		    preparedStatement.setString(5, cccd);
//		    preparedStatement.setInt(6, id);
		    result = preparedStatement.executeUpdate();
		    ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
		    e.printStackTrace();
		}
		return result;
	}
	
	public String getMaNhanVienByCCCD(String cccd) {
	    String id = "";
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT MaNhanVien FROM NhanVien WHERE CCCD = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, cccd);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            id = resultSet.getString("MaNhanVien");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}
	
	public int insert(String name, String user) {
		int result = 0;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "INSERT INTO NhanVien (TenNhanVien, TaiKhoan) VALUES (?,?)";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, name);
			preparedStatement.setString(2, user);
			result = preparedStatement.executeUpdate();
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
}

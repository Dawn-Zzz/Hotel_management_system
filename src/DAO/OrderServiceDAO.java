package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;

public class OrderServiceDAO {
	public static OrderServiceDAO getInstance () {
		return new OrderServiceDAO();
	}
	
	public JTable selectOrderServiceByID (String id, JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		DecimalFormat df = new DecimalFormat("#,###");
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT dv.TenDichVu, hddv.SoLuong, dv.GiaDichVu "
					+ "FROM hoadon hd "
					+ "INNER JOIN chitiethoadondichvu hddv ON hd.MaHoaDon = hddv.MaHoaDon "
					+ "INNER JOIN dichvu dv ON hddv.MaDichVu = dv.MaDichVu "
					+ "WHERE hd.MaHoaDon = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nameService = resultSet.getString("TenDichVu");
				Double price = (double) resultSet.getFloat("GiaDichVu");
//				Service service = new Service(nameService,total);
				int amount = resultSet.getInt("SoLuong");
				Object[] object = {nameService,amount,df.format(price),df.format(amount*price)};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public String getMaDichVuByNameService(String name) {
	    String id = "";
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT MaDichVu FROM DichVu WHERE TenDichVu = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, name);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            id = resultSet.getString("MaDichVu");
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return id;
	}
	
	public int insertOrderService (String idBill, String idService, int amount) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "INSERT INTO ChiTietHoaDonDichVU(MaHoaDon,MaDichVu,SoLuong) VALUES (?,?,?)";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, idBill);
	        preparedStatement.setString(2, idService);
	        preparedStatement.setInt(3, amount);
	        result = preparedStatement.executeUpdate();
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}

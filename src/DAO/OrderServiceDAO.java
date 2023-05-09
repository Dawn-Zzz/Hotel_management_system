package DAO;

import java.sql.Connection;
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
					+ "INNER JOIN hoadondichvu hddv ON hd.MaHoaDon = hddv.MaHoaDon "
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
}

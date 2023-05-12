package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;

public class EquipmentDAO {
	public static EquipmentDAO getInstance () {
		return new EquipmentDAO();
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT p.MaPhong, tb.TenThietBi, pctb.HienTrang FROM PhongCoThietBi pctb "
					+ "INNER JOIN Phong p ON p.MaPhong = pctb.MaPhong "
					+ "INNER JOIN ThietBi tb ON tb.MaThietBi = pctb.MaThietBi ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idRoom = resultSet.getString("MaPhong");
				String nameEquipment = resultSet.getString("TenThietBi");
				//String idEquipment = resultSet.getString("MaThietBi");
				String statusEquipment = resultSet.getString("HienTrang");
				Object[] object = {idRoom,nameEquipment,statusEquipment};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public int updateStatusEquipment (String id, String nameService, String type) {
		int result = 0;
		try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "UPDATE PhongCoThietBi pctb "
	        		+ "INNER JOIN ThietBi tb ON tb.MaThietBi = pctb.MaThietBi "
	        		+ "SET pctb.HienTrang = ? "
	        		+ "WHERE (pctb.MaPhong = ? AND tb.TenThietBi = ?) ";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, type);
	        preparedStatement.setString(2, id);
	        preparedStatement.setString(3, nameService);
	        result = preparedStatement.executeUpdate();
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
		return result;
	}
}

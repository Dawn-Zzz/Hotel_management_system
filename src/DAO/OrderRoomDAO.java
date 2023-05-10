package DAO;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import database.ConnectDatabase;

public class OrderRoomDAO {
	public static OrderRoomDAO getInstance () {
		return new OrderRoomDAO();
	}
	
	public JTable selectOrderRoomByID (String id, JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		DecimalFormat df = new DecimalFormat("#,###");
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT p.MaPhong, ptp.HinhThucThue, ptp.ThoiGianNhanPhong, ptp.ThoiGianTraPhong, ptp.SoNguoiO, "
					+ "		CASE ptp.HinhThucThue "
					+ "            WHEN N'Đêm' THEN lp.GiaQuaDem "
					+ "            WHEN N'Ngày' THEN lp.GiaTheoNgay "
					+ "            WHEN N'Giờ' THEN lp.GiaTheoGio "
					+ "            ELSE NULL "
					+ "       END AS Gia "
					+ "FROM hoadon hd "
					+ "INNER JOIN hoadonphong hdp ON hd.MaHoaDon = hdp.MaHoaDon "
					+ "INNER JOIN phong p ON hdp.MaPhong = p.MaPhong "
					+ "INNER JOIN loaiphong lp ON p.MaLoaiPhong = lp.MaLoaiPhong "
					+ "INNER JOIN phieuthuephong ptp ON p.MaPhong = ptp.MaPhong "
					+ "WHERE hd.MaHoaDon = ? ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String nameRoom = resultSet.getString("MaPhong");
				String rentalOption = resultSet.getString("HinhThucThue");
				String checkin = resultSet.getTimestamp("ThoiGianNhanPhong").toLocalDateTime().format(formatter);
				String checkout = resultSet.getTimestamp("ThoiGianTraPhong").toLocalDateTime().format(formatter);
				Double price = (double) resultSet.getFloat("Gia");
				int amount = resultSet.getInt("SoNguoiO");
				Object[] object = {nameRoom,rentalOption,checkin,checkout,df.format(price)};
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

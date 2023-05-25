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
import model.Bill;

public class BillDAO {
	public static BillDAO getInstance () {
		return new BillDAO();
	}
	
	public JTable selectAll (JTable table) {
		DefaultTableModel defaultTableModel = (DefaultTableModel) table.getModel();
		try {
			Connection connection = ConnectDatabase.connection();

			String sql = "SELECT hd.MaHoaDon, hd.NgayLapHoaDon, kh.TenKhachHang, (hd.TienPhong + IFNULL(hd.TienDichVu, 0)) AS TongTien, nv.TenNhanVien "

					+ "FROM HoaDon hd "
					+ "INNER JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang "
					+ "INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien ";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String idBill = resultSet.getString("MaHoaDon");
				Date dateBill = resultSet.getDate("NgayLapHoaDon");
				String nameGuest = resultSet.getString("TenKhachHang");
				String nameStaff = resultSet.getString("TenNhanVien");
				DecimalFormat df = new DecimalFormat("#,###");
				String total = df.format(resultSet.getFloat("TongTien"));
				
				Object[] object = {idBill,nameGuest,dateBill,total,nameStaff};
				defaultTableModel.addRow(object);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return table;
	}
	
	public Bill selectBillByID (String id) {
		Bill bill = null;
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT kh.TenKhachHang, nv.TenNhanVien, (hd.TongTienPhong + IFNULL(hd.TongTienDichVu, 0)) AS TongTien, hd.TongTienPhong, hd.TongTienDichVu, hd.NgayLapHoaDon FROM HoaDon hd "
					+ "INNER JOIN KhachHang kh ON kh.MaKhachHang = hd.MaKhachHang "
					+ "INNER JOIN NhanVien nv ON nv.MaNhanVien = hd.MaNhanVien "
					+ "WHERE hd.MaHoaDon = ?";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, id);
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				String nameGuest = resultSet.getString("TenKhachHang");
				String nameStaff = resultSet.getString("TenNhanVien");
				Double total = (double) resultSet.getFloat("TongTien");
				Double totalRoom = (double) resultSet.getFloat("TongTienPhong");
				Double totalService = (double) resultSet.getFloat("TongTienDichVu");
				Date dateBill = resultSet.getDate("NgayLapHoaDon");
				bill = new Bill(nameGuest, nameStaff,total, totalRoom, totalService, dateBill);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bill;
	}
}
package DAO;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import database.ConnectDatabase;
import model.Guest;
import model.Room;

public class RoomDAO {
	public static RoomDAO getInstance () {
		return new RoomDAO();
	}
	
	public ArrayList<Room> selectAll () {
		ArrayList<Room> arrResult = new ArrayList<>();
		try {
			Connection connection = ConnectDatabase.connection();
			String sql = "SELECT * FROM Phong";
			PreparedStatement preparedStatement = connection.prepareStatement(sql);
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {
				String id = resultSet.getString("MaPhong");
				String status = resultSet.getString("HienTrang");
				Room room = new Room(id,status);
				arrResult.add(room);
			}
			ConnectDatabase.disconnection(connection);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return arrResult;
	}
	
	public Room getRoomByID (String id) {
		Room room = null;
	    try {
	        Connection connection = ConnectDatabase.connection();
	        String sql = "SELECT * FROM Phong WHERE MaPhong = ?";
	        PreparedStatement preparedStatement = connection.prepareStatement(sql);
	        preparedStatement.setString(1, id);
	        ResultSet resultSet = preparedStatement.executeQuery();
	        if (resultSet.next()) {
	            String status = resultSet.getString("HienTrang");
	            room = new Room(id, status);
	        }
	        ConnectDatabase.disconnection(connection);
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return room;
	}
}

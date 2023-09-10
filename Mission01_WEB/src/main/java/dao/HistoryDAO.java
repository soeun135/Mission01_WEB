package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.WifiHistory;

public class HistoryDAO {
	public List<WifiHistory> showHistory() {
		List<WifiHistory> wifiHistoryResult = new ArrayList<>();
			try {
				Class.forName("org.sqlite.JDBC");
			} catch (ClassNotFoundException e) {
				e.printStackTrace();
			}
			
			Connection connection = null;
			PreparedStatement preparedStatement = null;
			ResultSet rs = null;

			String url_value = "jdbc:sqlite:C:\\dev_web\\sqlite-tools-win32-x86-3430000\\wifi.db";

			try {
				connection = DriverManager.getConnection(url_value);

				String sql = "select * from wifi_history";

				preparedStatement = connection.prepareStatement(sql);

				rs = preparedStatement.executeQuery();

				while(rs.next()) {
					WifiHistory historyList = new WifiHistory();
					historyList.setId(rs.getInt("id"));
					historyList.setX(rs.getDouble("x"));
					historyList.setY(rs.getDouble("y"));
					historyList.setMakeDate(rs.getString("make_date"));					
					wifiHistoryResult.add(historyList);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				try {
					if (rs != null && !rs.isClosed()) {
						rs.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (preparedStatement != null && !preparedStatement.isClosed()) {
						preparedStatement.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
				try {
					if (connection != null && !connection.isClosed()) {
						connection.close();
					}
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return wifiHistoryResult;
	}
	
	public void delhistory(int id) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		
		String url_value = "jdbc:sqlite:C:\\dev_web\\sqlite-tools-win32-x86-3430000\\wifi.db";
	
		try {
			connection = DriverManager.getConnection(url_value);
			
			String sql = "delete from wifi_history"
					+ " where id = ?;";
			
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);
			
			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				if (connection != null && !connection.isClosed()) {
					connection.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
			try {
				if (preparedStatement != null && !preparedStatement.isClosed()) {
					preparedStatement.close();
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}
}

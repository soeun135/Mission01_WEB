package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class DistUpdateDAO {
	//controller에서 호출되어 DB를 접근할 dao
	//db 정보를 적어줌.
	public void calDist(double x, double y) {
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
			connection.setAutoCommit(false);
			String sql = "select * from wifi_list";

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
	
			String insertsql = "insert into wifi_history (x, y)"
					+ " values(?,?)";

			preparedStatement = connection.prepareStatement(insertsql);
			preparedStatement.setDouble(1, x);
			preparedStatement.setDouble(2, y);
			
			preparedStatement.executeUpdate();
	
			int i = 0;
			HashMap<String, Double> map = new HashMap<>();
			while (rs.next()) {								
				double x_value = rs.getDouble("x");
				double y_value = rs.getDouble("y");
				
				double dist = distance(x, y, x_value, y_value);
				dist = Math.round(dist * 10000) / 10000.0;
				map.put(rs.getString("manage_no"), dist);
			}
			String updatesql = "update wifi_list set dist= ? where manage_no=?";
			preparedStatement = connection.prepareStatement(updatesql);
			int j = 0;
			for(Map.Entry<String,Double> item : map.entrySet()) {
				preparedStatement.setDouble(1, item.getValue());
				preparedStatement.setString(2, item.getKey());
				
				preparedStatement.addBatch();
				preparedStatement.clearParameters();
				
				if ((j % 1000) == 0) {
					preparedStatement.executeBatch(); //sql문 실행
					preparedStatement.clearBatch(); //배치 클리어
					connection.commit();
				} 
				j++;
			}
			preparedStatement.executeBatch();
			connection.commit();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch(SQLException e1) {
				e1.printStackTrace();
			}
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
	}

	public double distance(double lat1, double lon1, double lat2, double lon2) {
		double R = 6371; // 지구 반지름 (단위: km)
		double dLat = deg2rad(lat2 - lat1);
		double dLon = deg2rad(lon2 - lon1);
		double a = Math.sin(dLat / 2) * Math.sin(dLat / 2)
				+ Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.sin(dLon / 2) * Math.sin(dLon / 2);
		double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
		double distance = R * c; // 두 지점 간의 거리 (단위: km)
		return distance;
	}

	public double deg2rad(double deg) {
		return deg * (Math.PI / 180);
	}
}

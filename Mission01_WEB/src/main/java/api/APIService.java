package api;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class APIService {
	public int apiCnt() {
		int result = 0;
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

			String sql = "select count(*) from wifi_list";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();
			
			result = rs.getInt("count(*)");
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
	                if(preparedStatement != null && !preparedStatement.isClosed()) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            try {
	                if(connection != null && !connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		}
		System.out.println(result);
		return result;
	}

	public void calDist(double x, double y) {
		System.out.println("실행은 되니 ?");
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

			String sql = "select x, y from wifi_list";;

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			while(rs.next()) {
				double x_value = rs.getDouble("x");
				double y_value = rs.getDouble("y");
				
				double dist = distance(x, y, x_value, y_value);
				
				String updatesql = "update wifi_list set dist= ?";
	            preparedStatement = connection.prepareStatement(updatesql);
				preparedStatement.setDouble(1, dist);
				preparedStatement.executeUpdate();
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
	                if(preparedStatement != null && !preparedStatement.isClosed()) {
	                    preparedStatement.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
	            try {
	                if(connection != null && !connection.isClosed()) {
	                    connection.close();
	                }
	            } catch (SQLException e) {
	                e.printStackTrace();
	            }
		}
	}
	public double distance(double lat1,double lon1,double lat2,double lon2) {
		  double R = 6371; // 지구 반지름 (단위: km)
		  double dLat = deg2rad(lat2 - lat1);
		  double dLon = deg2rad(lon2 - lon1);
		  double a = Math.sin(dLat/2) * Math.sin(dLat/2) +
		            Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) *
		            Math.sin(dLon/2) * Math.sin(dLon/2);
		  double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));
		  double distance = R * c; // 두 지점 간의 거리 (단위: km)
		  return distance;
	}
		public double deg2rad(double deg) {
		  return deg * (Math.PI/180);
		}
}

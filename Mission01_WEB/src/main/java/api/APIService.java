package api;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import dto.WifiList;

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
		System.out.println(result);
		return result;
	}

	public @ResponseBody void calDist(@RequestParam("xvalue") double x, @RequestParam("yvalue") double y) {
		System.out.println("실행은 되니 ?");
		System.out.println(x+"되라 제바아아랑러" + y);
		/*try {
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

			String sql = "select * from wifi_list";

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();
			
			String insertsql = "insert into wifi_history (x, y)"
					+ " values(?,?)";
			
			preparedStatement = connection.prepareStatement(insertsql);
			preparedStatement.setDouble(1, x);
			preparedStatement.setDouble(2, y);
			
			preparedStatement.executeUpdate();

			while (rs.next()) {
				double x_value = rs.getDouble("x");
				double y_value = rs.getDouble("y");

//				double dist = distance(x, y, x_value, y_value);
//
//				String updatesql = "update wifi_list set dist= ?";
//				preparedStatement = connection.prepareStatement(updatesql);
//				preparedStatement.setDouble(1, dist);
//				preparedStatement.executeUpdate();
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
		}*/
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

	public List<WifiList> showWifi() {
		List<WifiList> wifiListResult = new ArrayList<>();
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

				String sql = "select * from wifi_list"
						+ " order by dist limit 0,20;";

				preparedStatement = connection.prepareStatement(sql);

				rs = preparedStatement.executeQuery();

				while(rs.next()) {
					WifiList wifiList = new WifiList();
					wifiList.setManageNo(rs.getString("manage_no"));
					 wifiList.setDist(rs.getDouble("dist"));
					wifiList.setGugun(rs.getString("gugun"));
					wifiList.setWifiName(rs.getString("wifi_name"));
					wifiList.setAddress(rs.getString("address"));
					wifiList.setDetailAddress(rs.getString("detail_address"));
					wifiList.setSetFloor(rs.getString("set_floor"));
					wifiList.setSetType(rs.getString("set_type"));
					wifiList.setSetOrgan(rs.getString("set_organ"));
					wifiList.setServiceDivision(rs.getString("service_division"));
					wifiList.setNetworkKind(rs.getString("network_kind"));
					wifiList.setSetYear(rs.getInt("set_year"));
					wifiList.setInOutdoor(rs.getString("in_outdoor"));
					wifiList.setWificonnectEnvirionment(rs.getString("wificonnect_environment"));
					wifiList.setX(rs.getDouble("x"));
					wifiList.setY(rs.getDouble("y"));
					wifiList.setWorkDate(rs.getString("work_date"));
					
					wifiListResult.add(wifiList);
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
			return wifiListResult;
	}
}

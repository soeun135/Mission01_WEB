package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import dto.WifiList;

public class ShowWifiDAO {
	//controller에서 호출되어 DB를 접근할 dao
	//db 정보를 적어줌.
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

	public WifiList findBymanageNo(String no) {
		WifiList wifiList = new WifiList();
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
					+ " where manage_no = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1,no);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
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
		}		return wifiList;
	}
}

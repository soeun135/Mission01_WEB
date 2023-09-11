package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import java.util.ArrayList;

import dto.BookMark;
import dto.BookMarkGroup;
import dto.WifiList;

public class BookmarkDAO {
	// controller에서 호출되어 DB를 접근할 dao
	// db 정보를 적어줌
	public void add(String group, String wifiName){		
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
			String sql = "insert into bookmark_list (bookmark_name, wifi_name)"
					+ " values(?,?)";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, group);
			preparedStatement.setString(2,wifiName);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
			try {
				connection.rollback();
			} catch (SQLException e1) {
				e1.printStackTrace();
			}
		} finally {
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

	public List<BookMark> show() {
		List<BookMark> bookMarkList = new ArrayList<>();
		
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

			String sql = "select * from bookmark_list";

			preparedStatement = connection.prepareStatement(sql);

			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				BookMark bookMark = new BookMark();
				bookMark.setId(rs.getInt("id"));
				bookMark.setBookmarkName(rs.getString("bookmark_name"));
				bookMark.setWifiName(rs.getString("wifi_name"));
				bookMark.setMakeDate(rs.getString("make_date"));
				bookMarkList.add(bookMark);
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
		return bookMarkList;
	}

	public void delete(int id) {
		
	}

	public BookMark findById(int id) {
		BookMark bookMark = new BookMark();
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

			String sql = "select * from bookmark_list"
					+ " where id = ?";

			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1,id);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				bookMark.setId(rs.getInt("id"));
				bookMark.setBookmarkName(rs.getString("bookmark_name"));
				bookMark.setWifiName(rs.getString("wifi_name"));
				bookMark.setMakeDate(rs.getString("make_date"));

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
		return bookMark;
	}

	public void deleteset(int id) {
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
			String sql = "delete from bookmark_list "
					+ " where id=?";
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setInt(1, id);

			preparedStatement.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
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
}

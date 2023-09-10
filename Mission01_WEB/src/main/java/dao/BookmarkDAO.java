package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.ArrayList;

import dto.BookMark;

public class BookmarkDAO {
	// controller에서 호출되어 DB를 접근할 dao
	// db 정보를 적어줌
	public List<BookMark> add(){
		List<BookMark> bookmarkList = new ArrayList<>();
		
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
			String sql = "select * from bookmark_group order by sequence";

			preparedStatement = connection.prepareStatement(sql);
			rs = preparedStatement.executeQuery();

			while(rs.next()) {
				BookMark bookmark = new BookMark();
				bookmark.setId(rs.getInt("id"));
				bookmark.setBookmarkName(rs.getString("bookmark_name"));
				bookmark.setSequence(rs.getInt("sequence"));
				bookmark.setMakeDate(rs.getString("make_date"));
				bookmark.setEditDate(rs.getString("edit_date"));
				bookmarkList.add(bookmark);
			}
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
		
		return bookmarkList;
	}

}

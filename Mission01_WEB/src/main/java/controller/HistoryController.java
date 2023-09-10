package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HistorydelDAO;
import dto.WifiHistory;

public class HistoryController {
	public void showHistory(HttpServletRequest request) {
		HistorydelDAO dao = new HistorydelDAO();

		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		List<WifiHistory> list = dao.showHistory();
		request.setAttribute("lists", list);
	}

}

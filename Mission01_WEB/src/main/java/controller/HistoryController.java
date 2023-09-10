package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HistoryDAO;
import dto.WifiHistory;

public class HistoryController {
	HistoryDAO dao = new HistoryDAO();

	public void showHistory(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		List<WifiHistory> list = dao.showHistory();
		request.setAttribute("lists", list);
	}

	public void delete(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		dao.delhistory(Integer.parseInt(request.getParameter("id")));
	}
}

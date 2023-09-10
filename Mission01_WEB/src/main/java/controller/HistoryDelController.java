package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.HistorydelDAO;
import dto.WifiHistory;

public class HistoryDelController {
	public void execute(HttpServletRequest request) {
		HistorydelDAO dao = new HistorydelDAO();

		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		System.out.println(request.getParameter("id"));
		dao.delhistory(Integer.parseInt(request.getParameter("id")));
	}
}

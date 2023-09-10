package controller;

import java.io.UnsupportedEncodingException;
import javax.servlet.http.HttpServletRequest;

import dao.DistUpdateDAO;

public class DistUpdateController{//frobt로부터 데이터 전달받을
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		double x = Double.parseDouble(request.getParameter("x"));
		double y = Double.parseDouble(request.getParameter("y"));
		
		DistUpdateDAO dao = new DistUpdateDAO();
		dao.calDist(x, y);
	}
}

package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BookmarkDAO;
import dao.BookmarkGroupDAO;
import dto.BookMark;

public class BookmarkController{//frobt로부터 데이터 전달받을
	BookmarkDAO dao = new BookmarkDAO();
	
	public void add(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		String name = request.getParameter("name");
		int order =  Integer.parseInt(request.getParameter("order"));
		
		dao.add(name, order);
	}
}

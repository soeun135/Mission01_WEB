package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BookmarkDAO;
import dto.BookMark;

public class BookmarkController{//frobt로부터 데이터 전달받을
	BookmarkDAO dao = new BookmarkDAO();
	
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		String name = request.getParameter("name");
		int order =  Integer.parseInt(request.getParameter("order"));
		
		dao.add(name, order);
	}

	public void showList(HttpServletRequest request){
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		List<BookMark> list = dao.showList();
		request.setAttribute("lists", list);
	}
	
	public void edit(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		System.out.println(request.getParameter("name"));
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int order = Integer.parseInt(request.getParameter("order"));
		dao.edit(id, name, order);
	}
}

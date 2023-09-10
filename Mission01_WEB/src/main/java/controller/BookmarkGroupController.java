package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BookmarkGroupDAO;
import dto.BookMark;

public class BookmarkGroupController{//frobt로부터 데이터 전달받을
	BookmarkGroupDAO dao = new BookmarkGroupDAO();
	
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
		
		int id = Integer.parseInt(request.getParameter("id"));
		String name = request.getParameter("name");
		int order = Integer.parseInt(request.getParameter("order"));
		dao.edit(id, name, order);
	}

	public void delete(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		int id = Integer.parseInt(request.getParameter("id"));
		dao.delete(id);
	}
}

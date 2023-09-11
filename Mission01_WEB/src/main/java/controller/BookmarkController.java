package controller;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.BookmarkDAO;
import dao.BookmarkGroupDAO;
import dao.ShowWifiDAO;
import dto.BookMark;
import dto.BookMarkGroup;
import dto.WifiList;

public class BookmarkController{//frobt로부터 데이터 전달받을
	BookmarkDAO dao = new BookmarkDAO();
	
	public void add(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		String bmGroup = request.getParameter("bookmarkGroup");
		String manageNo = request.getParameter("manageNo");

		ShowWifiDAO wifiDAO = new ShowWifiDAO();
		WifiList wifiList = wifiDAO.findBymanageNo(manageNo);

		dao.add(bmGroup, wifiList.getWifiName());
	}

	public void show(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		List<BookMark>list = dao.show();
		request.setAttribute("lists", list);
	}

	public void delete(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		BookMark bookMark = dao.findById(Integer.parseInt(request.getParameter("id")));
		request.setAttribute("bookMark",bookMark);
	}

	public void deleteset(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		dao.deleteset(Integer.parseInt(request.getParameter("id")));
	}
}

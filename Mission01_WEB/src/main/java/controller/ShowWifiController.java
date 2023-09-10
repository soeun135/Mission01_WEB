package controller;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import dao.ShowWifiDAO;
import dto.WifiList;

public class ShowWifiController{
	public void execute(HttpServletRequest request) {
		try {
			request.setCharacterEncoding("utf-8");
		} catch(UnsupportedEncodingException e) {	}
		
		ShowWifiDAO dao = new ShowWifiDAO();
		List<WifiList>list = dao.showWifi();
		request.setAttribute("lists", list);
	}
}

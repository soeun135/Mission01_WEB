package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FrontController extends HttpServlet implements javax.servlet.Servlet{
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String requestURI = request.getRequestURI();
		String contextPath = request.getContextPath();
		String command = request.getRequestURI().substring(contextPath.length());
		if (command.equals("/home")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		} else if (command.equals("/load")) {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/load-wifi.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/distupdate")) {
			DistUpdateController action = new DistUpdateController();
			action.execute(request);
			response.sendRedirect("/home");
		} else if(command.equals("/showwifi")) {
			ShowWifiController action = new ShowWifiController();
			action.execute(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
			dispatcher.forward(request, response);
		}else if(command.equals("/history")) {
			HistoryController action = new HistoryController();
			action.showHistory(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/loc-history.jsp");
			dispatcher.forward(request, response);
		} 	else if(command.equals("/historydel")) {
			HistoryController action = new HistoryController();
			action.delete(request);
			response.sendRedirect("/history");
		} else if(command.equals("/bookmark/bookmarkadd")) {
			BookmarkController action = new BookmarkController();
			action.execute(request);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarklist");
//			dispatcher.forward(request, response);
			response.sendRedirect("/bookmark/bookmarklist");
		} else if(command.equals("/bookmark/bookmarklist")) {
			BookmarkController action = new BookmarkController();
			action.showList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark-group.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkedit")) {
			BookmarkController action = new BookmarkController();
			action.edit(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarklist");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkdel")) {
			BookmarkController action = new BookmarkController();
			action.delete(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarklist");
			dispatcher.forward(request, response);
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

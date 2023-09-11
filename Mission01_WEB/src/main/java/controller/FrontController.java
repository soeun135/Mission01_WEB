package controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
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
			BookmarkGroupController action = new BookmarkGroupController();
			action.execute(request);
//			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarkglist");
//			dispatcher.forward(request, response);
			response.sendRedirect("/bookmark/bookmarkglist");
		} else if(command.equals("/bookmark/bookmarkglist")) {
			BookmarkGroupController action = new BookmarkGroupController();
			action.showList(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark-group.jsp");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkedit")) {
			BookmarkGroupController action = new BookmarkGroupController();
			action.edit(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarkglist");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkgdel")) {
			BookmarkGroupController action = new BookmarkGroupController();
			action.delete(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarkglist");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkListadd")) {
			BookmarkController action = new BookmarkController();
			action.add(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarkList");
			dispatcher.forward(request, response);
		} else if(command.equals("/bookmark/bookmarkList")) {
			BookmarkController action = new BookmarkController();
			action.show(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark-list.jsp");
			dispatcher.forward(request, response);
				} else if(command.equals("/bookmark/bookmarkdel")) {
			BookmarkController action = new BookmarkController();
			action.delete(request);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmark-list-del.jsp");
			dispatcher.forward(request, response);
			} else if(command.equals("/bookmark/bookmarkdelset")) {
				BookmarkController action = new BookmarkController();
				action.deleteset(request);
				RequestDispatcher dispatcher = request.getRequestDispatcher("/bookmark/bookmarkList");
				dispatcher.forward(request, response);
				} 
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

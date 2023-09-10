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
//		System.out.println(requestURI);
//		System.out.println(contextPath);
//		System.out.println(command);
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
		} 
		else if(command.equals("/historydel")) {
			HistoryDelController action = new HistoryDelController();
			action.execute(request);
//			response.sendRedirect("/history");
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	}

}

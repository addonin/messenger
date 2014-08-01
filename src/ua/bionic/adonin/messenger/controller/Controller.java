package ua.bionic.adonin.messenger.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.commands.ICommand;
import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class Controller extends HttpServlet {
       
	private static final long serialVersionUID = 1L;
	RequestHelper requestHelper = RequestHelper.getRequestHelperInstance();

	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		response.setContentType ("text/html; charset=UTF-8");
		request.setCharacterEncoding ("UTF-8");
		
		String page = null;
		
		if ( "XMLHttpRequest".equals(request.getHeader("X-Requested-With")) ) {
			System.out.println("=== ajax request ===");
			String result = null;
			ICommand command = requestHelper.getAjaxCommand(request);	
			try {
				result = command.execute(request, response);
				response.getWriter().write(result);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ERROR_PAGE);
			}			
		} else {						
			ICommand command = requestHelper.getCommand(request);
			try {
				page = command.execute(request, response);
			} catch (ServletException | IOException e) {
				e.printStackTrace();
				page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ERROR_PAGE);
			}
			RequestDispatcher dispatcher = getServletContext().getRequestDispatcher(page);
	        dispatcher.forward(request, response);		
		}

	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		processRequest(request, response);
	}
	
	public String getServletInfo() {
        return "Servlet Controller";
    }

}

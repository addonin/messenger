package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;
import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class RegisterCommand implements ICommand {
	
	private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    private static final String EMAIL = "email";
    private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page;
		String login = new String(request.getParameter(LOGIN).getBytes("UTF-8"), "UTF-8");
    	String password = new String(request.getParameter(PASSWORD).getBytes("UTF-8"), "UTF-8");
    	String email = new String(request.getParameter(EMAIL).getBytes("UTF-8"), "UTF-8");
    	String firstname = new String(request.getParameter(FIRSTNAME).getBytes("UTF-8"), "UTF-8");
    	String lastname = new String(request.getParameter(LASTNAME).getBytes("UTF-8"), "UTF-8");
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		IUsersDAO userDAO = daoFactory.getUsersDAO();
		
		if (userDAO.addUser(login, password, email, firstname, lastname, "", "") == 1) {
			page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.LOGIN_PAGE);
		} else {
			page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ERROR_PAGE);
		}
		
		return page;
	}

}

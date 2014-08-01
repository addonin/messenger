package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.entities.User;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;
import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class EditCommand implements ICommand {
	
	private static final String FIRSTNAME = "firstname";
    private static final String LASTNAME = "lastname";
    private static final String EMAIL = "email";
    private static final String INFO = "info";
    private static final String PASSWORD = "password";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String page;
    	String firstname = request.getParameter(FIRSTNAME);
    	String lastname = request.getParameter(LASTNAME);
    	String email = request.getParameter(EMAIL);
    	String info = request.getParameter(INFO);
    	String password = request.getParameter(PASSWORD);
    	
    	HttpSession session = request.getSession(true);
    	User user = (User) session.getAttribute("user");
    	int userID = user.getUserID();  
    	String username = user.getUsername();
  
    	DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IUsersDAO userDAO = daoFactory.getUsersDAO();
    	
    	if (userDAO.updateUser(userID, password, email, firstname, lastname, info) != 0) {
    		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.MAIN_PAGE);
    	} else {
    		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.ERROR_PAGE);
    	};
    	
    	session.setAttribute("user", userDAO.findUserByUsername(username));
		
		return page;
	}

}

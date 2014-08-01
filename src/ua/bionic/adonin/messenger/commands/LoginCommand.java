package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.entities.User;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;
import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class LoginCommand implements ICommand {
		
	private static final String LOGIN = "login";
    private static final String PASSWORD = "password";
    
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    	
    	String page;
    	String login = request.getParameter(LOGIN);
    	String password = request.getParameter(PASSWORD);    	
  
    	DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IUsersDAO userDAO = daoFactory.getUsersDAO();
  
    	if (userDAO.checkAuth(login, password)) {    	
    		
    		HttpSession session = request.getSession(true);
    		User user = userDAO.findUserByUsername(login);
    		int userID = user.getUserID();
    		userDAO.updateUserActivity(userID, true);
    		session.setAttribute("user", user);
    		
    		// TODO add cookie if check "Remember me"
    		Cookie cookie = new Cookie("cookie", login);    		
    		response.addCookie(cookie);
    		
    		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.MAIN_PAGE);
    		
    	} else {
    		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.LOGIN_PAGE);
    	}  
    	
    	return page;
    }	

}

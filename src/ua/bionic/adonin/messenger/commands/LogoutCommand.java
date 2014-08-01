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

public class LogoutCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {	
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IUsersDAO userDAO = daoFactory.getUsersDAO();
		
		HttpSession session = request.getSession(true);
		User user = (User) session.getAttribute("user");
		userDAO.updateUserActivity(user.getUserID(), false);
		session.invalidate();
		
		return ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.LOGIN_PAGE);
	}

}

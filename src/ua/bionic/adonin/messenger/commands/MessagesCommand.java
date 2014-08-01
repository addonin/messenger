package ua.bionic.adonin.messenger.commands;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;
import ua.bionic.adonin.messenger.data.entities.User;
import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class MessagesCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IUsersDAO userDAO = daoFactory.getUsersDAO();
		
		HttpSession session = request.getSession(true);
		int userID = ((User) session.getAttribute("user")).getUserID();
        List<User> friends = userDAO.findFriends(userID);
        session.setAttribute("friends", friends);
		
		String page;
		page = ConfigurationManager.getConfigurationManagerInstance().getProperty(ConfigurationManager.MESSAGES_PAGE);
		return page;		
		
	}

}

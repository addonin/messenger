package ua.bionic.adonin.messenger;

import java.util.List;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.entities.User;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;

public class Client {

	public static void main(String[] args) {

		DAOFactory daoFactory = DAOFactory.getDAOFactory();
		IUsersDAO userDAO = daoFactory.getUsersDAO();
		
		//insert user
		//System.out.println(userDAO.addUser("fedorov", "fedorov", "fedorov@bionic.ua", "Fedor", "Fedorov", "I am Fedorov", ""));
						
		//select users
		/*List<User> users_before_update = userDAO.findUsers();
		for (User user : users_before_update) {
			System.out.println(user.getUsername());
		}*/
		
		
		
		//update user
		//userDAO.updateUser(4, "root", "root", "new@email.com", "firstname", "lastname", "info", "photo");
		
		//select users
		List<User> users_after_update = userDAO.findUsers();
		for (User user : users_after_update) {
			System.out.println(user.getUsername());
		}
		
		//delete user		
		//System.out.println(userDAO.deleteUser(4));
		
	}

}

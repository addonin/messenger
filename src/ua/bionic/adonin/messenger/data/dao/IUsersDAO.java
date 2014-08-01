package ua.bionic.adonin.messenger.data.dao;

import java.util.List;

import ua.bionic.adonin.messenger.data.entities.User;

public interface IUsersDAO {
	public int addUser(String username, String password, String email, String firstname, String lastname, String info, String photo);
	public List<User> findUsers();
	public User findUserByUsername(String username);
	public List<User> findUsersByUsername(String lastname);
	public int updateUser(int userID, String password, String email, String firstname, String lastname, String info);
	public int updateUserActivity(int userID, boolean activity);
	public int deleteUser(int userID);
	public boolean checkAuth(String login, String password);
}

package ua.bionic.adonin.messenger.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.bionic.adonin.messenger.data.core.QueryJDBC;
import ua.bionic.adonin.messenger.data.entities.User;

public class UsersDAOImpl implements IUsersDAO {
	
	protected final String SQL_SET_ENCODING = "SET NAMES UTF8";
	
	protected final String SQL_INSERT = "INSERT INTO Users (username, password, email, type_id, firstname, lastname, info, photo) VALUES (?, ?, ?, default, ?, ?, ?, ?)";	
	protected final String SQL_SELECT = "SELECT user_id, username, password, email, type_id, firstname, lastname, activity, info, photo FROM Users";
	protected final String SQL_UPDATE = "UPDATE Users SET password = ?, email = ?, firstname = ?, lastname = ?, info = ? WHERE user_id = ?";
	protected final String SQL_DELETE = "DELETE FROM Users WHERE user_id = ?";
	
	protected final String SQL_SELECT_CHECK_LOGIN = SQL_SELECT + " WHERE username LIKE ? AND password LIKE ?";	
	protected final String SQL_SELECT_USER_BY_USERNAME = SQL_SELECT + " WHERE username = ?";
	protected final String SQL_SELECT_USERS_BY_USERNAME = SQL_SELECT + " WHERE lastname LIKE ?";
	protected final String SQL_SELECT_FRIENDS = SQL_SELECT + " JOIN Relations r1 ON Users.user_id = r1.from_id JOIN Relations r2 ON r1.from_id = r2.to_id AND r1.to_id = r2.from_id AND r1.from_id <> r1.to_id WHERE r2.from_id = ?";
	
	protected final String SQL_UPDATE_ACTIVITY = "UPDATE Users SET activity = ? WHERE user_id = ?";
	
	private static final int COLUMN_USER_ID = 1;
    private static final int COLUMN_USERNAME = 2;
    private static final int COLUMN_PASSWORD = 3;
    private static final int COLUMN_EMAIL = 4;
    private static final int COLUMN_TYPE_ID = 5;
    private static final int COLUMN_FIRSTNAME = 6;
    private static final int COLUMN_LASTNAME = 7;
    private static final int COLUMN_ACTIVITY = 8;
    private static final int COLUMN_INFO = 9;
    private static final int COLUMN_PHOTO = 10;
	
	@Override
	public int addUser(String username, String password, String email, String firstname, String lastname, String info, String photo) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_INSERT);
			query.setString(1, username);
			query.setString(2, password);
			query.setString(3, email);
			query.setString(4, firstname);
			query.setString(5, lastname);
			query.setString(6, info);
			query.setString(7, photo);	
			result = query.executeUpdate();			
		} 
		catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return result;
	}

	@Override
	public List<User> findUsers() {
		QueryJDBC query = new QueryJDBC();
		List<User> users = new ArrayList<>();
		try {
			query.createStatement();
			ResultSet resultSet = query.executeQuery(SQL_SELECT);
			while (resultSet.next()) {
				User user = new User();		
				user.setUserID(resultSet.getInt(COLUMN_USER_ID));
				user.setUsername(resultSet.getString(COLUMN_USERNAME));
				user.setPassword(resultSet.getString(COLUMN_PASSWORD));
				user.setEmail(resultSet.getString(COLUMN_EMAIL));
				user.setType(resultSet.getInt(COLUMN_TYPE_ID));
				user.setFirstname(resultSet.getString(COLUMN_FIRSTNAME));
				user.setLastname(resultSet.getString(COLUMN_LASTNAME));
				user.setActivity(resultSet.getBoolean(COLUMN_ACTIVITY));
				user.setInfo(resultSet.getString(COLUMN_INFO));
				user.setPhoto(resultSet.getString(COLUMN_PHOTO));
				users.add(user);
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create statement");
			e.printStackTrace();
		} 
		finally {
			query.close();
		}
		return users;		
	}
	
	@Override
	public User findUserByUsername(String username) {
		QueryJDBC query = new QueryJDBC();
		User user = null;
		try {
			query.createPreparedStatement(SQL_SELECT_USER_BY_USERNAME);
			query.setString(1, username);
			ResultSet resultSet = query.executeQuery();
			if (resultSet != null) {
				while (resultSet.next()) {
					user = new User();
					user.setUserID(resultSet.getInt(COLUMN_USER_ID));
					user.setUsername(resultSet.getString(COLUMN_USERNAME));
					user.setPassword(resultSet.getString(COLUMN_PASSWORD));
					user.setEmail(resultSet.getString(COLUMN_EMAIL));
					user.setType(resultSet.getInt(COLUMN_TYPE_ID));
					user.setFirstname(resultSet.getString(COLUMN_FIRSTNAME));
					user.setLastname(resultSet.getString(COLUMN_LASTNAME));
					user.setActivity(resultSet.getBoolean(COLUMN_ACTIVITY));
					user.setInfo(resultSet.getString(COLUMN_INFO));
					user.setPhoto(resultSet.getString(COLUMN_PHOTO));
				}
				resultSet.close();
			} 
		} catch (SQLException e) {
			System.out.println("=== Failed to create prepared statement ===");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return user;
	}
	
	@Override
	public List<User> findUsersByUsername(String lastname) {
		QueryJDBC query = new QueryJDBC();
		List<User> users = new ArrayList<>();
		try {
			query.createPreparedStatement(SQL_SELECT_USERS_BY_USERNAME);
			query.setString(1, lastname + "%");
			ResultSet resultSet = query.executeQuery();
			while (resultSet.next()) {
				User user = new User();	
				user.setUserID(resultSet.getInt(COLUMN_USER_ID));
				user.setUsername(resultSet.getString(COLUMN_USERNAME));
				user.setPassword(resultSet.getString(COLUMN_PASSWORD));
				user.setEmail(resultSet.getString(COLUMN_EMAIL));
				user.setType(resultSet.getInt(COLUMN_TYPE_ID));
				user.setFirstname(resultSet.getString(COLUMN_FIRSTNAME));
				user.setLastname(resultSet.getString(COLUMN_LASTNAME));
				user.setActivity(resultSet.getBoolean(COLUMN_ACTIVITY));
				user.setInfo(resultSet.getString(COLUMN_INFO));
				user.setPhoto(resultSet.getString(COLUMN_PHOTO));
				users.add(user);
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create statement");
			e.printStackTrace();
		} 
		finally {
			query.close();
		}
		return users;		
	}	

	@Override
	public int updateUser(int userID, String password, String email, String firstname, String lastname, String info) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_UPDATE);
			query.setString(1, password);
			query.setString(2, email);
			query.setString(3, firstname);
			query.setString(4, lastname);
			query.setString(5, info);			
			query.setInt(6, userID);
			result = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return result;
	}
	
	@Override
	public int updateUserActivity(int userID, boolean activity) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_UPDATE_ACTIVITY);
			query.setBoolean(1, activity);
			query.setInt(2, userID);
			result = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}			
		return result;
	}

	@Override
	public int deleteUser(int userID) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_DELETE);
			query.setInt(1, userID);
			result = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}	
		finally {
			query.close();
		}		
		return result;
	}
	
	@Override
	public boolean checkAuth(String login, String password) {
		QueryJDBC query = new QueryJDBC();
		boolean result = false;
		try {
			query.createPreparedStatement(SQL_SELECT_CHECK_LOGIN);
			query.setString(1, login);
			query.setString(2, password);
			ResultSet resultSet = query.executeQuery();
			result = resultSet.next(); 
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("=== Failed to create prepared statement ===");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return result;
	}

	@Override
	public List<User> findFriends(int userID) {
		QueryJDBC query = new QueryJDBC();
		List<User> friends = new ArrayList<>();
		try {
			query.createPreparedStatement(SQL_SELECT_FRIENDS);
			query.setInt(1, userID);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()) {
				User user = new User();
				user.setUserID(resultSet.getInt(COLUMN_USER_ID));
				user.setUsername(resultSet.getString(COLUMN_USERNAME));
				user.setPassword(resultSet.getString(COLUMN_PASSWORD));
				user.setEmail(resultSet.getString(COLUMN_EMAIL));
				user.setType(resultSet.getInt(COLUMN_TYPE_ID));
				user.setFirstname(resultSet.getString(COLUMN_FIRSTNAME));
				user.setLastname(resultSet.getString(COLUMN_LASTNAME));
				user.setActivity(resultSet.getBoolean(COLUMN_ACTIVITY));
				user.setInfo(resultSet.getString(COLUMN_INFO));
				user.setPhoto(resultSet.getString(COLUMN_PHOTO));
				friends.add(user);
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create statement");
			e.printStackTrace();
		} 
		finally {
			query.close();
		}
		return friends;
	}	
 
}

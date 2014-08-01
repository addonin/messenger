package ua.bionic.adonin.messenger.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.bionic.adonin.messenger.data.core.QueryJDBC;
import ua.bionic.adonin.messenger.data.entities.UserType;

public class UserTypesDAOImpl implements IUserTypesDAO {
	
	protected final String SQL_INSERT = "INSERT INTO UserTypes type VALUES (?)";
	protected final String SQL_SELECT = "SELECT type_id, type FROM UserTypes";
	protected final String SQL_UPDATE = "UPDATE UserTypes SET type = ? WHERE type_id = ?";
	protected final String SQL_DELETE = "DELETE FROM UserTypes WHERE type_id = ?";

	//private static final int COLUMN_TYPE_ID = 1;
	private static final int COLUMN_TYPE = 2;
	
	@Override
	public int addUserType(String type) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_INSERT);
			query.setString(1, type);
			query.executeUpdate();
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
	public List<UserType> findUserTypes() {
		QueryJDBC query = new QueryJDBC();
		List<UserType> userTypes = new ArrayList<>();
		try {
			query.createStatement();
			ResultSet resultSet = query.executeQuery(SQL_SELECT);
			while(resultSet.next()) {
				UserType userType = new UserType();
				userType.setUserType(resultSet.getString(COLUMN_TYPE));
				userTypes.add(userType);
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
		return userTypes;
	}

	@Override
	public int updateUserType(int typeID, String type) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_UPDATE);
			query.setString(1, type);
			query.setInt(2, typeID);
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
	public int deleteUserType(int typeID) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_DELETE);
			query.setInt(1, typeID);
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

}

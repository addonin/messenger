package ua.bionic.adonin.messenger.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.bionic.adonin.messenger.data.core.QueryJDBC;

public class RelationsDAOImpl implements IRelationsDAO {
	
	protected final String SQL_INSERT = "INSERT INTO Relations (from_id, to_id) VALUES (?, ?)";
	protected final String SQL_SELECT = "SELECT to_id FROM Relations WHERE from_id = ?";	
	protected final String SQL_UPDATE = "";
	protected final String SQL_DELETE = "DELETE FROM Relations WHERE from_id = ? AND to_id = ?";
	
	protected final String SQL_SELECT_RELATION = SQL_SELECT + " AND to_id = ?";
	
	protected static final int COLUMN_RELATION_ID = 1;
	protected static final int COLUMN_FROM_ID = 2;
	protected static final int COLUMN_TO_ID = 3;	

	@Override
	public int addRelation(int fromID, int toID) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_INSERT);
			query.setInt(1, fromID);
			query.setInt(2, toID);
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
	public List<Integer> findRelations(int fromID) {
		QueryJDBC query = new QueryJDBC();
		List<Integer> relatives = new ArrayList<>();
		try {
			query.createPreparedStatement(SQL_SELECT);
			query.setInt(1, fromID);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()) {				
				relatives.add(resultSet.getInt(COLUMN_TO_ID));
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return relatives;
	}
	
	@Override
	public boolean findRelation(int fromID, int toID) {
		QueryJDBC query = new QueryJDBC();
		boolean relation = false;
		try {
			query.createPreparedStatement(SQL_SELECT_RELATION);
			query.setInt(1, fromID);
			query.setInt(2, toID);
			ResultSet resultSet = query.executeQuery();
			relation = resultSet.next();
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return relation;
	}

	@Override
	public UnsupportedOperationException updateRelation() {
		return new UnsupportedOperationException("Unable to update relations");
	}
	
	@Override
	public int deleteRelation(int fromID, int toID) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_DELETE);
			query.setInt(1, fromID);
			query.setInt(2, toID);
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

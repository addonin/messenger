package ua.bionic.adonin.messenger.data.core;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class QueryJDBC {

	private Connection connection = null;
	private Statement statement = null;
	private static ConnectionManagerJDBC connectionManager = ConnectionManagerJDBC.getConnectionManagerInstance();
	
	public QueryJDBC() {
		this.connection = connectionManager.getConnection();
	}
	
	public void createStatement() throws SQLException {
		this.statement = connection.createStatement();
	}
	
	public void createPreparedStatement(String query) throws SQLException {
		this.statement = connection.prepareStatement(query);		
	}
	
	public void setString(int key, String value) throws SQLException {
		((PreparedStatement) this.statement).setString(key, value);
	}
	
	public void setInt(int key, int value) throws SQLException {
		((PreparedStatement) this.statement).setInt(key, value);
	}
	
	public void setBoolean(int key, boolean value) throws SQLException {
		((PreparedStatement) this.statement).setBoolean(key, value);
	}
	
	public void setDate(int key, Date date) throws SQLException {
		((PreparedStatement) this.statement).setDate(key, date);
	}
	
	public ResultSet executeQuery(String query) throws SQLException {
		return statement.executeQuery(query);
	}	
	
	public ResultSet executeQuery() throws SQLException {
		return ((PreparedStatement)statement).executeQuery();
	}
	
	public int executeUpdate() throws SQLException {
		return ((PreparedStatement)statement).executeUpdate();
	}
	
	public void close() {
		try {
			if (this.statement != null) {
				this.statement.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to close statement");
			e.printStackTrace();
		}
		finally {
			connectionManager.releaseConnection(connection);
		}
	}
	
}

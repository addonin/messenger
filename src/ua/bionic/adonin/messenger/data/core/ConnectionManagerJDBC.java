package ua.bionic.adonin.messenger.data.core;

import java.sql.Connection;

public final class ConnectionManagerJDBC {
	
	//TODO: log4j
	
	private static volatile ConnectionManagerJDBC connectionManagerInstance;
	private static ConnectionPoolJDBC connectionPool = ConnectionPoolJDBC.getConnectionPoolInstance();
	
	private ConnectionManagerJDBC() {
		
	}
			
	public static ConnectionManagerJDBC getConnectionManagerInstance() {
		if (connectionManagerInstance == null) {
			synchronized (ConnectionManagerJDBC.class) {				
				if (connectionManagerInstance == null) {
					connectionManagerInstance = new ConnectionManagerJDBC();
				}
			}
		}
		return connectionManagerInstance;
	}
	
	public Connection getConnection() {
		return connectionPool.getConnection();
	}
	
	public void releaseConnection(Connection connection) {
		connectionPool.releaseConnection(connection);
	}

}

package ua.bionic.adonin.messenger.data.core;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import ua.bionic.adonin.messenger.managers.ConfigurationManager;

public class ConnectionPoolJDBC {
	
	//TODO: log4j
	
	private int connectionCounter = 0;
	private static volatile ConnectionPoolJDBC connectionPoolInstance;	
	private Connection [] connectionPoolArray;
	
	private ConfigurationManager configurationManager = ConfigurationManager.getConfigurationManagerInstance();
	private String jdbcDriver = configurationManager.getProperty(ConfigurationManager.DATABASE_DRIVER_NAME);
	private String jdbcURL = configurationManager.getProperty(ConfigurationManager.DATABASE_URL);
	private String user = configurationManager.getProperty(ConfigurationManager.DATABASE_USER);
	private String password = configurationManager.getProperty(ConfigurationManager.DATABASE_PASSWORD);
	private int connectionPoolSize = Integer.parseInt(configurationManager.getProperty(ConfigurationManager.DATABASE_POOL_SIZE));
		
	
	private ConnectionPoolJDBC() {	
		connectionPoolArray = new Connection[connectionPoolSize];
		try {
			Class.forName(jdbcDriver);			
			System.out.println("=== Driver found ===");
		} catch (ClassNotFoundException e) {			
			System.out.println("=== Driver not found ===");
			e.printStackTrace();
		}
		for (int i = 0; i < connectionPoolSize; i++) {
			Connection connection = null;
			try {
				connection = DriverManager.getConnection(jdbcURL, user, password);
				System.out.println("=== Connection " + (i + 1) + " established ===");
			} catch (SQLException e) {
				System.out.println("=== Connection " + (i + 1) + " failed ===");
				e.printStackTrace();
			}
			connectionPoolArray[i] = connection;
		}
	}
	
	public static ConnectionPoolJDBC getConnectionPoolInstance() {
		if (connectionPoolInstance == null) {
			synchronized (ConnectionPoolJDBC.class) {				
				if (connectionPoolInstance == null) {
					connectionPoolInstance = new ConnectionPoolJDBC();
				}
			}
		}
		return connectionPoolInstance;
	}
	
	public synchronized Connection getConnection() {
		Connection connection = null;
		while (connectionCounter == connectionPoolSize) {
			try {
				wait();
			} catch (InterruptedException e) {
				System.out.println("=== Failed to get connection from pool ===");
				e.printStackTrace();
			}
		}		
		connectionCounter++;
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] != null) {
				connection = connectionPoolArray[i];
				connectionPoolArray[i] = null;
				break;
			}
		}
		notify();
		return connection;
	}
	
	synchronized public void releaseConnection(Connection connection) {
		connectionCounter--;
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] == null) {
				connectionPoolArray[i] = connection;
				break;
			}
		}
	}
		
	synchronized public void closeAll() {
		for (int i = 0; i < connectionPoolSize; i++) {
			if (connectionPoolArray[i] != null) {
				try {
					connectionPoolArray[i].close();
				} catch (SQLException e) {
					System.out.println("=== Failed to close connection " + i + " ===");
					e.printStackTrace();
				}
			}
		}
		connectionPoolArray = null;
	}
	
}

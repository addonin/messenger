package ua.bionic.adonin.messenger.managers;

import java.util.ResourceBundle;

public class ConfigurationManager {	  
    
    public static final String DATABASE_DRIVER_NAME = "JDBC_DRIVER";
    public static final String DATABASE_URL = "JDBC_URL";
    public static final String DATABASE_POOL_SIZE = "CONNECTION_POOL_SIZE";
    public static final String DATABASE_USER = "USER";
    public static final String DATABASE_PASSWORD = "PASSWORD";
    public static final String ERROR_PAGE = "ERROR_PAGE";
    public static final String MAIN_PAGE = "MAIN_PAGE";
    public static final String LOGIN_PAGE = "LOGIN_PAGE";
    public static final String PEOPLE_PAGE = "PEOPLE_PAGE";
    public static final String MESSAGES_PAGE = "MESSAGES_PAGE";
    
    private static final String CONFIGURATION = "ua.bionic.adonin.messenger.managers.configuration";
    private static volatile ConfigurationManager configurationManagerInstance;
    private ResourceBundle resourceBundle;
    
    public static ConfigurationManager getConfigurationManagerInstance(){
    	if (configurationManagerInstance == null) {
			synchronized (ConfigurationManager.class) {				
				if (configurationManagerInstance == null) {
					configurationManagerInstance = new ConfigurationManager();
					configurationManagerInstance.resourceBundle = ResourceBundle.getBundle(CONFIGURATION);					
				}
			}
		}
		return configurationManagerInstance;
    }
    
    public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}


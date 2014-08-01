package ua.bionic.adonin.messenger.managers;

import java.util.ResourceBundle;

public class MessageManager {
		
	public static final String LOGIN_ERROR = "LOGIN_ERROR";
    public static final String SERVLET_EXCEPTION_ERROR = "SERVLET_EXCEPTION_ERROR";
    public static final String IO_EXCEPTION_ERROR = "IO_EXCEPTION_ERROR";
	
    private static final String MESSAGES = "ua.bionic.adonin.messenger.messages";
    private static volatile MessageManager messageManagerInstance = null;
	private ResourceBundle resourceBundle;
	
	private MessageManager() {
		
	}
	
	public MessageManager getMessageManagerInstance() {
		if (messageManagerInstance == null) {
			synchronized (MessageManager.class) {
				if (messageManagerInstance == null) {
					messageManagerInstance = new MessageManager();
					messageManagerInstance.resourceBundle = ResourceBundle.getBundle(MESSAGES);
				}				
			}
		}
		return messageManagerInstance;
	}
	
	public String getProperty(String key){
        return (String)resourceBundle.getObject(key);
    }
}

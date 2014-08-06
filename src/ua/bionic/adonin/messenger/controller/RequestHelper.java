package ua.bionic.adonin.messenger.controller;

import java.util.HashMap;

import javax.servlet.http.HttpServletRequest;

import ua.bionic.adonin.messenger.commands.AddRelationCommand;
import ua.bionic.adonin.messenger.commands.EditCommand;
import ua.bionic.adonin.messenger.commands.FindRelationCommand;
import ua.bionic.adonin.messenger.commands.FindUsersCommand;
import ua.bionic.adonin.messenger.commands.GetConversationCommand;
import ua.bionic.adonin.messenger.commands.HomeCommand;
import ua.bionic.adonin.messenger.commands.ICommand;
import ua.bionic.adonin.messenger.commands.LoginCommand;
import ua.bionic.adonin.messenger.commands.LogoutCommand;
import ua.bionic.adonin.messenger.commands.MessagesCommand;
import ua.bionic.adonin.messenger.commands.NoCommand;
import ua.bionic.adonin.messenger.commands.PeopleCommand;
import ua.bionic.adonin.messenger.commands.RegisterCommand;
import ua.bionic.adonin.messenger.commands.DeleteRelationCommand;
import ua.bionic.adonin.messenger.commands.SendMessageCommand;

public class RequestHelper {
	
	private static RequestHelper requestHelperInstance = null;
    HashMap<String,ICommand> commands = new HashMap<String,ICommand>();

    private RequestHelper() {
      commands.put("login", new LoginCommand());
      commands.put("logout", new LogoutCommand());
      commands.put("register", new RegisterCommand());
      commands.put("edit", new EditCommand());
      commands.put("home", new HomeCommand());
      commands.put("people", new PeopleCommand());
      commands.put("findusers", new FindUsersCommand());
      commands.put("messages", new MessagesCommand());
      commands.put("addrelation", new AddRelationCommand());
      commands.put("deleterelation", new DeleteRelationCommand());
      commands.put("findrelation", new FindRelationCommand());
      commands.put("getconversation", new GetConversationCommand());
      commands.put("sendmessage", new SendMessageCommand());
    }
    
    public ICommand getCommand(HttpServletRequest request){
        String action = request.getParameter("command");
        System.out.println("=== " + action + " ===");
        ICommand command = commands.get(action);
        if (command == null){
            command = new NoCommand();
        }
        return command;
    }
    
    public ICommand getAjaxCommand(HttpServletRequest request){
        String action = request.getParameter("ajaxCommand");
        System.out.println("=== " + action + " ===");
        ICommand command = commands.get(action);
        if (command == null){
            command = new NoCommand();
        }
        return command;
    }
    
    public static RequestHelper getRequestHelperInstance(){
    	if (requestHelperInstance == null) {
			synchronized (RequestHelper.class) {				
				if (requestHelperInstance == null) {
					requestHelperInstance = new RequestHelper();
				}
			}
		}
		return requestHelperInstance;
    }
    
}

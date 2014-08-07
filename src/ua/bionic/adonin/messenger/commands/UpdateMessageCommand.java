package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IMessagesDAO;

public class UpdateMessageCommand implements ICommand {
	
	private static final String MESSAGE_ID = "messageId";
    private static final String STATUS = "status";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        int messageID = Integer.parseInt(request.getParameter(MESSAGE_ID));
        boolean status = Boolean.parseBoolean(request.getParameter(STATUS));
        
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        IMessagesDAO messagesDAO = daoFactory.getMessagesDAO();
        int result = messagesDAO.updateMessage(messageID, status);
        
		return Integer.toString(result);
	}

}

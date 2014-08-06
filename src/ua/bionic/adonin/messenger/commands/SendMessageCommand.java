package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IMessagesDAO;

public class SendMessageCommand implements ICommand {
	
	private static final String FROMID = "fromID";
    private static final String TOID = "toID";
    private static final String MESSAGE = "message";

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

        int senderID = Integer.parseInt(request.getParameter(FROMID));
        int receiverID = Integer.parseInt(request.getParameter(TOID));
        String text = request.getParameter(MESSAGE);
    
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        IMessagesDAO messagesDAO = daoFactory.getMessagesDAO();
        int result = messagesDAO.addMessage(senderID, receiverID, text);    
        
		return Integer.toString(result);
	}

}

package ua.bionic.adonin.messenger.commands;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONException;
import org.json.JSONObject;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IMessagesDAO;
import ua.bionic.adonin.messenger.data.entities.User;

public class GetUpdatesCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		String result = "";
		response.setContentType("application/json");
		HttpSession session = request.getSession(true);
		int receiverID = ((User) session.getAttribute("user")).getUserID();
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
        IMessagesDAO messagesDAO = daoFactory.getMessagesDAO();  
        
        Map<Integer, Integer> unread = messagesDAO.findUnread(receiverID);
        JSONObject jsonObject = new JSONObject();
        
        for (Map.Entry<Integer, Integer> entry : unread.entrySet()) {
        	try {
				jsonObject.put(entry.getKey().toString(), entry.getValue());
			} catch (JSONException e) {
				System.out.println("=== Failed to create JSON object ===");
				e.printStackTrace();
			}
        }
        result = jsonObject.toString();
		return result;
	}

}

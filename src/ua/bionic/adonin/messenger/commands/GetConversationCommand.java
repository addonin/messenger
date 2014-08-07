package ua.bionic.adonin.messenger.commands;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IMessagesDAO;
import ua.bionic.adonin.messenger.data.entities.Message;

/**
*
* @author dimon
*/

public class GetConversationCommand implements ICommand {
    
    private static final String SENDER_ID = "senderId";
    private static final String RECEIVER_ID = "receiverId";

    @Override
    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        String result = "";
        int senderId = Integer.parseInt(request.getParameter(SENDER_ID));
        int receiverId = Integer.parseInt(request.getParameter(RECEIVER_ID));
        
        DAOFactory daoFactory = DAOFactory.getDAOFactory();
        IMessagesDAO messagesDAO = daoFactory.getMessagesDAO();
        
        List<Message> list = new ArrayList<>();
        
        List<Message> out = messagesDAO.findMessages(senderId, receiverId);
        if (out != null) {
            list.addAll(out);
        }
        List<Message> in = messagesDAO.findMessages(receiverId, senderId);
        if (in != null) {
            list.addAll(in);
        }
        
        Collections.sort(list, new Comparator<Message>() {
            public int compare(Message m1, Message m2) {
                return m1.getTimestamp().compareTo(m2.getTimestamp());
            }
        });
        
        JSONArray jsonArray = new JSONArray();
        if (list != null) {
            for (Message message : list) {
                JSONObject jsonObject = new JSONObject();
                try {
                		jsonObject.put("messageId", message.getMessageID());
                		jsonObject.put("senderId", message.getSenderID());
                        jsonObject.put("receiverId", message.getReceiverID());
                        jsonObject.put("text", message.getText());
                        jsonObject.put("status", message.getStatus());
                        jsonObject.put("timestamp", message.getTimestamp().getTime());
                } catch (JSONException e) {
                        System.out.println("=== Failed to create JSON object ===");
                        e.printStackTrace();
                }
                jsonArray.put(jsonObject);
            }
        }
        result = jsonArray.toString();
        return result;
    }
    
}
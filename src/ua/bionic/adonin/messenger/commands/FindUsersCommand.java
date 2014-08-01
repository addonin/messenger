package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.entities.User;
import ua.bionic.adonin.messenger.data.dao.IRelationsDAO;
import ua.bionic.adonin.messenger.data.dao.IUsersDAO;

public class FindUsersCommand implements ICommand {

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
				
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IUsersDAO userDAO = daoFactory.getUsersDAO();
    	IRelationsDAO relationsDAO = daoFactory.getRelationsDAO();

    	HttpSession session = request.getSession(true);
		int initiatorID = ((User) session.getAttribute("user")).getUserID();
    	String search = request.getParameter("search");
		String result = "";
    	
    	System.out.println("=== " + search + " ===");
    	
    	JSONArray jsonArray = new JSONArray();
    	    	
    	for ( User user : userDAO.findUsersByUsername(search) ) {    		
    		JSONObject jsonObject = new JSONObject();
    		int searchedUserID = user.getUserID();
    		
    		boolean checkTarget = relationsDAO.findRelation(initiatorID, searchedUserID);
    		boolean checkSource = relationsDAO.findRelation(searchedUserID, initiatorID);
    		String relation = "no";
    		if (checkSource) {
    			if (checkTarget) {
    				relation = "friend";
    			} else {
    				relation = "offer";
    			}
    		} else {
    			if (checkTarget) {
    				relation = "candidate";
    			}
    		}
    		
    		try {
    			jsonObject.put("userID", searchedUserID);
				jsonObject.put("username", user.getUsername());
				jsonObject.put("firstname", user.getFirstname());
				jsonObject.put("lastname", user.getLastname());
				jsonObject.put("email", user.getEmail());
				jsonObject.put("status", user.getStatus());
				jsonObject.put("info", user.getInfo());
				jsonObject.put("photo", user.getPhoto());
				jsonObject.put("initiator", initiatorID);
				jsonObject.put("relation", relation);
				
			} catch (JSONException e) {
				System.out.println("=== Failed to create json object ===");
				e.printStackTrace();
			}
    		jsonArray.put(jsonObject);
    	};
    	
   		result = jsonArray.toString();
    	
    	return result;
	}

}

package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IRelationsDAO;

public class AddRelationCommand implements ICommand {
	
	private static final String FROM = "fromID";
    private static final String TO = "toID";
    private static final String STATE = "state";
    private static final String NO = "no";
    private static final String OFFER = "offer";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IRelationsDAO relationsDAO = daoFactory.getRelationsDAO();

    	String result = "-1";
    	int fromID = Integer.valueOf(request.getParameter(FROM));
    	int toID = Integer.valueOf(request.getParameter(TO));
    	String state = request.getParameter(STATE);
    	
    	switch (state) {
		case NO:
			if (!relationsDAO.findRelation(fromID, toID) && !relationsDAO.findRelation(toID, fromID)) {
				result = String.valueOf(relationsDAO.addRelation(fromID, toID));
			}
			break;
		case OFFER:
			if (!relationsDAO.findRelation(fromID, toID) && relationsDAO.findRelation(toID, fromID)) {
				result = String.valueOf(relationsDAO.addRelation(fromID, toID));
			}
			break;
		default:
			//TODO kind of exception?
			break;
		} 

		return result;
	}

}

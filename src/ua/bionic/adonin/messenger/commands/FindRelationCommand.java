package ua.bionic.adonin.messenger.commands;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ua.bionic.adonin.messenger.data.dao.DAOFactory;
import ua.bionic.adonin.messenger.data.dao.IRelationsDAO;

public class FindRelationCommand implements ICommand {

	private static final String FROM = "fromID";
    private static final String TO = "toID";

	@Override
	public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		DAOFactory daoFactory = DAOFactory.getDAOFactory();
    	IRelationsDAO relationsDAO = daoFactory.getRelationsDAO();

    	int fromID = Integer.valueOf(request.getParameter(FROM));
    	int toID = Integer.valueOf(request.getParameter(TO));

		return String.valueOf(relationsDAO.findRelation(fromID, toID));
	}

}

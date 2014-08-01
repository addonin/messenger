package ua.bionic.adonin.messenger.data.dao;

public abstract class DAOFactory {
	
	public abstract IUsersDAO getUsersDAO();
	public abstract IUserTypesDAO getUserTypesDAO();
	public abstract IMessagesDAO getMessagesDAO();
	public abstract IRelationsDAO getRelationsDAO();
	
	public static DAOFactory getDAOFactory() {
		return new DAOFactoryImpl();
	}	
	
}

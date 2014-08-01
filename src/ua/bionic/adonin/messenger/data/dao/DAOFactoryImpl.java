package ua.bionic.adonin.messenger.data.dao;

public class DAOFactoryImpl extends DAOFactory {

	@Override
	public IUsersDAO getUsersDAO() {
		return new UsersDAOImpl();
	}

	@Override
	public IUserTypesDAO getUserTypesDAO() {
		return new UserTypesDAOImpl();
	}

	@Override
	public IMessagesDAO getMessagesDAO() {		
		return new MessagesDAOImpl();
	}

	@Override
	public IRelationsDAO getRelationsDAO() {		
		return new RelationsDAOImpl();
	}

}

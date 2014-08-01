package ua.bionic.adonin.messenger.data.dao;

import java.util.List;

import ua.bionic.adonin.messenger.data.entities.UserType;

public interface IUserTypesDAO {
	public int addUserType(String type);
	public List<UserType> findUserTypes();
	public int updateUserType(int typeID, String type);
	public int deleteUserType(int typeID);	
	
}

package ua.bionic.adonin.messenger.data.dao;

import java.util.List;

public interface IRelationsDAO {

	public int addRelation(int fromID, int toID);
	public List<Integer> findRelations(int fromID);
	public boolean findRelation(int fromID, int toID);
	public UnsupportedOperationException updateRelation();
	public int deleteRelation(int fromID, int toID);	
	
}

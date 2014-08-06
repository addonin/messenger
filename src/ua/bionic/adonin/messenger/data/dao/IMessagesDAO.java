package ua.bionic.adonin.messenger.data.dao;

import java.util.List;
import java.util.Map;

import ua.bionic.adonin.messenger.data.entities.Message;

public interface IMessagesDAO {
	
	public int addMessage(int senderID, int receiverID, String text);	
	public List<Message> findMessages(int senderID, int receiverID);
	public Map<Integer, Integer> findUnread(int receiverID);
	public UnsupportedOperationException updateMessage();
	public UnsupportedOperationException deleteMessage();	
	
}

package ua.bionic.adonin.messenger.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import ua.bionic.adonin.messenger.data.core.QueryJDBC;
import ua.bionic.adonin.messenger.data.entities.Message;

public class MessagesDAOImpl implements IMessagesDAO {
	
	protected final String SQL_INSERT = "INSERT INTO Messages (sender_id, receiver_id, text, status, timestamp) VALUES (?, ?, ?, ?, ?)";
	protected final String SQL_SELECT = "SELECT message_id, sender_id, receiver_id, text, status, timestamp FROM Messages";
	protected final String SQL_UPDATE = "";
	protected final String SQL_DELETE = "";
	
	
	protected final String SQL_SELECT_FROM_TO = SQL_SELECT + " WHERE sender_id = ? AND receiver_id = ?";
	//protected final String SQL_SELECT_UNREAD = SQL_SELECT + " WHERE receiver_id = ? AND status = 0"; 
	protected final String SQL_SELECT_UNREAD = "SELECT sender_id, COUNT(sender_id) FROM Messages WHERE receiver_id = ? AND status = 0 GROUP BY sender_id";
	
	//private static final int COLUMN_MESSAGE_ID = 1;
	private static final int COLUMN_SENDER_ID = 2;
	private static final int COLUMN_RECEIVER_ID = 3;
	private static final int COLUMN_TEXT = 4;
	private static final int COLUMN_STATUS = 5;
	private static final int COLUMN_TIMESTAMP = 6;
		
	@Override
	public int addMessage(int senderID, int receiverID, String text) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_INSERT);
			query.setInt(1, senderID);
			query.setInt(2, receiverID);
			query.setString(3, text);
			query.setBoolean(4, false);
			query.setDate(5, new java.sql.Date((new java.util.Date()).getTime()));
			result = query.executeUpdate();
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return result;
	}

	@Override
	public List<Message> findMessages(int senderID, int receiverID) {
		QueryJDBC query = new QueryJDBC();
		List<Message> messages = new ArrayList<>();
		try {
			query.createPreparedStatement(SQL_SELECT_FROM_TO);
			query.setInt(1, senderID);
			query.setInt(2, receiverID);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()) {
				Message message = new Message();				
				message.setSenderID(resultSet.getInt(COLUMN_SENDER_ID));
				message.setReceiverID(resultSet.getInt(COLUMN_RECEIVER_ID));
				message.setText(resultSet.getString(COLUMN_TEXT));
				message.setStatus(resultSet.getBoolean(COLUMN_STATUS));
				message.setTimestamp(resultSet.getDate(COLUMN_TIMESTAMP));
				messages.add(message);
			}
			if (resultSet != null) {
				resultSet.close();
			}
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		finally {
			query.close();
		}
		return messages;
	}

	@Override
	public Map<Integer, Integer> findUnread(int receiverID) {
		QueryJDBC query = new QueryJDBC();
		Map<Integer, Integer> unread = new HashMap<>();
		try {
			query.createPreparedStatement(SQL_SELECT_UNREAD);
			query.setInt(1, receiverID);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()) {
				unread.put(resultSet.getInt(1), resultSet.getInt(2));
			}
		} catch (SQLException e) {
			System.out.println("Failed to create prepared statement");
			e.printStackTrace();
		}
		return unread;
	}
	
	@Override
	public UnsupportedOperationException updateMessage() {		
		return new UnsupportedOperationException("Unable to update messages");
	}
	
	@Override
	public UnsupportedOperationException deleteMessage() {		
		return new UnsupportedOperationException("Unable to delete messages");
	}

}

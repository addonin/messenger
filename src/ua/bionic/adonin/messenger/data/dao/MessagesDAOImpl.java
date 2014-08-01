package ua.bionic.adonin.messenger.data.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import ua.bionic.adonin.messenger.data.core.QueryJDBC;
import ua.bionic.adonin.messenger.data.entities.Message;

public class MessagesDAOImpl implements IMessagesDAO {
	
	protected final String SQL_INSERT = "INSERT INTO Messages (sender_id, receiver_id, text, status, timestamp) VALUES (?, ?, ?, ?)";
	protected final String SQL_SELECT = "SELECT sender_id, receiver_id, text, status, timestamp FROM Messages WHERE sender_id = \"?\" AND receiver_id = \"?\"";
	protected final String SQL_UPDATE = "";
	protected final String SQL_DELETE = "";
	
	//private static final int COLUMN_MESSAGE_ID = 1;
	//private static final int COLUMN_SENDER_ID = 2;
	//private static final int COLUMN_RECEIVER_ID = 3;
	private static final int COLUMN_TEXT = 3;
	private static final int COLUMN_STATUS = 4;
	private static final int COLUMN_TIMESTAMP = 5;
		
	@Override
	public int addMessage(int senderID, int receiverID, String text) {
		QueryJDBC query = new QueryJDBC();
		int result = 0;
		try {
			query.createPreparedStatement(SQL_INSERT);
			query.setInt(1, senderID);
			query.setInt(2, receiverID);
			query.setString(3, text);
			query.setDate(4, new java.sql.Date((new java.util.Date()).getTime()));
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
			query.createPreparedStatement(SQL_SELECT);
			query.setInt(1, senderID);
			query.setInt(2, receiverID);
			ResultSet resultSet = query.executeQuery();
			while(resultSet.next()) {
				Message message = new Message();
				message.setSenderID(senderID);
				message.setReceiverID(receiverID);
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
	public UnsupportedOperationException updateMessage() {		
		return new UnsupportedOperationException("Unable to update messages");
	}
	
	@Override
	public UnsupportedOperationException deleteMessage() {		
		return new UnsupportedOperationException("Unable to delete messages");
	}



}

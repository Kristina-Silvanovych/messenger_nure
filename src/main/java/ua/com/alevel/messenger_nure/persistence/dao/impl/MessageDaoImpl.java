package ua.com.alevel.messenger_nure.persistence.dao.impl;

import ua.com.alevel.messenger_nure.config.DatabaseHandler;
import ua.com.alevel.messenger_nure.persistence.dao.MessageDao;
import ua.com.alevel.messenger_nure.persistence.entity.message.Message;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MessageDaoImpl implements MessageDao {
    private final static String CREATE_MESSAGE_QUERY = "insert into messages values(default,?,?,?,?,?,?)";

    private final static String FIND_ALL_MESSAGE_IN_CORRESPONDENCE_QUERY = "SELECT * FROM messages WHERE (recipient IN (?, ?)) AND (user_login IN (?, ?));\n";

    private final static String UPDATE_MESSAGE_QUERY = "update messages set context = ?, updated = ? where id = ?";

    private final static String DELETE_MESSAGE_BY_ID_QUERY = "delete from messages where id = ";
    private final DatabaseHandler databaseHandler;

    public MessageDaoImpl() {
        this.databaseHandler = new DatabaseHandler();
    }

    @Override
    public void create(Message entity) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(CREATE_MESSAGE_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getContext());
            preparedStatement.setString(5, entity.getRecipient());
            preparedStatement.setString(6, entity.getUserLogin());
            preparedStatement.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Message entity) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(UPDATE_MESSAGE_QUERY)) {
            preparedStatement.setString(1, entity.getContext());
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setLong(3, entity.getId());
            preparedStatement.execute();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void delete(Long id) {
        try (PreparedStatement prepared = databaseHandler.getDbConnection().prepareStatement(DELETE_MESSAGE_BY_ID_QUERY + id)) {
            prepared.execute();
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    @Override
    public Message findById(Long id) {
        return null;
    }

    public List<Message> findMessage(String userLogin, String recipient) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(FIND_ALL_MESSAGE_IN_CORRESPONDENCE_QUERY)) {
            preparedStatement.setString(1, recipient);
            preparedStatement.setString(2, userLogin);
            preparedStatement.setString(3, recipient);
            preparedStatement.setString(4, userLogin);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    return initMessagesInCorrespondenceByResultSet(resultSet);
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }


    private List<Message> initMessagesInCorrespondenceByResultSet(ResultSet resultSet) throws SQLException {
        List<Message> messageList = new ArrayList<>();
        while (resultSet.next()) {
            messageList.add(initMessageByResultSer(resultSet));
        }
        return messageList;
    }

    private Message initMessageByResultSer(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        Timestamp created = resultSet.getTimestamp("created");
        Timestamp updated = resultSet.getTimestamp("updated");
        boolean visible = resultSet.getBoolean("visible");
        String context = resultSet.getString("context");
        String recipient = resultSet.getNString("recipient");
        String userLogin = resultSet.getString("user_login");

        Message message = new Message();
        message.setId(id);
        message.setCreated(created);
        message.setUpdated(updated);
        message.setVisible(visible);
        message.setContext(context);
        message.setRecipient(recipient);
        message.setUserLogin(userLogin);
        return message;
    }
}

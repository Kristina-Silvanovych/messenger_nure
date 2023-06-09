package ua.com.alevel.messenger_nure.persistence.dao.impl;

import ua.com.alevel.messenger_nure.config.DatabaseHandler;
import ua.com.alevel.messenger_nure.persistence.dao.UserDao;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class UserDaoImpl implements UserDao {

    private final DatabaseHandler databaseHandler = new DatabaseHandler();
    private final static String CREATE_USER_QUERY = "insert into users values(default,?,?,?,?,?,?,?)";

    private final static String FIND_USER_BY_LOGIN_QUERY = "select * from users where login = ?";

    private final static String FIND_ALL_USER_CORRESPONDENCE = "SELECT DISTINCT CASE WHEN recipient = ? THEN user_login WHEN user_login = ? THEN recipient END AS result FROM messages WHERE recipient = ? OR user_login = ?";

    @Override
    public void create(User entity) throws ClassNotFoundException {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(CREATE_USER_QUERY)) {
            preparedStatement.setTimestamp(1, new Timestamp(entity.getCreated().getTime()));
            preparedStatement.setTimestamp(2, new Timestamp(entity.getUpdated().getTime()));
            preparedStatement.setBoolean(3, entity.getVisible());
            preparedStatement.setString(4, entity.getPhoneNumber());
            preparedStatement.setString(5, entity.getEmail());
            preparedStatement.setString(6, entity.getLogin());
            preparedStatement.setString(7, entity.getPassword());
            preparedStatement.execute();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }


    @Override
    public User findByLogin(String login) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(FIND_USER_BY_LOGIN_QUERY)) {
            preparedStatement.setString(1, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    return initUserByResultSet(resultSet);
                }
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public List<String> findAllUserCorrespondence(String login) {
        try (PreparedStatement preparedStatement = databaseHandler.getDbConnection().prepareStatement(FIND_ALL_USER_CORRESPONDENCE)) {
            preparedStatement.setString(1, login);
            preparedStatement.setString(2, login);
            preparedStatement.setString(3, login);
            preparedStatement.setString(4, login);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                List<String> stringList = new ArrayList<>();
                while (resultSet.next()){
                    stringList.add(resultSet.getString("result"));
                }
                return stringList;
            }
        } catch (SQLException | ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

//    @TODO Finish the job
    @Override
    public void update(User entity) {

    }

    //    @TODO Finish the job
    @Override
    public void delete(Long id) {

    }

    @Override
    public boolean existById(Long id) {
        return false;
    }

    //    @TODO Finish the job
    @Override
    public User findById(Long id) {
        return null;
    }

    private User initUserByResultSet(ResultSet resultSet) {
        try {
            Long id = resultSet.getLong("id");
            Timestamp created = resultSet.getTimestamp("created");
            Timestamp updated = resultSet.getTimestamp("updated");
            boolean visible = resultSet.getBoolean("visible");
            String login = resultSet.getString("login");
            String email = resultSet.getString("email");
            String phoneNumber = resultSet.getString("phone_number");
            String password = resultSet.getNString("password");

            User user = new User();
            user.setId(id);
            user.setCreated(created);
            user.setUpdated(updated);
            user.setLogin(login);
            user.setVisible(visible);
            user.setEmail(email);
            user.setPhoneNumber(phoneNumber);
            user.setPassword(password);
            System.out.println("user = " + user);
            return user;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}

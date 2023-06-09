package ua.com.alevel.messenger_nure.persistence.dao;

import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserDao extends BaseDao<User>{
    void create(User entity) throws SQLException, ClassNotFoundException;

    User findByLogin(String login);

    List<String> findAllUserCorrespondence(String login);
}

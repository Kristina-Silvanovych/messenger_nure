package ua.com.alevel.messenger_nure.service;

import ua.com.alevel.messenger_nure.persistence.entity.user.User;

import java.sql.SQLException;
import java.util.List;

public interface UserService extends BaseService<User> {

    void create(User entity) throws SQLException, ClassNotFoundException;

    User findByLogin(String login);

    List<String> findAllUserCorrespondence(String login);
}

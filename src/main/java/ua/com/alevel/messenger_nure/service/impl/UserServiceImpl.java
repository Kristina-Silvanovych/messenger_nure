package ua.com.alevel.messenger_nure.service.impl;

import ua.com.alevel.messenger_nure.persistence.dao.UserDao;
import ua.com.alevel.messenger_nure.persistence.dao.impl.UserDaoImpl;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;
import ua.com.alevel.messenger_nure.service.UserService;

import java.sql.SQLException;
import java.util.List;

public class UserServiceImpl implements UserService {

    private final UserDao userDao = new UserDaoImpl();
    @Override
    public void create(User entity) throws SQLException, ClassNotFoundException {
        userDao.create(entity);
    }

    @Override
    public void update(User entity) {
        userDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        userDao.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userDao.findById(id);
    }

    @Override
    public boolean existById(Long id) {
        return userDao.existById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userDao.findByLogin(login);
    }

    @Override
    public List<String> findAllUserCorrespondence(String login) {
        return userDao.findAllUserCorrespondence(login);
    }
}

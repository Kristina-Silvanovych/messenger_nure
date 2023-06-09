package ua.com.alevel.messenger_nure.facade.impl;

import ua.com.alevel.messenger_nure.facade.UserFacade;
import ua.com.alevel.messenger_nure.persistence.entity.user.User;
import ua.com.alevel.messenger_nure.service.UserService;
import ua.com.alevel.messenger_nure.service.impl.UserServiceImpl;

import java.sql.SQLException;
import java.util.List;

public class UserFacadeImpl implements UserFacade {

    private final UserService userService;

    public UserFacadeImpl() {
        this.userService = new UserServiceImpl();
    }

    @Override
    public void create(User entity) throws SQLException, ClassNotFoundException {
        userService.create(entity);
    }

    @Override
    public void update(User entity) {
        userService.update(entity);
    }

    @Override
    public void delete(Long id) {
        userService.delete(id);
    }

    @Override
    public User findById(Long id) {
        return userService.findById(id);
    }

    @Override
    public User findByLogin(String login) {
        return userService.findByLogin(login);
    }

    @Override
    public List<String> findAllUserCorrespondence(String login) {
        return userService.findAllUserCorrespondence(login);
    }
}

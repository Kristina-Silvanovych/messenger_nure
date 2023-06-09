package ua.com.alevel.messenger_nure.persistence.dao;

import ua.com.alevel.messenger_nure.persistence.entity.message.Message;

import java.util.List;

public interface MessageDao extends BaseDao<Message> {

    void create(Message entity);

    List<Message> findMessage(String userLogin, String recipient);

}

package ua.com.alevel.messenger_nure.service;

import ua.com.alevel.messenger_nure.persistence.entity.message.Message;

import java.util.List;

public interface MessageService extends BaseService<Message> {

    void create(Message entity);

    List<Message> findMessage(String userLogin, String recipient);
}

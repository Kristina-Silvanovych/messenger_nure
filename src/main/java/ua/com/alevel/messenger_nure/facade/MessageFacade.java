package ua.com.alevel.messenger_nure.facade;

import ua.com.alevel.messenger_nure.persistence.entity.message.Message;

import java.util.List;

public interface MessageFacade extends BaseFacade<Message> {

    void create(Message entity);

    List<Message> findMessage(String userLogin, String recipient);
}

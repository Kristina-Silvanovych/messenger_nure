package ua.com.alevel.messenger_nure.service.impl;

import ua.com.alevel.messenger_nure.persistence.dao.MessageDao;
import ua.com.alevel.messenger_nure.persistence.dao.impl.MessageDaoImpl;
import ua.com.alevel.messenger_nure.persistence.entity.message.Message;
import ua.com.alevel.messenger_nure.service.MessageService;

import java.util.List;

public class MessageServiceImpl implements MessageService {

    private final MessageDao messageDao = new MessageDaoImpl();
    @Override
    public void create(Message entity) {
        messageDao.create(entity);
    }

    @Override
    public void update(Message entity) {
        messageDao.update(entity);
    }

    @Override
    public void delete(Long id) {
        messageDao.delete(id);
    }

    @Override
    public Message findById(Long id) {
        return messageDao.findById(id);
    }

    @Override
    public boolean existById(Long id) {
        return messageDao.existById(id);
    }

    @Override
    public List<Message> findMessage(String userLogin, String recipient) {
        return messageDao.findMessage(userLogin, recipient);
    }
}

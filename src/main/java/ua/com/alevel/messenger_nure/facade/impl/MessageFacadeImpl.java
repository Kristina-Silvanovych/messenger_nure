package ua.com.alevel.messenger_nure.facade.impl;

import ua.com.alevel.messenger_nure.facade.MessageFacade;
import ua.com.alevel.messenger_nure.persistence.entity.message.Message;
import ua.com.alevel.messenger_nure.service.MessageService;
import ua.com.alevel.messenger_nure.service.impl.MessageServiceImpl;

import java.util.List;

public class MessageFacadeImpl implements MessageFacade {

    private final MessageService messageService;

    public MessageFacadeImpl() {
        this.messageService = new MessageServiceImpl();
    }

    @Override
    public void create(Message entity) {
        messageService.create(entity);
    }

    @Override
    public void update(Message entity) {
        messageService.update(entity);
    }

    @Override
    public void delete(Long id) {
        messageService.delete(id);
    }

    @Override
    public Message findById(Long id) {
        return messageService.findById(id);
    }

    @Override
    public List<Message> findMessage(String userLogin, String recipient) {
        return messageService.findMessage(userLogin, recipient);
    }
}
